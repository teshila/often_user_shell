package com.ly.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ly.pojo.Stock;

@Repository
public class StockDao extends BaseDaoHibernate4<Stock>{
}
