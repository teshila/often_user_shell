package com.ly.task.jsoup.day.cal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.ly.task.jsoup.day.cal.computing.CalStock_Shou_Yang_Day_One_DayComputing;
import com.ly.task.jsoup.day.cal.computing.CalStock_Shou_Yang_Day_Three_DayComputing;
import com.ly.task.jsoup.day.cal.computing.CalStock_Shou_Yang_Day_Two_DayComputing;
import com.ly.task.jsoup.day.cal.computing.Chi_CangDayUpComputing;

@EnableAsync
@Component
public class CalDayLineUpAsync {
	
	@Autowired
	private CalStock_Shou_Yang_Day_One_DayComputing calstock_shou_yang_day_one_day;
	
	
	@Autowired
	private CalStock_Shou_Yang_Day_Two_DayComputing calstock_shou_yang_day_two_dayservice;
	
	
	@Autowired
	private CalStock_Shou_Yang_Day_Three_DayComputing calstock_shou_yang_day_three_dayservice;
	@Autowired
	private Chi_CangDayUpComputing chi_CangDayUpComputing;
	
	
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task01() {
		calstock_shou_yang_day_one_day.getStockInfo();
	}
	
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task02() {
		calstock_shou_yang_day_two_dayservice.getStockInfo();
	}
	
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task03() {
		calstock_shou_yang_day_three_dayservice.getStockInfo();
	}
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task04() {
		chi_CangDayUpComputing.getStockInfo();
	}
	
}
