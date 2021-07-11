package com.ly.dao.impl;

import org.springframework.stereotype.Repository;

import com.ly.pojo.Special_Setting;

@Repository
public class SpecialSettingDao extends BaseDaoHibernate4<Special_Setting>{
	
	public void clearDefault(){
		this.getCurrentSession().createQuery("update Special_Setting set isdefault = 0").executeUpdate();
	}

	public void updateBuyDefault(String percent_value){
		this.getCurrentSession().createQuery("update Special_Setting set isdefault = 1 where percent_value = ? and  type = 1").setParameter(0, percent_value).executeUpdate();
	}
	
/*	public void updateSellDefault(String percent_value){
		this.getCurrentSession().createQuery("update Special_Setting set isdefault = 1 where percent_value = ? and  type = 2").setParameter(0, percent_value).executeUpdate();
	}
*/	
}
