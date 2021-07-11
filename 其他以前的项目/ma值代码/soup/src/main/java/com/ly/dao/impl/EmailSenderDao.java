package com.ly.dao.impl;

import org.springframework.stereotype.Repository;

import com.ly.pojo.Email_Sender;
import com.ly.pojo.Stock_Shou_Yang_Two_Day;

@Repository
public class EmailSenderDao extends BaseDaoHibernate4<Email_Sender>{

	
	public void updateDefault(String address){
		this.getCurrentSession().createQuery("update Email_Sender set flag = 0").executeUpdate();
		
		this.getCurrentSession().createQuery("update Email_Sender set flag = 1 where address = ? ").setParameter(0, address).executeUpdate();
	}
}
