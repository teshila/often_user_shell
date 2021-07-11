package com.ly.task.jsoup.day.cal.computing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ly.common.util.SystemSoupConstrant;
import com.ly.dao.impl.StockDao;
import com.ly.dao.impl.Stock_K_line_Day_Data_List_Dao;
import com.ly.dao.impl.Stock_Shou_Yang_One_DayDao;
import com.ly.pojo.Stock;
import com.ly.pojo.Stock_K_line_Day_DataList;
import com.ly.pojo.Stock_Shou_Yang_One_Day;
//https://www.cnblogs.com/ios9/p/7475979.html
//https://blog.csdn.net/hwt_211/article/details/15505349
//https://blog.csdn.net/jrn1012/article/details/70255552
//https://blog.csdn.net/hexiaoli666/article/details/83109451
//@Component
public class CalStock_Shou_Yang_Day_One_DayComputing2 {
	
	private static Logger calday = Logger.getLogger("calday");
	
	
	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private Stock_K_line_Day_Data_List_Dao stock_k_line_day_data_list_dao;
	
	@Autowired
	private Stock_Shou_Yang_One_DayDao stock_Shou_Yang_One_DayDao;
	
	//@Scheduled(cron="*/10 * * * * ?")
	//@Scheduled(cron="0 02 9 ? * *")
	//@Scheduled(cron="*/10 * * * * ?")
	public void getStockInfo() {
		long startTime = System.currentTimeMillis();    //获取开始时间
		List<Stock_Shou_Yang_One_Day> entitys =  null;
		stock_Shou_Yang_One_DayDao.truncateTable(Stock_Shou_Yang_One_Day.class);
		Stock_Shou_Yang_One_Day stock_Shou_Yang_Day_One_Day = null;
		long total = stockDao.findCount(Stock.class);
		int rows = SystemSoupConstrant.rows;
		int seconds = SystemSoupConstrant.seconds;
		int step = (int) (total / rows + 1);
		
		for (int i = 1; i <= step; i++) {
			entitys = new ArrayList<Stock_Shou_Yang_One_Day>(); 
			List<Stock> list = stockDao.findByPage("from Stock", i, rows);
			
			for (int j = 0; j < list.size(); j++) {
				Stock sts = list.get(j);
				String code =sts.getCode();
				
				//Object [] parasm = new Object[]{code} ;
				List<Stock_K_line_Day_DataList> datas = stock_k_line_day_data_list_dao.findByPage("from Stock_K_line_Day_DataList  where code = ?  ORDER BY date desc",0,15, code);
				
				
				if(datas!=null&&datas.size()>3){
					
					String day1ma5SQL ="select round(sum(t.closeprice)/5,2) from (select * from stock_k_line_day_datalist t  where code=? order by date desc limit 0,5) t ";

					String day1ma10SQL ="select round(sum(t.closeprice)/10,2) from (select * from stock_k_line_day_datalist t  where code=? order by date desc limit 0,10) t";


					String day1ma20SQL ="select round(sum(t.closeprice)/20,2) from (select * from stock_k_line_day_datalist t  where code=? order by date desc limit 0,20) t";


					String day2ma5SQL ="select round(sum(t.closeprice)/5,2) from (select * from stock_k_line_day_datalist t  where code=? order by date desc limit 1,5) t";

					String day2ma10SQL ="select round(sum(t.closeprice)/10,2) from (select * from stock_k_line_day_datalist t  where code=? order by date desc limit 1,10) t";

					String day2ma20SQL ="select round(sum(t.closeprice)/20,2) from (select * from stock_k_line_day_datalist t  where code=? order by date desc limit 1,20) t";



					String day3ma5SQL ="select round(sum(t.closeprice)/5,2) from (select * from stock_k_line_day_datalist t  where code=? order by date desc limit 2,5) t";

					String day3ma10SQL ="select round(sum(t.closeprice)/10,2) from (select * from stock_k_line_day_datalist t  where code=? order by date desc limit 2,10) t";

					String day3ma20SQL ="select round(sum(t.closeprice)/20,2) from (select * from stock_k_line_day_datalist t  where code=? order by date desc limit 2,20) t";

					
					
					Double day1ma5 = stock_k_line_day_data_list_dao.getNum(day1ma5SQL,code);
					Double day1ma10 = stock_k_line_day_data_list_dao.getNum(day1ma10SQL.toString(),code);
					Double day1ma20 = stock_k_line_day_data_list_dao.getNum(day1ma20SQL.toString(),code);
					
					
					
					Double day2ma5 = stock_k_line_day_data_list_dao.getNum(day2ma5SQL,code);
					Double day2ma10 = stock_k_line_day_data_list_dao.getNum(day2ma10SQL.toString(),code);
					Double day2ma20 = stock_k_line_day_data_list_dao.getNum(day2ma20SQL.toString(),code);
					
					
					Double day3ma5 = stock_k_line_day_data_list_dao.getNum(day3ma5SQL,code);
					Double day3ma10 = stock_k_line_day_data_list_dao.getNum(day3ma10SQL.toString(),code);
					Double day3ma20 = stock_k_line_day_data_list_dao.getNum(day3ma20SQL.toString(),code);
					
					Stock_K_line_Day_DataList day1 = datas.get(0);
					Stock_K_line_Day_DataList day2 = datas.get(1);
					Stock_K_line_Day_DataList day3 = datas.get(2);
					
					
					Double day1Open  =Double.valueOf( day1.getOpenPrice());
					Double day1Close =Double.valueOf(day1.getClosePrice());
					
					
					Double day2Open  =Double.valueOf( day2.getOpenPrice());
					Double day2Close =Double.valueOf(day2.getClosePrice());
					
					Double day3Open  =Double.valueOf( day3.getOpenPrice());
					Double day3Close =Double.valueOf(day3.getClosePrice());

					double day1IsZhang = day1Close - day1Open;
					double day1IsBigThanMa5 = day1Close - day1ma5 ;
					
					double day1Ma5IsBigThanyesterMa5 = day1ma5 - day2ma5;
					double day1Ma5IsBigThanyesterMa10 = day1Close - day1ma10;
					
					double day2IsZhang = day2Close - day2Open;
					double day2IsBigThanMa5 = day2Close - day2ma5;
					double day2Ma5IsBigThanyesterMa5 = day2ma5 - day3ma5;
					
					
					double day3IsZhang = day3Close - day3Open;
					double day3IsBigThanMa5 = day3Close - day3ma5;
					double openIsBig= day1Open - day2Open;
					
					calday.debug(day1IsZhang + " =====  " + day1IsBigThanMa5 + " ===>" + sts.getCode() + "  ==> "+ sts.getName());
					
					if(day1IsZhang>0&&day1IsBigThanMa5>0&&day1Ma5IsBigThanyesterMa5>0&&day1Ma5IsBigThanyesterMa10>0&&openIsBig>0){
						
						stock_Shou_Yang_Day_One_Day = new Stock_Shou_Yang_One_Day();
						
						stock_Shou_Yang_Day_One_Day.setCode(code);
						stock_Shou_Yang_Day_One_Day.setName(sts.getName());
						stock_Shou_Yang_Day_One_Day.setPinyin(sts.getPinyin());
						
						stock_Shou_Yang_Day_One_Day.setPrevClose(day2.getClosePrice());
						stock_Shou_Yang_Day_One_Day.setExchangeType(sts.getExchangeType());
						stock_Shou_Yang_Day_One_Day.setMarketType(sts.getMarketType());
						stock_Shou_Yang_Day_One_Day.setOpenPrice(day1.getOpenPrice());
						stock_Shou_Yang_Day_One_Day.setClosePrice(day1.getClosePrice());
						stock_Shou_Yang_Day_One_Day.setMinPrice(day1.getMinPrice());
						stock_Shou_Yang_Day_One_Day.setMaxPrice(day1.getMaxPrice());
						stock_Shou_Yang_Day_One_Day.setOpenPrice(day1.getOpenPrice());
						stock_Shou_Yang_Day_One_Day.setBenRiTotalHand(day1.getTotalHand());
						stock_Shou_Yang_Day_One_Day.setZuoRiTotalHand(day2.getTotalHand());
						stock_Shou_Yang_Day_One_Day.setQianRiTotalHand(day3.getTotalHand());
						
						stock_Shou_Yang_Day_One_Day.setQianRiClose(day3.getClosePrice());
						
						stock_Shou_Yang_Day_One_Day.setHangye(sts.getHangye());
						stock_Shou_Yang_Day_One_Day.setLiuTongGu(sts.getLiuTongGu());
						stock_Shou_Yang_Day_One_Day.setDiQu(sts.getDiQu());
						stock_Shou_Yang_Day_One_Day.setZongGuBen(sts.getZongGuBen());
						stock_Shou_Yang_Day_One_Day.setShiyinglvJing(sts.getShiyinglvJing());
						stock_Shou_Yang_Day_One_Day.setTime(new Date().toLocaleString());
						stock_Shou_Yang_Day_One_Day.setPrefix(sts.getPrefix());
						
						
						entitys.add(stock_Shou_Yang_Day_One_Day);
					}
					
				}
			}
			stock_Shou_Yang_One_DayDao.batchSave(entitys);
			
		}
		long endTime = System.currentTimeMillis();    //获取结束时间
		long time = endTime - startTime;
		double hour = time/1000/60.0/60.0;
		calday.debug("系统运行完成,程序运行时间：" + (time) + "毫秒 , 共 " + (time) / 1000 / 60.0 + "分,共" + hour + "小时"); // 输出程序运行时间
	}
}
