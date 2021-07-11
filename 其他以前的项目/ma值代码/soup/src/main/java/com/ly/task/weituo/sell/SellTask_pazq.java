package com.ly.task.weituo.sell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.common.http.PingAnWithouAuthInfo;
import com.ly.common.util.CalcUtil;
import com.ly.core.StockTradeCore;
import com.ly.dao.impl.AccountDao;
import com.ly.dao.impl.SMS_RecevierDao;
import com.ly.dao.impl.SellDao;
import com.ly.dao.impl.SettingDao;
import com.ly.email.MyJavaMailSenderUtil;
import com.ly.pojo.Account;
import com.ly.pojo.Account_Operation;
import com.ly.pojo.SMS_Recevier;
import com.ly.pojo.Sell;
import com.ly.pojo.Setting;
import com.ly.sms.MyTencentSMSUtils;

//https://www.cnblogs.com/zy-jiayou/p/7007303.html

@Component
public class SellTask_pazq {
	//private static Logger logger = Logger.getLogger(ForSell.class);
	private static final Logger logger = Logger.getLogger("sendWeiTuoInfo");
	
	@Autowired
	private SettingDao settingDao;

	@Autowired
	private SellDao sellDao;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private StockTradeCore stockTradeBus;

	@Autowired
	private PingAnWithouAuthInfo pinanHttpUtil;
	
	@Autowired
	private MyTencentSMSUtils tencentSMS;
	
	@Autowired
	private SMS_RecevierDao smsDao;
	

	@Autowired
	private MyJavaMailSenderUtil srv;
	
	
	//@Scheduled(cron="0/20 * * * * ?")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Scheduled(cron = "30 35 9 ? * MON-FRI")
	public void taskSell() throws Exception {
		SMS_Recevier smsPhone = null;
		List<Account> accounts = accountDao.find("select distinct t from  Account  t left outer join fetch t.options order by seqs");
		List<Sell> sells = sellDao.find("from Sell");
		if (sells != null && sells.size() > 0) {
			for (int i = 0; i < sells.size(); i++) {
				Sell sell = sells.get(i);
				String code = sell.getCode();
				String exchange_type = sell.getExchangeType();
				Map<String, String> codeMap = new HashMap<String, String>();
				codeMap.put("code", sell.getCode());
				codeMap.put("marketType", sell.getMarketType());
				Map<String, String> map;
				try {
					map = pinanHttpUtil.getInfo(codeMap);
					
					String nameStr = map.get("stockName");
					String prevCloseStr = map.get("prevClose");
					String openStr = map.get("open");
					String newPriceStr = map.get("newPrice"); // 当前价
					
					String weiTuoBuyPriceStr = sell.getBuyPrice();
					
					Double newPrice = Double.valueOf(newPriceStr);
					Double openPrice = Double.valueOf(openStr);
					Double prevClosePrice = Double.valueOf(prevCloseStr);
					
					
					Double zhangDe = openPrice - prevClosePrice;
					
					Double fuDu = (zhangDe / prevClosePrice)*100;
					String fuDuStr = CalcUtil.formatDouble(fuDu);
					Double zhangDieLv = Double.valueOf(fuDuStr);
					
					
					logger.debug("代码【"+code+"】，名称【"+nameStr+"】, 本日开盘【"+openStr+"】，开盘涨跌百分比【"+zhangDieLv+"】， 本日开盘涨跌===> 【 " + zhangDe+" 】 ,当前价【"+newPriceStr+"】，,本日涨停价 【"+ prevClosePrice*1.1 +"】 ,本日跌停价 【" +prevClosePrice*0.9+ "】,昨收 【"+prevClosePrice+"】 ");
					logger.info("代码【"+code+"】，名称【"+nameStr+"】, 本日开盘【"+openStr+"】，开盘涨跌百分比【"+zhangDieLv+"】， 本日开盘涨跌===> 【 " + zhangDe+" 】 ,当前价【"+newPriceStr+"】，,本日涨停价 【"+ prevClosePrice*1.1 +"】 ,本日跌停价 【" +prevClosePrice*0.9+ "】,昨收 【"+prevClosePrice+"】 ");
					
					Double guaDanJia =  Double.valueOf(weiTuoBuyPriceStr) * (1 + 2.1 / 100); //每次系统自动交易的话，赚2.0个点
					
					String priceForSell = guaDanJia+"";
					
					String weiTuoPrice = null;// 卖出价

					Map<String, String> lastestMap = new HashMap<String, String>();
					lastestMap.put("stockCode", code);
					lastestMap.put("stockName", nameStr);
					lastestMap.put("exchangeType", exchange_type);
					lastestMap.put("prevClosePrice", prevCloseStr);
					

					Setting setting = settingDao.find("from Setting where isdefault> 0 and type=2").get(0);
					String defaultPercent = setting.getPercent_value();
					Integer selfCangWei =Integer.valueOf(defaultPercent);
					int sellNum =  0;
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
							if(type.equals("只卖不买")||type.equals("买卖同操")){
								if(guaDanJia>prevClosePrice*1.1){
									priceForSell = prevClosePrice*1.1 + "" ;
								}
								
								
								weiTuoPrice = CalcUtil.formatDouble(priceForSell);
								sellNum = stockTradeBus.doSell(code, weiTuoPrice, ac, lastestMap,selfCangWei);	
								logger.debug("代码【" + code + "】,名称【" + nameStr + "】卖出委托,委托价" + weiTuoPrice + " ,卖出委托数量 " + sellNum);
								logger.info("代码【" + code + "】,名称【" + nameStr + "】卖出委托,委托价" + weiTuoPrice + " ,卖出委托数量 " + sellNum);
							}
							if(j>0){
								Thread.sleep(3*1000);
							}
							
						}
					}
						
					sell.setWeiTuoTodaySellPrice(weiTuoPrice);
					sellDao.update(sell);
					
					if (nameStr.contains("神州")) {
						nameStr = nameStr.replace("神州", "sz");
					}
					String  [] params = {code,"【"+nameStr+"】","【"+weiTuoPrice+"】卖"};
					List<SMS_Recevier> phones = smsDao.find("from SMS_Recevier");
					logger.debug("查询要发送的短信手机号码列表" + phones);
					ArrayList phoneList = new ArrayList();
					if (phoneList != null && phones.size() > 0) {
						for (int k = 0; k < phones.size(); k++) {
							smsPhone = phones.get(k);
							phoneList.add(smsPhone.getPhone());
						}
					}
					try {
						tencentSMS.sendSell(phoneList, params);
					} catch (Exception e) {
						logger.debug(e.getMessage());
					    e.printStackTrace();
					}
					
					StringBuffer title = new StringBuffer();
					StringBuffer contents = new StringBuffer();
					title.append("代码【" + code + "】,名称【" + nameStr + "】卖出委托,委托价" + weiTuoPrice);
					contents.append("代码【" + code + "】,名称【" + nameStr + "】卖出委托,委托" + weiTuoPrice);
					srv.sendEmail(title.toString(), contents.toString());
				} catch (IOException e) {
					logger.debug(e.getMessage());
					logger.info(e.getMessage());
					e.printStackTrace();
				}
			}
		} else {
			logger.debug("定时查询到不存在需要卖的列表,当前不需要挂单");
		}
		
		
		
		
	}
}
