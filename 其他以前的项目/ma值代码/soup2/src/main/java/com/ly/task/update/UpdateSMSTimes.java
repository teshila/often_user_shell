package com.ly.task.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.dao.impl.SMS_SenderDao;

@Component
public class UpdateSMSTimes {

	@Autowired
	private SMS_SenderDao smsSenderDao;

	@Scheduled(cron = "0 0 1 1 * ?")
	public void task01() {
		smsSenderDao.updateSms();
	}
}
