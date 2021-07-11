package com.ly.task.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.ly.task.jsoup.GetStockQuShiLine;


//@Component
public class UpdatStockQuShiTask {
	@Autowired
	private GetStockQuShiLine getStockQuShiLine;
	
	
	@Scheduled(cron = "0 11 15 ? * MON-FRI")
	public void taskSendDay() throws Exception {
		getStockQuShiLine.getStockPic();
	}
}
