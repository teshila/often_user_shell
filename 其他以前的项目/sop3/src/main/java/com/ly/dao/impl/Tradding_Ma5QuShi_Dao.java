package com.ly.dao.impl;

import org.springframework.stereotype.Repository;

import com.ly.pojo.Tradding_Stock_K_line_Day_DataList;
import com.ly.pojo.Tradding_Ma5Vol_QuShi;
@Repository
public class Tradding_Ma5QuShi_Dao extends BaseDaoHibernate4<Tradding_Ma5Vol_QuShi>{
	
	
	public Double getNum(String sql,String params){
		Double num = (Double) this.getCurrentSession().createSQLQuery(sql).setParameter(0, params).uniqueResult();
		return num;
	}
	
}
