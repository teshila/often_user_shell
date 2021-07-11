package com.ly.task.weituo.buy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.common.http.PingAnWithouAuthInfo;
import com.ly.common.util.CalcUtil;
import com.ly.core.StockTradeCore;
import com.ly.dao.impl.AccountDao;
import com.ly.dao.impl.BuyDao;
import com.ly.dao.impl.SMS_RecevierDao;
import com.ly.dao.impl.SettingDao;
import com.ly.email.MyJavaMailSenderUtil;
import com.ly.pinyin.YePinYinUtils;
import com.ly.pojo.Account;
import com.ly.pojo.Account_Operation;
import com.ly.pojo.Buy;
import com.ly.pojo.SMS_Recevier;
import com.ly.pojo.Setting;
import com.ly.sms.MyTencentSMSUtils;

@Component
public class BuyTask_pazq {
	
	//http://nuff.eastmoney.com/EM_Finance2015TradeInterface/JS.ashx?id=0025072
	private static final Logger logger = Logger.getLogger("sendWeiTuoInfo");
	
	@Autowired
	private StockTradeCore stockTradeBus;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private BuyDao buyDao;
	

	@Autowired
	private PingAnWithouAuthInfo pinanHttpUtil;

	@Autowired
	private SMS_RecevierDao smsDao;


	@Autowired
	private MyTencentSMSUtils tencentSMS;
	
	@Autowired
	private MyJavaMailSenderUtil srv;
	
	@Autowired
	private SettingDao settingDao;
	

