package com.ly.vuecontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ly.common.util.MyStringUtil;
import com.ly.dao.Page;
import com.ly.dao.impl.ChiCangDayUpDao;
import com.ly.dao.impl.Stock_Shou_Yang_One_DayDao;
import com.ly.dao.impl.Stock_Shou_Yang_Three_DayDao;
import com.ly.pojo.Chi_Cang_Day_Up;
import com.ly.pojo.Stock_Shou_Yang_One_Day;
import com.ly.pojo.Stock_Shou_Yang_Three_Day;


@RestController
@RequestMapping("api")
public class HolderDayUpController {

	@Autowired
	private ChiCangDayUpDao stock_Shou_Yang_One_DayDao;
	
	@RequestMapping("/chiCangDayUpList")
	public Page getInfo(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		List list = stock_Shou_Yang_One_DayDao.findByPage("from Chi_Cang_Day_Up ORDER BY convert (closePrice, decimal(6, 2)) asc", pageNo, row);
		int total  = (int) stock_Shou_Yang_One_DayDao.findCount(Chi_Cang_Day_Up.class);
		
		Page<Chi_Cang_Day_Up> pages = new Page<Chi_Cang_Day_Up>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount(total);
		pages.setPageList(list);
		return pages;
	}
	
	
	@RequestMapping("/getChiCangDayUpMaxTime")
	public Map getMaxDay(String code) {
		Map ret = new HashMap();
		String time = (String)stock_Shou_Yang_One_DayDao.getCurrentSession().createQuery("select max(time) from  Chi_Cang_Day_Up").list().get(0);
		ret.put("time", time);
		return ret;
	}
	
	//https://wenku.baidu.com/view/2dc4552fa300a6c30c229fda.html
	//https://liyueling.iteye.com/blog/630200
	//https://www.cnblogs.com/zouqin/p/5319492.html
	@RequestMapping("/findChiCangDayUp")
	public Map findBlockDay(String code) {
		Map ret = new HashMap();
		if(StringUtils.isNotEmpty(code)){
			List lists = null;
			if(MyStringUtil.isInteger(code)){
				lists = stock_Shou_Yang_One_DayDao.findByPage("from Chi_Cang_Day_Up t where t.code  like '%'||?||'%' ",0,20,code);
			}else{
				lists = stock_Shou_Yang_One_DayDao.findByPage("from Chi_Cang_Day_Up t where t.pinyin  like '%'||?||'%'  ",0,20,code);
			}
			ret.put("list", lists);
		}else{
			ret.put("msg", "代码不能为空");
		}
		return ret;
	}
}
