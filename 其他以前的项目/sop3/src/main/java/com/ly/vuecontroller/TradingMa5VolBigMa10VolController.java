package com.ly.vuecontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ly.SystemConfig;
import com.ly.common.util.MyStringUtil;
import com.ly.dao.Page;
import com.ly.dao.impl.Tradding_Ma5VolBigMa10Vol_Dao;
import com.ly.dao.impl.Trading_Stock_Yang_Of_Two_Day_UpDao;
import com.ly.pojo.Tradding_Ma5Vol_Big_Ma10Vol;
import com.ly.pojo.Tradding_Stock_Yang_Of_One_Day_Up;
import com.ly.pojo.Tradding_Stock_Yang_Of_Two_Day_Up;


@RestController
@RequestMapping("api")
public class TradingMa5VolBigMa10VolController {

	@Autowired
	private Tradding_Ma5VolBigMa10Vol_Dao stock_Shou_Yang_One_DayDao;
	
	@RequestMapping("/traddingMa5VolBigMa10VolList")
	public Page getInfo(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		
		//List list = stock_Shou_Yang_One_DayDao.findByPage("from Trading_Stock_Yang_Of_Two_Day_Up ORDER BY convert (closePrice, decimal(6, 2)) asc", pageNo, row);
		List list = stock_Shou_Yang_One_DayDao.findByPage("from Trading_Ma5VolBigMa10Vol "+SystemConfig.condintionForVol, pageNo, row);
		List totalList  =  stock_Shou_Yang_One_DayDao.find("select count(1) from  Trading_Ma5VolBigMa10Vol "+SystemConfig.condintionForVol);
		long total = (long)totalList.get(0);
		
		Page<Tradding_Ma5Vol_Big_Ma10Vol> pages = new Page<Tradding_Ma5Vol_Big_Ma10Vol>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount((int)total);
		pages.setPageList(list);
		return pages;
	}
	
	
	@RequestMapping("/getTraddingMa5VolBigMa10VolMaxTime")
	public Map getMaxDay(String code) {
		Map ret = new HashMap();
		String time = (String)stock_Shou_Yang_One_DayDao.getCurrentSession().createQuery("select max(time) from  Trading_Ma5VolBigMa10Vol").list().get(0);
		ret.put("time", time);
		return ret;
	}
	
	//https://wenku.baidu.com/view/2dc4552fa300a6c30c229fda.html
	//https://liyueling.iteye.com/blog/630200
	//https://www.cnblogs.com/zouqin/p/5319492.html
	@RequestMapping("/findTraddingMa5VolBigMa10Vol")
	public Page<Tradding_Ma5Vol_Big_Ma10Vol> findBlockDay(String code) {

		Map ret = new HashMap();
		List list = null;
		List countList = null;
		long total = 0;
		if(StringUtils.isNotEmpty(code)){
			if(MyStringUtil.isInteger(code)){
				list = stock_Shou_Yang_One_DayDao.findByPage("from Trading_Ma5VolBigMa10Vol t where t.code  like '%'||?||'%' ",0,20,code);
				countList  =  stock_Shou_Yang_One_DayDao.find("select count(1) from  Trading_Ma5VolBigMa10Vol t where t.code  like '%'||?||'%' ",code);
				total = (long)countList.get(0);
			}else{
				list = stock_Shou_Yang_One_DayDao.findByPage("from Trading_Ma5VolBigMa10Vol t where t.pinyin  like '%'||?||'%' ",0,20,code);
				countList  =  stock_Shou_Yang_One_DayDao.find("select count(1) from  Trading_Ma5VolBigMa10Vol t where t.pinyin like '%'||?||'%' ",code);
				total = (long)countList.get(0);
				
			}
		}
		Page<Tradding_Ma5Vol_Big_Ma10Vol> pages = new Page<Tradding_Ma5Vol_Big_Ma10Vol>();
		pages.setTotalCount((int)total);
		pages.setPageList(list);
		return pages;
	}
}
