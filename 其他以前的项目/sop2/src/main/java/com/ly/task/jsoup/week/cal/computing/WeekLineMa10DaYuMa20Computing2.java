package com.ly.task.jsoup.week.cal.computing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.common.util.SystemSoupConstrant;
import com.ly.dao.impl.StockDao;
import com.ly.dao.impl.Stock_K_line_Week_Data_List_Dao;
import com.ly.dao.impl.WeekLineMa10DaYuMa20Dao;
import com.ly.pojo.Stock;
import com.ly.pojo.Stock_K_line_Week_DataList;
import com.ly.pojo.Week_Line_Ma10_Da_Yu_Ma20;

//短线算法
//@Component
public class WeekLineMa10DaYuMa20Computing2 {
	
	private static Logger calday = Logger.getLogger("week");
	
	@Autowired
	private StockDao stockDao;
	
	@Autowired
	private Stock_K_line_Week_Data_List_Dao stock_k_line_week_data_list_dao;
	
	@Autowired
	private WeekLineMa10DaYuMa20Dao weekLineMa10DaYuMa20Dao;
	
	
	//@Scheduled(cron="*/10 * * * * ?")
	//@Scheduled(cron="0 02 9 ? * *")
	//@Scheduled(cron="0 34 2 ? * *")
	//@Scheduled(cron = "40 05 16 ? * MON-FRI")
	//@Scheduled(cron="30 16 20 ? * *")
	public void getStockInfo() {
		long startTime = System.currentTimeMillis();    //获取开始时间
		List<Week_Line_Ma10_Da_Yu_Ma20> entitys =  null;
		weekLineMa10DaYuMa20Dao.truncateTable(Week_Line_Ma10_Da_Yu_Ma20.class);
		Week_Line_Ma10_Da_Yu_Ma20 stock_Shou_Yang_Day_One_Day = null;
		long total = stockDao.findCount(Stock.class);
		int rows = SystemSoupConstrant.rows;
		int seconds = SystemSoupConstrant.seconds;
		int step = (int) (total / rows + 1);
		
		for (int i = 1; i <= step; i++) {
			entitys = new ArrayList<Week_Line_Ma10_Da_Yu_Ma20>(); 
			List<Stock> list = stockDao.findByPage("from Stock", i, rows);
			
			for (int j = 0; j < list.size(); j++) {
				Stock sts = list.get(j);
				String code =sts.getCode();
				
				//Object [] parasm = new Object[]{code} ;
				List<Stock_K_line_Week_DataList> datas = stock_k_line_week_data_list_dao.findByPage("from Stock_K_line_Week_DataList  where code = ?  ORDER BY date desc",0,15, code);
				
				String week1ma5SQL ="select round(sum(t.closeprice)/5,2) from (select * from Stock_K_line_Week_DataList t  where code=? order by date desc limit 0,5) t ";

				String week1ma10SQL ="select round(sum(t.closeprice)/10,2) from (select * from Stock_K_line_Week_DataList t  where code=? order by date desc limit 0,10) t";


				String week1ma20SQL ="select round(sum(t.closeprice)/20,2) from (select * from Stock_K_line_Week_DataList t  where code=? order by date desc limit 0,20) t";


				String week2ma5SQL ="select round(sum(t.closeprice)/5,2) from (select * from Stock_K_line_Week_DataList t  where code=? order by date desc limit 1,5) t";

				String week2ma10SQL ="select round(sum(t.closeprice)/10,2) from (select * from Stock_K_line_Week_DataList t  where code=? order by date desc limit 1,10) t";

				String week2ma20SQL ="select round(sum(t.closeprice)/20,2) from (select * from Stock_K_line_Week_DataList t  where code=? order by date desc limit 1,20) t";



				String week3ma5SQL ="select round(sum(t.closeprice)/5,2) from (select * from Stock_K_line_Week_DataList t  where code=? order by date desc limit 2,5) t";

				String week3ma10SQL ="select round(sum(t.closeprice)/10,2) from (select * from Stock_K_line_Week_DataList t  where code=? order by date desc limit 2,10) t";

				String week3ma20SQL ="select round(sum(t.closeprice)/20,2) from (select * from Stock_K_line_Week_DataList t  where code=? order by date desc limit 2,20) t";

				
				
				try{
					if(datas!=null&&datas.size()>3){
						Stock_K_line_Week_DataList week1 = datas.get(0);
						Stock_K_line_Week_DataList week2 = datas.get(1);
						Stock_K_line_Week_DataList week3 = datas.get(2);
						
						Double week1ma5 = stock_k_line_week_data_list_dao.getNum(week1ma5SQL,code);
						Double week1ma10 = stock_k_line_week_data_list_dao.getNum(week1ma10SQL.toString(),code);
						Double week1ma20 = stock_k_line_week_data_list_dao.getNum(week1ma20SQL.toString(),code);
						
						
						
						Double week2ma5 = stock_k_line_week_data_list_dao.getNum(week2ma5SQL,code);
						Double week2ma10 = stock_k_line_week_data_list_dao.getNum(week2ma10SQL.toString(),code);
						Double week2ma20 = stock_k_line_week_data_list_dao.getNum(week2ma20SQL.toString(),code);
						
						
						Double week3ma5 = stock_k_line_week_data_list_dao.getNum(week3ma5SQL,code);
						Double week3ma10 = stock_k_line_week_data_list_dao.getNum(week3ma10SQL.toString(),code);
						Double week3ma20 = stock_k_line_week_data_list_dao.getNum(week3ma20SQL.toString(),code);
						
					
						Double week1Open  =Double.valueOf( week1.getOpenPrice());
						Double week1Close =Double.valueOf(week1.getClosePrice());
						Double week1Ma5 = Double.valueOf(week1.getMa5());
						
						
						Double week2Open  =Double.valueOf(week2.getOpenPrice());
						Double week2Close =Double.valueOf(week2.getClosePrice());
						Double week2Ma5 = Double.valueOf(week2.getMa5());
						
						Double week3Open  =Double.valueOf(week3.getOpenPrice());
						Double week3Close =Double.valueOf(week3.getClosePrice());
						Double week3Ma5 = Double.valueOf(week3.getMa5());
						
						
						double weekMa10IsZhang = week1ma10 - week2ma10;
						
						
						double weekMa10IsBigMa20 = week1ma10 - week1ma20;
						
						
						if(weekMa10IsBigMa20>=0&&weekMa10IsZhang>=0){
							
							calday.info( sts.getCode() + "  ==> "+ sts.getName()+" 周ma10与ma20比较 "+ weekMa10IsBigMa20  +" 本周ma10与上周ma10比较 "+ weekMa10IsZhang  );
							
							stock_Shou_Yang_Day_One_Day = new Week_Line_Ma10_Da_Yu_Ma20();
							
							stock_Shou_Yang_Day_One_Day.setCode(code);
							System.out.println(sts.getName());
							stock_Shou_Yang_Day_One_Day.setName(sts.getName());
							stock_Shou_Yang_Day_One_Day.setPinyin(sts.getPinyin());
							
							stock_Shou_Yang_Day_One_Day.setExchangeType(sts.getExchangeType());
							stock_Shou_Yang_Day_One_Day.setMarketType(sts.getMarketType());
							
							stock_Shou_Yang_Day_One_Day.setQianRiClose(week1.getClosePrice());
							
							stock_Shou_Yang_Day_One_Day.setBenZhouTotalHand(week1.getTotalHand());
							stock_Shou_Yang_Day_One_Day.setShangZhouTotalHand(week2.getTotalHand());
							stock_Shou_Yang_Day_One_Day.setQianZhouTotalHand(week2.getTotalHand());
							
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
