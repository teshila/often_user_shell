package com.ly.dao.impl;

import org.springframework.stereotype.Repository;

import com.ly.pojo.Tradding_Stock_K_line_Day_Data_Info;
import com.ly.pojo.Trading_Ma5VolBigMa10Vol;
@Repository
public class Tradding_Ma5VolBigMa10Vol_Dao extends BaseDaoHibernate4<Trading_Ma5VolBigMa10Vol>{
	
	
	public Double getNum(String sql,String params){
		Double num = (Double) this.getCurrentSession().createSQLQuery(sql).setParameter(0, params).uniqueResult();
		return num;
	}
	
}
