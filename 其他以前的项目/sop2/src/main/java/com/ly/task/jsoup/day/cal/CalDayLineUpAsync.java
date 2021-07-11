package com.ly.task.jsoup.day.cal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.ly.task.jsoup.day.cal.computing.CalStock_Shou_Yang_Day_One_DayComputing;
import com.ly.task.jsoup.day.cal.computing.CalStock_Shou_Yang_Day_Three_DayComputing;
import com.ly.task.jsoup.day.cal.computing.CalStock_Shou_Yang_Day_Two_DayComputing;
import com.ly.task.jsoup.day.cal.computing.CalWu_Ri_Shi_Ri_Chong_HeComputing;
import com.ly.task.jsoup.day.cal.computing.Calc_Shi_Ri_Jun_Xian_UpComputing;
import com.ly.task.jsoup.day.cal.computing.Chi_Cang_Day_UpComputing;
import com.ly.task.jsoup.day.cal.computing.Day_Line_Ma10_Da_Yu_Ma20Computing;
import com.ly.task.jsoup.day.cal.computing.Ma5VolBigMa10VolComputing;
import com.ly.task.jsoup.day.cal.computing.Ma5VolQuShiComputing;

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
	private Chi_Cang_Day_UpComputing chi_CangDayUpComputing;
	
	@Autowired
	private Calc_Shi_Ri_Jun_Xian_UpComputing calcShiRiJunXianUpComputing;

	@Autowired
	private CalWu_Ri_Shi_Ri_Chong_HeComputing calWu_Ri_Shi_Ri_Chong_HeComputing;
	
	@Autowired
	private Day_Line_Ma10_Da_Yu_Ma20Computing day_Line_Ma10_Da_Yu_Ma20Computing;
	
	
	
	@Autowired
	private Ma5VolQuShiComputing dayma5VolQuShi;
	
	@Autowired
	private Ma5VolBigMa10VolComputing dayma5VolBigMa10Vol;
	
	
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
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task05() {
		calcShiRiJunXianUpComputing.getStockInfo();
	}
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task06() {
		calWu_Ri_Shi_Ri_Chong_HeComputing.getStockInfo();
	}
	
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task07() {
		day_Line_Ma10_Da_Yu_Ma20Computing.getStockInfo();
	}
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task08() {
		dayma5VolQuShi.getStockInfo();
	}
	
	@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void task09() {
		dayma5VolBigMa10Vol.getStockInfo();
	}
	
}
