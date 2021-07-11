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
import com.ly.dao.impl.BuyDao;
import com.ly.dao.impl.SellDao;
import com.ly.dao.impl.StockDao;
import com.ly.pojo.Buy;
import com.ly.pojo.Sell;
import com.ly.pojo.Stock;

//http://www.360doc.com/content/11/0606/11/144659_121989553.shtml
@RestController
@RequestMapping("pro")
public class WeiTuoController {
	private static Logger logger = Logger.getLogger(WeiTuoController.class);
	@Autowired
	private BuyDao buyDao;
	@Autowired
	private SellDao sellDao;
	
	@Autowired
	private StockDao stockDao;
	
	@RequestMapping("/getBuyList")
	public Page<Buy> getInfo(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		List<Buy> list = buyDao.findByPage("from Buy ORDER BY addtime desc", pageNo, row);
		int total  = (int) buyDao.findCount(Buy.class);
		
		Page<Buy> pages = new Page<Buy>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount(total);
		pages.setPageList(list);
		return pages;
	} 
	
	
	
	@RequestMapping("/editBuy")
	public Map addAccount(Buy accWeb){
		Map<String,String> map = new HashMap<String,String>();
		if(accWeb!=null&&accWeb.getCode()!=null&&StringUtils.isNotEmpty(accWeb.getCode())){
			try {
				String code =  accWeb.getCode();
				List<Buy> list  = buyDao.find("from Buy t where t.code = ?",code);
				if(list!=null&&list.size()>0){
					Buy ac  = list.get(0);
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
						accWeb.setPrefix(infos.getPrefix());
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
	
	
	@RequestMapping("/editSell")
	public Map editSell(Sell accWeb){
		Map<String,String> map = new HashMap<String,String>();
		if(accWeb!=null&&accWeb.getCode()!=null&&StringUtils.isNotEmpty(accWeb.getCode())){
			try {
				String code =  accWeb.getCode();
				List<Sell> list  = sellDao.find("from Sell t where t.code = ?",code);
				if(list!=null&&list.size()>0){
					Sell ac  = list.get(0);
					BeanUtils.copyProperties(accWeb, ac);
					sellDao.update(ac);
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
						accWeb.setPrefix(infos.getPrefix());
						//accWeb.setIsAutoSell("1");
					}
					
					
					accWeb.setAddtime(new Date().toLocaleString());
					sellDao.save(accWeb);
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
	@RequestMapping("/delBuy")
	public Map deleBuyInfo(Buy buy) {
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
	
	
	
	@RequestMapping("/delSell")
	public Map delSellInfo(Sell sell) {
		Map ret = new HashMap();
		if (sell!=null&&StringUtils.isNotEmpty(sell.getCode())) {
			try {
				sellDao.delete(sell);
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
	
	
	@RequestMapping("/sellList")
	public Page<Sell> getSell(String pageNum, String pageSize) {
		Page<Sell> pages = new Page<Sell>();
		Map map = new HashMap();
		int pageNo = 1;
		int size = 10;
		if (pageNum != null && !pageNum.equals("0")) {
			pageNo = Integer.valueOf(pageNum);
		}
		if (pageSize != null && !pageSize.equals("0")) {
			size = Integer.valueOf(pageSize);
		}
		int firstIndex = (pageNo - 1) * size;
		// 到第几条数据结束
		int lastIndex = pageNo * size;
		
		
		
		List list = sellDao.findByPage("from Sell", pageNo, size);
		int total = (int) sellDao.findCount(Sell.class);
		pages.setPageNo(firstIndex / size + 1);
		pages.setStart(firstIndex);
		pages.setTotalCount(total);
		pages.setPageList(list);
		return pages;
	}
	
	
	
	@RequestMapping("/truncateBuy")
	public Map truncateBuy() {
		Map ret = new HashMap();
		try {
			buyDao.truncateTable(Buy.class);
			ret.put("msg", "删除成功");
		} catch (Exception e) {
			ret.put("msg", "删除失败, 原因==>" + e.getMessage());
		}
		return ret;
	}
	
	
	@RequestMapping("/truncateSell")
	public Map truncateSell() {
		Map ret = new HashMap();
		try {
			sellDao.truncateTable(Sell.class);
			ret.put("msg", "删除成功");
		} catch (Exception e) {
			ret.put("msg", "删除失败, 原因==>" + e.getMessage());
		}
		return ret;
	}
}
