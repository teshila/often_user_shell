package com.ly.task.jsoup.week.cal.computing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ly.common.util.SystemSoupConstrant;
import com.ly.dao.impl.StockDao;
import com.ly.dao.impl.Stock_K_line_Day_Data_List_Dao;
import com.ly.dao.impl.Stock_K_line_Week_Data_List_Dao;
import com.ly.dao.impl.WeekLineMa10DaYuMa20Dao;
import com.ly.pojo.Stock;
import com.ly.pojo.Stock_K_line_Day_DataList;
import com.ly.pojo.Stock_K_line_Week_DataList;
import com.ly.pojo.Stock_Week_Line_Ma10_Big_Ma20;

//短线算法
@Component
public class WeekLineMa10DaYuMa20Computing {
	
	private static Logger calday = Logger.getLogger("week");
	
	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private Stock_K_line_Week_Data_List_Dao stock_k_line_week_data_list_dao;
	
	@Autowired
	private WeekLineMa10DaYuMa20Dao weekLineMa10DaYuMa20Dao;
	
	@Autowired
	private Stock_K_line_Day_Data_List_Dao stock_k_line_day_data_list_dao;
	
	
	
	//@Scheduled(cron="*/10 * * * * ?")
	//@Scheduled(cron="0 02 9 ? * *")
	//@Scheduled(cron="0 34 2 ? * *")
	//@Scheduled(cron = "40 05 16 ? * MON-FRI")
	//@Scheduled(cron="30 16 20 ? * *")
	public void getStockInfo() {
		long startTime = System.currentTimeMillis();    //获取开始时间
		List<Stock_Week_Line_Ma10_Big_Ma20> entitys =  null;
		weekLineMa10DaYuMa20Dao.truncateTable(Stock_Week_Line_Ma10_Big_Ma20.class);
		Stock_Week_Line_Ma10_Big_Ma20 stock_Shou_Yang_Day_One_Day = null;
		long total = stockDao.findCount(Stock.class);
		int rows = SystemSoupConstrant.rows;
		int seconds = SystemSoupConstrant.seconds;
		int step = (int) (total / rows + 1);
		
		for (int i = 1; i <= step; i++) {
			entitys = new ArrayList<Stock_Week_Line_Ma10_Big_Ma20>(); 
			List<Stock> list = stockDao.findByPage("from Stock", i, rows);
			
			for (int j = 0; j < list.size(); j++) {
				Stock sts = list.get(j);
				String code =sts.getCode();
				
				//Object [] parasm = new Object[]{code} ;
				List<Stock_K_line_Week_DataList> datas = stock_k_line_week_data_list_dao.findByPage("from Stock_K_line_Week_DataList  where code = ?  ORDER BY date desc",0,15, code);
				List<Stock_K_line_Day_DataList> datas2 = stock_k_line_day_data_list_dao.findByPage("from Stock_K_line_Day_DataList  where code = ?  ORDER BY date desc",0,15, code);
				
				try{
					if(datas!=null&&datas.size()>=20){
						Stock_K_line_Week_DataList day1 = datas.get(0);
						Stock_K_line_Week_DataList day2 = datas.get(1);
						Stock_K_line_Week_DataList day3 = datas.get(2);
						
						
						Stock_K_line_Day_DataList day01 = datas2.get(0);
						Stock_K_line_Day_DataList day02 = datas2.get(1);
						Stock_K_line_Day_DataList day03 = datas2.get(2);
						
						
						Double day02Open  =Double.valueOf( day02.getOpenPrice());
						Double day02Close =Double.valueOf(day02.getClosePrice());
						Double day02Ma5 = Double.valueOf(day02.getMa5());
						
						Double day03Open  =Double.valueOf( day03.getOpenPrice());
						Double day03Close =Double.valueOf(day03.getClosePrice());
						Double day03Ma5 = Double.valueOf(day03.getMa5());
						
						
						Double day1Open  =Double.valueOf( day1.getOpenPrice());
						Double day1Close =Double.valueOf(day1.getClosePrice());
						Double day1Ma5 = Double.valueOf(day1.getMa5());
						Double day1Ma10 = Double.valueOf(day1.getMa10());
						Double day1Ma20 = Double.valueOf(day1.getMa20());
						
						
						
						Double day2Open  =Double.valueOf( day2.getOpenPrice());
						Double day2Close =Double.valueOf(day2.getClosePrice());
						Double day2Ma5 = Double.valueOf(day2.getMa5());
						Double day2Ma10 = Double.valueOf(day2.getMa10());
						Double day2Ma20 = Double.valueOf(day2.getMa20());
						
						Double day3Open  =Double.valueOf( day3.getOpenPrice());
						Double day3Close =Double.valueOf(day3.getClosePrice());
						Double day3Ma5 = Double.valueOf(day3.getMa5());
						Double day3Ma20 = Double.valueOf(day3.getMa20());
						
						
						double day1IsZhang = day1Close - day1Open;
						
						
						double day1IsBigThanMa5 = day1Close - day1Ma5 ;
						double day1Ma5IsBigThanyesterMa5 = day1Ma5 - day2Ma5;
						
						double day2IsZhang = day2Close - day2Open;
						double day2IsBigThanMa5 = day2Close - day2Ma5;
						double day2Ma5IsBigThanyesterMa5 = day2Ma5 - day3Ma5;
						
						
						double day3IsZhang = day3Close - day3Open;
						double day3IsBigThanMa5 = day3Close - day3Ma5;
						
						
						double zhouIsUp =  day1Close - day2Close;
						 
						double weekMa10DaYuMa20 = day1Ma10 -day1Ma20;
						
						double openIsBig= day1Open - day2Open;
						
					//	if(day1IsZhang>0&&day1IsBigThanMa5>0&&day1Ma5IsBigThanyesterMa5>0&&day2IsZhang>0&&day2IsBigThanMa5>0&&day2Ma5IsBigThanyesterMa5>0){
						if(day1IsZhang>0&&day2IsZhang>0&&zhouIsUp>0&&weekMa10DaYuMa20>=0&&openIsBig>0){
							
							calday.debug(day2IsZhang + " =====  " + day2IsBigThanMa5 + " ===>" + sts.getCode() + "  ==> "+ sts.getName());
							
							stock_Shou_Yang_Day_One_Day = new Stock_Week_Line_Ma10_Big_Ma20();
							
							stock_Shou_Yang_Day_One_Day.setCode(code);
							stock_Shou_Yang_Day_One_Day.setName(sts.getName());
							stock_Shou_Yang_Day_One_Day.setPinyin(sts.getPinyin());
							
							stock_Shou_Yang_Day_One_Day.setPrevClose(day02.getClosePrice());
							stock_Shou_Yang_Day_One_Day.setExchangeType(sts.getExchangeType());
							stock_Shou_Yang_Day_One_Day.setMarketType(sts.getMarketType());
							stock_Shou_Yang_Day_One_Day.setOpenPrice(day01.getOpenPrice());
							stock_Shou_Yang_Day_One_Day.setClosePrice(day01.getClosePrice());
							stock_Shou_Yang_Day_One_Day.setMinPrice(day01.getMinPrice());
							stock_Shou_Yang_Day_One_Day.setMaxPrice(day01.getMaxPrice());
							stock_Shou_Yang_Day_One_Day.setOpenPrice(day01.getOpenPrice());
							stock_Shou_Yang_Day_One_Day.setBenRiTotalHand(day01.getTotalHand());
							stock_Shou_Yang_Day_One_Day.setZuoRiTotalHand(day02.getTotalHand());
							stock_Shou_Yang_Day_One_Day.setQianRiTotalHand(day03.getTotalHand());
							
							stock_Shou_Yang_Day_One_Day.setQianRiClose(day3.getClosePrice());
							
							stock_Shou_Yang_Day_One_Day.setBenZhouTotalHand(day1.getTotalHand());
							stock_Shou_Yang_Day_One_Day.setShangZhouTotalHand(day2.getTotalHand());
							stock_Shou_Yang_Day_One_Day.setQianZhouTotalHand(day2.getTotalHand());
							
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
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			weekLineMa10DaYuMa20Dao.batchSave(entitys);
			
		}
		long endTime = System.currentTimeMillis();    //获取结束时间
		long time = endTime - startTime;
		double hour = time/1000/60.0/60.0;
		calday.debug("系统运行完成,程序运行时间：" + (time) + "毫秒 , 共 " + (time) / 1000 / 60.0 + "分,共" + hour + "小时"); // 输出程序运行时间
	}
}
