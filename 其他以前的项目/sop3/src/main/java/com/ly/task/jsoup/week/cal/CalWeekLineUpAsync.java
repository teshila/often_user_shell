package com.ly.task.jsoup.week.cal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.ly.task.jsoup.week.cal.computing.WeekLineMa10DaYuMa20Computing;
import com.ly.task.jsoup.week.cal.computing.WeekMa5VolBigMa10VolComputing;
import com.ly.task.jsoup.week.cal.computing.WeekMa5VolQuShiComputing;

@EnableAsync
@Component
public class CalWeekLineUpAsync {

	@Autowired
	private  WeekLineMa10DaYuMa20Computing stock_shou_yang_only_week_shou_yang_computing;
	
	  
	@Autowired
	private  WeekMa5VolQuShiComputing weekMa5;
	
	
	@Autowired
	private  WeekMa5VolBigMa10VolComputing weekMa10;
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task02() {
		stock_shou_yang_only_week_shou_yang_computing.getStockInfo();
	}
	
	
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task03() {
		weekMa5.getStockInfo();
	}
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task04() {
		weekMa10.getStockInfo();
	}
}
