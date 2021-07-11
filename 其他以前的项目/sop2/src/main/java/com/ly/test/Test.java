package com.ly.test;

import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ly.dao.impl.AccountDao;
import com.ly.pojo.Account;

public class Test {

	
	public static void main(String[] args) {
		
		Calendar calendar=Calendar.getInstance();
		int hours=calendar.get(Calendar.HOUR_OF_DAY);//时
		int minutes=calendar.get(Calendar.MINUTE);//分
		int seconds=calendar.get(Calendar.SECOND);//秒
		System.out.println(String.format("hours:%s,minutes:%s,seconds:%s",hours,minutes,seconds));
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:app.xml");
		AccountDao da  = ac.getBean(AccountDao.class);
		
		
		List<Account> list =da.getAccounts();
		for (Account account : list) {
			System.out.println(account.toString());
		}
	}
}
