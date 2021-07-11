package com.ly.dao.impl;

import org.springframework.stereotype.Repository;

import com.ly.pojo.Tradding_Stock_K_line_Day_Data_Info;
@Repository
public class Tradding_Stock_K_line_Day_Data_Info_Dao extends BaseDaoHibernate4<Tradding_Stock_K_line_Day_Data_Info>{
	
	
	public Double getNum(String sql,String params){
		Double num = (Double) this.getCurrentSession().createSQLQuery(sql).setParameter(0, params).uniqueResult();
		return num;
	}
	
}
