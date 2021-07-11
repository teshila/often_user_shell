package com.ly.task.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.dao.impl.EmailRecevierLogDao;
import com.ly.dao.impl.SMSSendLogDao;
import com.ly.pojo.Email_Recevier_Log;
import com.ly.pojo.SMS_Recevier_Log;


@Component
public class UpdateSysLogs {

	@Autowired
	private EmailRecevierLogDao emailSendLogDao;
	@Autowired
	private SMSSendLogDao smsSendLogDao;
	
	//
	//@Scheduled(cron = "0/10 * * * * ?")
	//@Scheduled(cron = "0 3 5,23 * * ?")
	@Scheduled(cron = "0 3 23 * * ?")
	public void task(){
		
		emailSendLogDao.truncateTable(Email_Recevier_Log.class);;
		
		smsSendLogDao.truncateTable(SMS_Recevier_Log.class);
		
	}
}
