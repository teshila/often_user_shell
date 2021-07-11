package com.ly.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ly.task.jsoup.day.cal.computing.Chi_Cang_Day_UpComputing;


public class Test2 {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:app.xml");
		/*GetDayLineInfoByPingAnHttpV2 da = ac.getBean(GetDayLineInfoByPingAnHttpV2.class);
		
		da.getDayLineInfo("000983");*/
		

		
		
		Chi_Cang_Day_UpComputing ds = ac.getBean(Chi_Cang_Day_UpComputing.class);
		
		
		ds.getStockInfo();
	}
}
