package com.ly.dao.impl;

import org.springframework.stereotype.Repository;

import com.ly.pojo.SMS_Sender;

@Repository
public class SMS_SenderDao extends BaseDaoHibernate4<SMS_Sender>{
	
	public void updateSms(){
		this.getCurrentSession().createQuery("update SMS_Sender set times = 100").executeUpdate();
		
	}
}