	//@Scheduled(cron="0/20 * * ? * *")
	@Scheduled(cron="05 30 9 ? * MON-FRI")
	public void taskForBuy() throws Exception {
		List<Account> accounts = accountDao.find("select distinct t from  Account  t left outer join fetch t.options order by seqs");
		List<Buy> buys = buyDao.findAll(Buy.class);
		SMS_Recevier smsPhone = null;
		
		if (buys != null && buys.size() > 0) {
			for (int i = 0; i < buys.size(); i++) {
				Buy mo = buys.get(i);
				String code = mo.getCode();
				String market_type = mo.getMarketType();
				Map<String, String> codeMap = new HashMap<String, String>();
				codeMap.put("code", code);
				codeMap.put("marketType", mo.getMarketType());
				Double priceForBuy = 0.0;
				try {
					Map<String, String> map = pinanHttpUtil.getInfo(codeMap);
					String nameStr = map.get("stockName");
					String prevClose = map.get("prevClose");
					String openStr = map.get("open");
					String buyPrice3 = map.get("buyPrice3");
					String newPriceStr = map.get("newPrice"); // 当前价
					String minPriceString = map.get("minPrice");

					Double openPrice = Double.valueOf(openStr);
					Double newPrice = Double.valueOf(newPriceStr);
					Double prevClosePrice = Double.valueOf(prevClose);
				
					Double zhangDe = newPrice - prevClosePrice;//当前涨跌
					Double fuDu = (zhangDe / prevClosePrice) * 100;
					
					List<Map<String,String>> resultList = pinanHttpUtil.getDayLineMaInfo(code, market_type);
					
					Map retMap = resultList.get(resultList.size()-1);
					//ma5=7.59, ma10=7.58, ma30=7.52
					String ma5 = (String) retMap.get("ma5");
					String ma10 = (String) retMap.get("ma10");
					Double ma5Price = Double.valueOf(ma5);
					
					String fuDuStr = CalcUtil.formatDouble(fuDu);
					Double zhangDieLv = Double.valueOf(fuDuStr);
					
					//涨跌幅=(今日收盘价-昨日收盘价)/昨日收盘价*100%。
					//改用如下的方法是最安全的了，昨收阳，且上五日线，本日现价要大于开盘，且大于昨天的价格,且本日的五日线大于本日的五日线,newPrice为当前价，ma5Price为本日五日线价
					
					String yuShePriceString = mo.getYuSheBuyPrice();
					if(StringUtils.isEmpty(yuShePriceString)){
						
					}
					
					if(newPrice>ma5Price){
						priceForBuy = newPrice *(1 - 1.6 / 100);
					}else{
						priceForBuy = 	newPrice * (1 - 2.68 / 100);//开盘价跌4个点，改为现价跌2.68个点
					}
				
					
					//ma5 ma10
					String weiTuoPrice = CalcUtil.formatDouble(priceForBuy);
					logger.debug("代码【" + code + "】，名称【" + nameStr + "】"+", 5日线价格=> "+ma5+" ,10日线价格=> "+ma10+" , 20日线价格=>, 当前价【" + newPriceStr + "】，当前涨跌==> " +zhangDieLv);
					
					Map<String, String> lastestMap = new HashMap<String, String>();
					lastestMap.put("stockCode", mo.getCode());
					lastestMap.put("stockName", nameStr.trim());
					lastestMap.put("exchangeType", mo.getExchangeType());
					lastestMap.put("prevClosePrice", prevClose);

					Setting setting = settingDao.find("from Setting where isdefault> 0 and type=1").get(0);
					String defaultPercent = setting.getPercent_value();
					Integer selfCangWei =Integer.valueOf(defaultPercent);
					if (accounts != null && accounts.size() > 0) {
						for (int j = 0; j < accounts.size(); j++) {
							Account ac = accounts.get(j);
						
							String type= "";
							
							List<Account_Operation> opts  = ac.getOptions();
							for (Account_Operation obj : opts) {
								if(obj.getIsdefault().equals("1")){
									type = obj.getOperationType();
									
								}
							}
							if(type.equals("只买不卖")||type.equals("买卖同操")){
								if (weiTuoPrice != null) {
									String nums = stockTradeBus.doBuy(mo.getCode(), weiTuoPrice, ac, lastestMap,selfCangWei);
									logger.debug("代码【" + code + "】，名称【" + nameStr + "】,当前买入价为====> " + weiTuoPrice +",账号【"+ac.getAccount()+"】,买入量为 【" + nums+"】股");
									logger.info("代码【" + code + "】，名称【" + nameStr + "】,当前买入价为====> " + weiTuoPrice +",账号【"+ac.getAccount()+"】,买入量为 【" + nums+"】股");
								}
							}
							
							if(j>0){
								Thread.sleep(3*1000);
							}
						}
					}
					
					mo.setWeiTuoPrice(weiTuoPrice);
					YePinYinUtils.convertChineseToPinyin(nameStr);
					buyDao.update(mo);
					
					
					if (nameStr.contains("神州")) {
						nameStr = nameStr.replace("神州", "sz");
					}
					String[] params = {nameStr, "【涨跌" + zhangDieLv + "】", "【开" + openPrice + "】", "【" + weiTuoPrice + "】买" };
					List<SMS_Recevier> phones = smsDao.find("from SMS_Recevier");
					logger.debug("查询要发送的短信手机号码列表" + phones);
					ArrayList phoneList = new ArrayList();
					if (phones != null && phones.size() > 0) {
						for (int k = 0; k < phones.size(); k++) {
							smsPhone = phones.get(k);
							phoneList.add(smsPhone.getPhone());
						}
					}
					
					try {
						tencentSMS.sendBuy(phoneList, params);
					} catch (Exception e) {
						logger.debug(e.getMessage());
						e.printStackTrace();
					}
					
					
					StringBuffer title = new StringBuffer();
					StringBuffer contents = new StringBuffer();
					title.append("代码【" + code + "】,名称【" + nameStr + "】发起委托,委托介入区位" + weiTuoPrice);
					contents.append("代码【" + code + "】,名称【" + nameStr + "】委托,委托" + weiTuoPrice + " , 当前系统信息==>"+Math.random());
					srv.sendEmail(title.toString(), contents.toString());
							
				} catch (IOException e) {
					logger.debug(e.getMessage());
					e.printStackTrace();
				} 
			}
		}else{
			logger.debug("定时查询到不存在需要卖的列表,当前不需要挂单");
		}

	}

}
