package com.ly.task.weituo.zuot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


//https://m.stock.pingan.com/html/h5security/trade/index.html
@Component
public class DiBuyGaoSellTask {
	
	
	@Autowired
	private BuyBySystem bySystem;
	
	@Scheduled(cron = "40 0 10 ? * MON-FRI")
	public void task() throws Exception{
		bySystem.taskForBuy();
	}
	
	@Scheduled(cron = "40 30 10,11 ? * MON-FRI")
	public void task02() throws Exception{
		bySystem.taskForBuy();
	}
	
	@Scheduled(cron = "40 30 13,14 ? * MON-FRI")
	public void task03() throws Exception{
		bySystem.taskForBuy();
	}
	
}
