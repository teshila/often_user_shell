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
import com.ly.dao.impl.Stock_Shou_Yang_Three_DayDao;
import com.ly.pojo.Stock_Shou_Yang_Three_Day;


@RestController
@RequestMapping("api")
public class StockShouYangThreeDayController {

	@Autowired
	private Stock_Shou_Yang_Three_DayDao stock_Shou_Yang_One_DayDao;
	
	@RequestMapping("/shouYangThreeList")
	public Page getInfo(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		
		//List list = stock_Shou_Yang_One_DayDao.findByPage("from Stock_Shou_Yang_Three_Day ORDER BY convert (closePrice, decimal(6, 2)) asc", pageNo, row);
		List list = stock_Shou_Yang_One_DayDao.findByPage("from Stock_Shou_Yang_Three_Day"+SystemConfig.myAppendStr, pageNo, row);
		List totalList  =  stock_Shou_Yang_One_DayDao.find("select count(1) from  Stock_Shou_Yang_Three_Day "+SystemConfig.condition);
		
		long total = (long)totalList.get(0);
		
		Page<Stock_Shou_Yang_Three_Day> pages = new Page<Stock_Shou_Yang_Three_Day>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount((int)total);
		pages.setPageList(list);
		return pages;
	}
	
	
	@RequestMapping("/getShouYangThreeMaxTime")
	public Map getMaxDay(String code) {
		Map ret = new HashMap();
		String time = (String)stock_Shou_Yang_One_DayDao.getCurrentSession().createQuery("select max(time) from  Stock_Shou_Yang_Three_Day").list().get(0);
		ret.put("time", time);
		return ret;
	}
	
	//https://wenku.baidu.com/view/2dc4552fa300a6c30c229fda.html
	//https://liyueling.iteye.com/blog/630200
	//https://www.cnblogs.com/zouqin/p/5319492.html
	@RequestMapping("/findShouYangThree")
	public Page<Stock_Shou_Yang_Three_Day> findBlockDay(String code) {
		List list = null;
		List countList = null;
		long total = 0;
		if(StringUtils.isNotEmpty(code)){
			if(MyStringUtil.isInteger(code)){
				list = stock_Shou_Yang_One_DayDao.findByPage("from Stock_Shou_Yang_Three_Day t where t.code  like '%'||?||'%' ",0,20,code);
				countList  =  stock_Shou_Yang_One_DayDao.find("select count(1) from  Stock_Shou_Yang_Three_Day t where t.code  like '%'||?||'%' ",code);
				total = (long)countList.get(0);
			}else{
				list = stock_Shou_Yang_One_DayDao.findByPage("from Stock_Shou_Yang_Three_Day t where t.pinyin  like '%'||?||'%' ",0,20,code);
				countList  =  stock_Shou_Yang_One_DayDao.find("select count(1) from  Stock_Shou_Yang_Three_Day t where t.pinyin like '%'||?||'%' ",code);
				total = (long)countList.get(0);
				
			}
		}
		Page<Stock_Shou_Yang_Three_Day> pages = new Page<Stock_Shou_Yang_Three_Day>();
		pages.setTotalCount((int)total);
		pages.setPageList(list);
		return pages;
	}
}
