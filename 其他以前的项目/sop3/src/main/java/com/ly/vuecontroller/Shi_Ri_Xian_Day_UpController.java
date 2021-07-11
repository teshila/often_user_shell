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
import com.ly.dao.impl.Shi_Ri_Jun_Xian_UpDao;
import com.ly.pojo.Stock_Shi_Ri_Jun_Xian_Up;


@RestController
@RequestMapping("api")
public class Shi_Ri_Xian_Day_UpController {

	@Autowired
	private Shi_Ri_Jun_Xian_UpDao stock_Shou_Yang_Only_Week_Shou_YangDao;
	
	@RequestMapping("/shiRi")
	public Page<Stock_Shi_Ri_Jun_Xian_Up> getInfo(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		//List list = stock_Shou_Yang_Only_Week_Shou_YangDao.findByPage("from Stock_Shou_Yang_Only_Week_Shou_Yang ORDER BY convert (closePrice, decimal(6, 2)) asc", pageNo, row);
		//List list = stock_Shou_Yang_Only_Week_Shou_YangDao.findByPage("from Stock_Shi_Ri_Jun_Xian_Up "+SystemConfig.myAppendStr, pageNo, row);
		List list = stock_Shou_Yang_Only_Week_Shou_YangDao.findByPage("from Stock_Shi_Ri_Jun_Xian_Up "+SystemConfig.myAppendStrForShiRi, pageNo, row);
		//int total  = (int) stock_Shou_Yang_Only_Week_Shou_YangDao.findCount(Stock_Shou_Yang_Only_Week_Shou_Yang.class);
		
		//List totalList  =  stock_Shou_Yang_Only_Week_Shou_YangDao.find("select count(1) from  Stock_Shi_Ri_Jun_Xian_Up "+SystemConfig.condition);
		List totalList  =  stock_Shou_Yang_Only_Week_Shou_YangDao.find("select count(1) from  Stock_Shi_Ri_Jun_Xian_Up "+SystemConfig.myAppendStrForShiRi);
		long total = (long)totalList.get(0);
		
		Page<Stock_Shi_Ri_Jun_Xian_Up> pages = new Page<Stock_Shi_Ri_Jun_Xian_Up>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount((int)total);
		pages.setPageList(list);
		return pages;
	}
	
	
	@RequestMapping("/getShiRiMaxTime")
	public Map getMaxDay(String code) {
		Map ret = new HashMap();
		String time = (String)stock_Shou_Yang_Only_Week_Shou_YangDao.getCurrentSession().createQuery("select max(time) from  Stock_Shi_Ri_Jun_Xian_Up").list().get(0);
		ret.put("time", time);
		return ret;
	}
	
	//https://wenku.baidu.com/view/2dc4552fa300a6c30c229fda.html
	//https://liyueling.iteye.com/blog/630200
	//https://www.cnblogs.com/zouqin/p/5319492.html
	@RequestMapping("/finShiRi")
	public Page<Stock_Shi_Ri_Jun_Xian_Up> findBlockDay(String code){
		
		Map ret = new HashMap();
		List list = null;
		List countList = null;
		long total = 0;
		if(StringUtils.isNotEmpty(code)){
			if(MyStringUtil.isInteger(code)){
				list = stock_Shou_Yang_Only_Week_Shou_YangDao.findByPage("from Stock_Shi_Ri_Jun_Xian_Up t where t.code  like '%'||?||'%' ",0,20,code);
				countList  =  stock_Shou_Yang_Only_Week_Shou_YangDao.find("select count(1) from  Stock_Shi_Ri_Jun_Xian_Up t where t.code  like '%'||?||'%' ",code);
				total = (long)countList.get(0);
			}else{
				list = stock_Shou_Yang_Only_Week_Shou_YangDao.findByPage("from Stock_Shi_Ri_Jun_Xian_Up t where t.pinyin  like '%'||?||'%' ",0,20,code);
				countList  =  stock_Shou_Yang_Only_Week_Shou_YangDao.find("select count(1) from  Stock_Shi_Ri_Jun_Xian_Up t where t.pinyin like '%'||?||'%' ",code);
				total = (long)countList.get(0);
				
			}
		}
		Page<Stock_Shi_Ri_Jun_Xian_Up> pages = new Page<Stock_Shi_Ri_Jun_Xian_Up>();
		pages.setTotalCount((int)total);
		pages.setPageList(list);
		return pages;
	}
}
