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
import com.ly.dao.impl.Stock_Ma5VolBigMa10Dao;
import com.ly.pojo.Stock_Ma5Vol_Big_Ma10Vol;


@RestController
@RequestMapping("api")
public class Ma5VolBigMa10VolController {

	@Autowired
	private Stock_Ma5VolBigMa10Dao stock_Shou_Yang_One_DayDao;
	
	@RequestMapping("/ma5BigVol")
	public Page<Stock_Ma5Vol_Big_Ma10Vol> getInfo(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		
		//List list = stock_Shou_Yang_One_DayDao.findByPage("from Stock_Shou_Yang_Day_And_Week_Shou_Yang ORDER BY convert (closePrice, decimal(6, 2)) asc", pageNo, row);
		List list = stock_Shou_Yang_One_DayDao.findByPage("from Ma5VolBigMa10Vol "+SystemConfig.condintionForVol, pageNo, row);
		//int total  = (int) stock_Shou_Yang_One_DayDao.findCount(Stock_Shou_Yang_Day_And_Week_Shou_Yang.class);
		
		List totalList  =  stock_Shou_Yang_One_DayDao.find("select count(1) from  Ma5VolBigMa10Vol "+SystemConfig.condintionForVol);
		long total = (long)totalList.get(0);
		
		Page<Stock_Ma5Vol_Big_Ma10Vol> pages = new Page<Stock_Ma5Vol_Big_Ma10Vol>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount((int)total);
		pages.setPageList(list);
		return pages;
	}
	
	
	@RequestMapping("/getMa5BigVolMaxTime")
	public Map getMaxDay(String code) {
		Map ret = new HashMap();
		String time = (String)stock_Shou_Yang_One_DayDao.getCurrentSession().createQuery("select max(time) from  Ma5VolBigMa10Vol").list().get(0);
		ret.put("time", time);
		return ret;
	}
	
	//https://wenku.baidu.com/view/2dc4552fa300a6c30c229fda.html
	//https://liyueling.iteye.com/blog/630200
	//https://www.cnblogs.com/zouqin/p/5319492.html
	@RequestMapping("/findMa5BigVol")
	public Page<Stock_Ma5Vol_Big_Ma10Vol> findBlockDay(String code) {

		Map ret = new HashMap();
		List list = null;
		List countList = null;
		long total = 0;
		if(StringUtils.isNotEmpty(code)){
			if(MyStringUtil.isInteger(code)){
				list = stock_Shou_Yang_One_DayDao.findByPage("from Ma5VolBigMa10Vol t where t.code  like '%'||?||'%' ",0,20,code);
				countList  =  stock_Shou_Yang_One_DayDao.find("select count(1) from  Ma5VolBigMa10Vol t where t.code  like '%'||?||'%' ",code);
				total = (long)countList.get(0);
			}else{
				list = stock_Shou_Yang_One_DayDao.findByPage("from Ma5VolBigMa10Vol t where t.pinyin  like '%'||?||'%' ",0,20,code);
				countList  =  stock_Shou_Yang_One_DayDao.find("select count(1) from  Ma5VolBigMa10Vol t where t.pinyin like '%'||?||'%' ",code);
				total = (long)countList.get(0);
				
			}
		}
		Page<Stock_Ma5Vol_Big_Ma10Vol> pages = new Page<Stock_Ma5Vol_Big_Ma10Vol>();
		pages.setTotalCount((int)total);
		pages.setPageList(list);
		return pages;
	}
}
