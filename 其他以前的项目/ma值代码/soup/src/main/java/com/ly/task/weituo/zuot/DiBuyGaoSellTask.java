package com.ly.task.weituo.zuot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


//https://m.stock.pingan.com/html/h5security/trade/index.html
@Component
public class DiBuyGaoSellTask {
	
	
	@Autowired
	private BuyBySystem bySystem;
	
	@Scheduled(cron = "20 0,30 10,13,14 ? * MON-FRI")
	public void task02() throws Exception{
		bySystem.taskForBuy();
	}
	
	
	@Scheduled(cron = "20 0 11 ? * MON-FRI")
	public void task03() throws Exception{
		bySystem.taskForBuy();
	}
	
	@Scheduled(cron = "20 45 9 ? * MON-FRI")
	public void task() throws Exception{
		bySystem.taskForBuy();
	}
	
}
