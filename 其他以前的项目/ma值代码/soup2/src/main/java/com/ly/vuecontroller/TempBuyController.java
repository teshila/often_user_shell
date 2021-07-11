package com.ly.vuecontroller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ly.common.bean.BeanUtils;
import com.ly.dao.Page;
import com.ly.dao.impl.StockDao;
import com.ly.dao.impl.Temp_BuyDao;
import com.ly.pojo.Buy;
import com.ly.pojo.Stock;
import com.ly.pojo.Temp_Buy;

@RestController
@RequestMapping("api")
public class TempBuyController {
	private static Logger logger = Logger.getLogger(TempBuyController.class);
	@Autowired
	private Temp_BuyDao buyDao;
	
	@Autowired
	private StockDao stockDao;
	
	@RequestMapping("/getTempBuyList")
	public Page<Temp_Buy> getInfo(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		List<Temp_Buy> list = buyDao.findByPage("from Temp_Buy ORDER BY addtime desc", pageNo, row);
		int total  = (int) buyDao.findCount(Temp_Buy.class);
		
		Page<Temp_Buy> pages = new Page<Temp_Buy>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount(total);
		pages.setPageList(list);
		return pages;
	} 
	
	
	
	@RequestMapping("/editTempBuy")
	public Map addAccount(Temp_Buy accWeb){
		Map<String,String> map = new HashMap<String,String>();
		if(accWeb!=null&&accWeb.getCode()!=null&&StringUtils.isNotEmpty(accWeb.getCode())){
			try {
				String code =  accWeb.getCode();
				List<Temp_Buy> list  = buyDao.find("from Temp_Buy t where t.code = ?",code);
				if(list!=null&&list.size()>0){
					Temp_Buy ac  = list.get(0);
					BeanUtils.copyProperties(accWeb, ac);
					buyDao.update(ac);
					map.put("msg", "保存成功");
				}else{
					if(code.indexOf("000")==0||code.indexOf("001")==0){
						accWeb.setExchangeType("2");
						accWeb.setMarketType("4609");
					}else if(code.indexOf("002")==0){
						accWeb.setExchangeType("2");
						accWeb.setMarketType("4614");
					}else if(code.indexOf("300")==0){
						accWeb.setExchangeType("2");
						accWeb.setMarketType("4621");
					}else if(code.indexOf("60")==0){
						accWeb.setExchangeType("1");
						accWeb.setMarketType("4353");
					}
					Stock infos = null;
					List<Stock> stocks = stockDao.find("from Stock t where t.code = ?", code);
					if(stocks!=null&&stocks.size()>0){
						infos = stocks.get(0);
						
						accWeb.setDiQu(infos.getDiQu());
						accWeb.setLiuTongGu(infos.getLiuTongGu());
						accWeb.setHangye(infos.getHangye());
						accWeb.setPinyin(infos.getPinyin());
						accWeb.setZongGuBen(infos.getZongGuBen());
						accWeb.setShiyinglvJing(infos.getShiyinglvJing());
						
					}
					
					
					
					
					accWeb.setAddtime(new Date().toLocaleString());
					buyDao.save(accWeb);
					map.put("msg", "保存成功");
				}
			} catch (Exception e) {
				map.put("msg", "保存或更新失败，请稍后重试，失败原因==>" + e.getMessage());
			}
		}else{
			map.put("msg", "保存或更新失败，买入代码不能为空");
		}
		
		return map;
		
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/delTempBuy")
	public Map deleBuyInfo(Temp_Buy buy) {
		Map ret = new HashMap();
		if (buy!=null&&StringUtils.isNotEmpty(buy.getCode())) {
			try {
				buyDao.delete(buy);
				ret.put("msg", "保存成功");
			} catch (Exception e) {
				
				e.printStackTrace();
				ret.put("msg", "保存失败，请稍后再试,失败原因："+e.getMessage());
			}
		} else {
			ret.put("msg", "代码不能为空");
		}
		return ret;
	}
	
	
	
	
	@RequestMapping("/truncateTempBuy")
	public Map truncateBuy() {
		Map ret = new HashMap();
		try {
			buyDao.truncateTable(Temp_Buy.class);
			ret.put("msg", "删除成功");
		} catch (Exception e) {
			ret.put("msg", "删除失败, 原因==>" + e.getMessage());
		}
		return ret;
	}
}
