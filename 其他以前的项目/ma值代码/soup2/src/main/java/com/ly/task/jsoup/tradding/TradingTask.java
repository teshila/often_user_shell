package com.ly.task.jsoup.tradding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.core.GetStockQuShiLine;
import com.ly.core.TradingInfo;
import com.ly.dao.impl.Trading_Stock_Yang_Of_Two_Day_UpDao;
import com.ly.email.EmailTemplate;
import com.ly.pojo.Trading_Stock_Yang_Of_Two_Day_Up;


//https://blog.csdn.net/qian_ch/article/details/57419720
@Component
public class TradingTask {
	
	@Autowired
	private GetStockQuShiLine getStockQuShiLine;

	@Autowired
	private TradingInfo tradingInfo;

	@Autowired
	private Trading_Stock_Yang_Of_Two_Day_UpDao tradingTwoDayUpDao;
	
	@Autowired
	private EmailTemplate template;
	

	public static final String title2 = "最近两天涨的";

	@Scheduled(cron = "0 50 9 ? * MON-FRI")
	public void task01() throws Exception {
		doTask();
	}

	@Scheduled(cron = "0 30 10 ? * MON-FRI")
	public void task02() throws Exception {
		doTask();
	}

	@Scheduled(cron = "0 30 11 ? * MON-FRI")
	public void task03() throws Exception {
		doTask();
	}

	@Scheduled(cron = "0 30 13 ? * MON-FRI")
	public void task04() throws Exception {
		doTask();
	}

	@Scheduled(cron = "0 30 14 ? * MON-FRI")
	public void task05() throws Exception {
		doTask();
	}
	
	
	public void doTask(){
		
		new Thread() {
			public void run() {
				try {
					getStockQuShiLine.getStockPic();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();// 开启线程
		
		
		new Thread() {
			public void run() {
				try {
					tradingInfo.getInfo();
					List<Trading_Stock_Yang_Of_Two_Day_Up> trading = tradingTwoDayUpDao.find("from Trading_Stock_Yang_Of_Two_Day_Up order by convert (closePrice, decimal(6, 2))   asc");
					template.send(title2 + "，共" + trading.size() + "条", trading);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();// 开启线程
		

		
	}
}
