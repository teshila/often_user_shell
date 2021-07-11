package com.ly.task.jsoup.week.cal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.ly.task.jsoup.week.cal.computing.Stock_Shou_Yang_Day_And_Week_Shou_Yang_Computing;
import com.ly.task.jsoup.week.cal.computing.Stock_Shou_Yang_Only_Week_Shou_Yang_Computing;

@EnableAsync
@Component
public class CalWeekLineUpAsync {

	@Autowired
	private Stock_Shou_Yang_Day_And_Week_Shou_Yang_Computing stock_shou_yang_day_and_week_shou_yang_computing;
	
	
	@Autowired
	private  Stock_Shou_Yang_Only_Week_Shou_Yang_Computing stock_shou_yang_only_week_shou_yang_computing;
	
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task01() {
		stock_shou_yang_day_and_week_shou_yang_computing.getStockInfo();
	}
	
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task02() {
		stock_shou_yang_only_week_shou_yang_computing.getStockInfo();
	}
}
