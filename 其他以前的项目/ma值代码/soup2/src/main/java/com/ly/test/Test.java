package com.ly.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ly.dao.impl.AccountDao;
import com.ly.dao.impl.StockDao;
import com.ly.pojo.Account;
import com.ly.pojo.Account_Operation;
import com.ly.task.jsoup.day.cal.computing.CalStock_Shou_Yang_Day_One_DayComputing;
import com.ly.task.jsoup.day.cal.computing.CalStock_Shou_Yang_Day_Three_DayComputing;
import com.ly.task.jsoup.day.cal.computing.CalStock_Shou_Yang_Day_Two_DayComputing;

public class Test {

	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:app.xml");
		AccountDao da  = ac.getBean(AccountDao.class);
		
		
		List<Account> list =da.getAccounts();
		for (Account account : list) {
			System.out.println(account.toString());
		}
	}
}
