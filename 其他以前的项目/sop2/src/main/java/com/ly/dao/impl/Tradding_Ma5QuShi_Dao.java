package com.ly.dao.impl;

import org.springframework.stereotype.Repository;

import com.ly.pojo.Tradding_Stock_K_line_Day_Data_Info;
import com.ly.pojo.Trading_Ma5VolQuShi;
@Repository
public class Tradding_Ma5QuShi_Dao extends BaseDaoHibernate4<Trading_Ma5VolQuShi>{
	
	
	public Double getNum(String sql,String params){
		Double num = (Double) this.getCurrentSession().createSQLQuery(sql).setParameter(0, params).uniqueResult();
		return num;
	}
	
}
