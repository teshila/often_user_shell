package com.ly.task.weituo.jijing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.common.http.PingAnWithouAuthInfo;
import com.ly.core.StockTradeCore;
import com.ly.dao.impl.AccountDao;
import com.ly.pojo.Account;
import com.ly.pojo.Account_Ji_Jing;


//https://m.stock.pingan.com/html/h5security/trade/index.html
@Component
public class BuyJiJing {
	
	private static final Logger logger = Logger.getLogger("sendWeiTuoInfo");
	
	
	@Autowired
	private StockTradeCore stockTradeBus;
	
	@Autowired
	private PingAnWithouAuthInfo pinanHttpUtil;

	@Autowired
	private AccountDao accountDao;
	
	//@Scheduled(cron="0/20 * * ? * *")
	@Scheduled(cron="01 55 15 ? * MON-FRI")
	public void doCheDan() throws Exception{
		List<Account> accounts = accountDao.find("select distinct  t from  Account  t left join  fetch t.optionsJiJing where isWeiTuo is not null");
		
		
		if(accounts!=null&&accounts.size()>0){
			
			String code = "131810";
			Map<String, String> codeMap = new HashMap<String, String>();
			codeMap.put("code", code);
			codeMap.put("marketType","4611");
			
			Map<String, String> map = pinanHttpUtil.getInfo(codeMap);
			String nameStr = map.get("stockName");
			String prevClose = map.get("prevClose");
			String openStr = map.get("open");
			String buyPrice3 = map.get("buyPrice3");
			String newPriceStr = map.get("newPrice"); // 当前价
			String minPriceString = map.get("minPrice");
			
			Map<String, String> lastestMap = new HashMap<String, String>();
			lastestMap.put("stockCode", code);
			lastestMap.put("stockName", nameStr.trim());
			lastestMap.put("exchangeType","2");
			lastestMap.put("prevClosePrice", prevClose);
			
			for (int i = 0; i < accounts.size(); i++) {
				Account acc = accounts.get(i);
				stockTradeBus.doCheDan(true,acc,false);  //进行撤单
				List<Account_Ji_Jing> opts  = acc.getOptionsJiJing();
				String type = "";
				for (Account_Ji_Jing obj : opts) {
					if(obj.getIsdefault().equals("1")){
						type = obj.getOperationType();
						if(type.equals("自动买")){
							String nums = stockTradeBus.doBuyForJiJing(code, newPriceStr, acc, lastestMap,1);
							logger.info("代码【" + code + "】，名称【" + nameStr + "】,当前基金买入价为====> " + newPriceStr +",账号【"+acc.getAccount()+"】,买入量为 【" + nums+"】股");
						}
					}
				}
				
				if(i>0){
					Thread.sleep(3*1000);
				}
			}
		}
		
	}
}
