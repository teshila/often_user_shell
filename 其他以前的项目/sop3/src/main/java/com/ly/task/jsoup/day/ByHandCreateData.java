package com.ly.task.jsoup.day;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.task.jsoup.day.cal.CalDayLineUpAsync;
@Component
public class ByHandCreateData {

	@Autowired
	private CalDayLineUpAsync caldaylineup;
	
	
	@Scheduled(cron = "10 53 11 ? * *")
	public void create(){
		caldaylineup.task01();
		caldaylineup.task02();
		caldaylineup.task03();
		caldaylineup.task04();
		caldaylineup.task05();
		caldaylineup.task06();
		caldaylineup.task07();
		caldaylineup.task08();
		caldaylineup.task09();
	}
}
