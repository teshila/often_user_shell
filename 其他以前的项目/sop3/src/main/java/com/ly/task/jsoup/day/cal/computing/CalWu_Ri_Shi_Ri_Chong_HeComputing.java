package com.ly.task.jsoup.day.cal.computing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.common.util.SystemSoupConstrant;
import com.ly.common.util.UUIDUtils;
import com.ly.dao.impl.StockDao;
import com.ly.dao.impl.Stock_K_line_Day_Data_List_Dao;
import com.ly.dao.impl.Stock_Wu_Ri_Shi_Ri_Chong_HeDao;
import com.ly.pojo.Stock;
import com.ly.pojo.Stock_K_line_Day_DataList;
import com.ly.pojo.Stock_Wu_Ri_Shi_Ri_ChongHe;
//https://www.cnblogs.com/ios9/p/7475979.html
//https://blog.csdn.net/hwt_211/article/details/15505349
//https://blog.csdn.net/jrn1012/article/details/70255552
//https://blog.csdn.net/hexiaoli666/article/details/83109451
@Component
public class CalWu_Ri_Shi_Ri_Chong_HeComputing {
	
	private static Logger calday = Logger.getLogger("twoday");
	
	
	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private Stock_K_line_Day_Data_List_Dao stock_k_line_day_data_list_dao;
	
	@Autowired
	private Stock_Wu_Ri_Shi_Ri_Chong_HeDao stock_Shou_Yang_One_DayDao;
	
	//@Scheduled(cron="*/10 * * * * ?")
	//@Scheduled(cron="0 02 9 ? * *")
	//@Scheduled(cron="*/10 * * * * ?")
	//@Scheduled(cron="0 23 17 ? * *")
	//@Scheduled(cron="0 41 12 ? * *")
	public void getStockInfo() {
		long startTime = System.currentTimeMillis();    //获取开始时间
		List<Stock_Wu_Ri_Shi_Ri_ChongHe> entitys =  null;
		stock_Shou_Yang_One_DayDao.truncateTable(Stock_Wu_Ri_Shi_Ri_ChongHe.class);
		Stock_Wu_Ri_Shi_Ri_ChongHe stock_Shou_Yang_Day_One_Day = null;
		long total = stockDao.findCount(Stock.class);
		int rows = SystemSoupConstrant.rows;
		int seconds = SystemSoupConstrant.seconds;
		int step = (int) (total / rows + 1);
		
		for (int i = 1; i <= step; i++) {
			entitys = new ArrayList<Stock_Wu_Ri_Shi_Ri_ChongHe>(); 
			List<Stock> list = stockDao.findByPage("from Stock", i, rows);
			
			for (int j = 0; j < list.size(); j++) {
				Stock sts = list.get(j);
				String code =sts.getCode();
				
				List<Stock_K_line_Day_DataList> datas = stock_k_line_day_data_list_dao.findByPage("from Stock_K_line_Day_DataList  where code = ?  ORDER BY date desc",0,30, code);
				
				
				if(datas!=null&&datas.size()>=20){
					
					for(int arr =0;arr<15;arr++){
						
						
						Stock_K_line_Day_DataList day1 = datas.get(arr);
						Double day1Open  =Double.valueOf( day1.getOpenPrice());
						Double day1Close =Double.valueOf(day1.getClosePrice());
						Double day1Ma5 = Double.valueOf(day1.getMa5());
						Double day1Ma10 = Double.valueOf(day1.getMa10());
						
						Stock_K_line_Day_DataList day2 = datas.get(arr+1);
						Double day2Open  =Double.valueOf( day2.getOpenPrice());
						Double day2Close =Double.valueOf(day2.getClosePrice());
						Double day2Ma5 = Double.valueOf(day2.getMa5());
						Double day2Ma10 = Double.valueOf(day2.getMa10());
						
						Stock_K_line_Day_DataList day3 = datas.get(arr+2);
						Double day3Open  =Double.valueOf( day3.getOpenPrice());
						Double day3Close =Double.valueOf(day3.getClosePrice());
						Double day3Ma5 = Double.valueOf(day3.getMa5());
						Double day3Ma10 = Double.valueOf(day3.getMa10());
						
						double day1IsZhang = day1Close - day1Open;
						double day1IsBigThanMa5 = day1Close - day1Ma5 ;
						double day1IsBigThanMa10 = day1Close - day1Ma10 ;
						double day1Ma5IsBigThanyesterMa5 = day1Ma5 - day2Ma5;
						
						double day2IsZhang = day2Close - day2Open;
						double day2IsBigThanMa5 = day2Close - day2Ma5;
						
						
						double wuRiJunXianUp = day1Ma5 - day2Ma5;
						double shiRiJunXianUp = day1Ma10 - day2Ma10;
						double wuRiIsBigThanShiRi = day1Ma5 - day1Ma10;
						
						double openIsBig= day1Open - day2Open;
						double flag = day1Ma5 - day1Ma10;
						double ma10IsZhang = day1Ma10 - day2Ma10;
						if(flag==0&&ma10IsZhang>=0){
						stock_Shou_Yang_Day_One_Day = new Stock_Wu_Ri_Shi_Ri_ChongHe();
						stock_Shou_Yang_Day_One_Day.setId(UUIDUtils.getUUID());
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
						//stock_Shou_Yang_Day_One_Day.setTime(new Date().toLocaleString());
						stock_Shou_Yang_Day_One_Day.setTime(day1.getDate());
						
						stock_Shou_Yang_Day_One_Day.setPrefix(sts.getPrefix());
						entitys.add(stock_Shou_Yang_Day_One_Day);
					  }
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
