package com.ly.task.sendemail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.SystemConfig;
import com.ly.dao.impl.ChiCangDayUpDao;
import com.ly.dao.impl.Shi_Ri_Jun_Xian_UpDao;
import com.ly.dao.impl.Stock_Ma10_DaYu_Ma20Dao;
import com.ly.dao.impl.Stock_Ma5VolBigMa10Dao;
import com.ly.dao.impl.Stock_Ma5VolQuShiDao;
import com.ly.dao.impl.Stock_Shou_Yang_One_DayDao;
import com.ly.dao.impl.Stock_Shou_Yang_Three_DayDao;
import com.ly.dao.impl.Stock_Shou_Yang_Two_DayDao;
import com.ly.dao.impl.Stock_Wu_Ri_Shi_Ri_Chong_HeDao;
import com.ly.dao.impl.WeekLineMa10DaYuMa20Dao;
import com.ly.dao.impl.Week_Ma5VolBigMa10Dao;
import com.ly.dao.impl.Week_Ma5VolQuShiDao;
import com.ly.email.EmailTemplate;
import com.ly.pojo.Chi_Cang_Day_Up;
import com.ly.pojo.Stock_Day_Ma10_Big_Ma20;
import com.ly.pojo.Stock_Shi_Ri_Jun_Xian_Up;
import com.ly.pojo.Stock_Shou_Yang_One_Day;
import com.ly.pojo.Stock_Shou_Yang_Three_Day;
import com.ly.pojo.Stock_Shou_Yang_Two_Day;
import com.ly.pojo.Stock_Wu_Ri_Shi_Ri_ChongHe;
import com.ly.pojo.Stock_Week_Line_Ma10_Big_Ma20;

@Component
public class DayEmail {
	
	
	//String condition = " where benRiTotalHand - zuoRiTotalHand >0 and zuoRiTotalHand - qianRiTotalHand >0 ";
	//String orderBy =" order by (benRiTotalHand*100)/(zongGuBen*100000000)*100 desc ,convert (closePrice, decimal(6, 2)) desc";
	
	//String myAppendStr = condition + orderBy;
	
	@Autowired
	private WeekLineMa10DaYuMa20Dao onlyWeekShouYangDao;

	
	@Autowired
	private Stock_Shou_Yang_One_DayDao stockCloseShouYangOndDayDao;
	
	@Autowired
	private Stock_Shou_Yang_Two_DayDao stockCloseShouYangTwoDayDao;
	
	
	@Autowired
	private Stock_Shou_Yang_Three_DayDao stockCloseShouYangThreeDayDao;
	
	
	@Autowired
	private ChiCangDayUpDao holderDayUpDao;
	
	
	@Autowired
	private Shi_Ri_Jun_Xian_UpDao shi_Ri_Jun_Xian_UpDao;
	
	@Autowired
	private  Stock_Wu_Ri_Shi_Ri_Chong_HeDao wuRiShiRiChongHeDao;
	
	
	@Autowired
	private  Stock_Ma10_DaYu_Ma20Dao stock_Ma10_DaYu_Ma20Dao;
	
	
	@Autowired
	private  Stock_Ma5VolQuShiDao dayma5;
	
	@Autowired
	private  Stock_Ma5VolBigMa10Dao dayma10;
	
	
	
	@Autowired
	private  Week_Ma5VolQuShiDao weekyma5;
	
	@Autowired
	private  Week_Ma5VolBigMa10Dao weekyma10;
	
	@Autowired
	private EmailTemplate template;

	/*public String getTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}*/
	
	public String getTime(){
		List times =  stockCloseShouYangTwoDayDao.find("select max(time) from  Stock_Shou_Yang_Two_Day");
		String time = (String) times.get(0);
		return time;// new Date()为获取当前系统时间
	}
	
	
	public String getTitle(String title){
		title = title+",数据生成时间【" + getTime()+"】";
		return title;
	}
	
	

	//@Scheduled(cron = "01 0 17 * * ?")
	@Scheduled(cron = "0 0 7,17 * * ?")
	public void taskSendDayWeek() throws Exception {
		//	convert (closeprice, decimal(6, 2)) as closeprice,
		//List<Stock_Shou_Yang_Only_Week_Shou_Yang> listDay = onlyWeekShouYangDao.find("from Stock_Shou_Yang_Only_Week_Shou_Yang order by convert (closeprice, decimal(6, 2))  desc");
		List<Stock_Week_Line_Ma10_Big_Ma20> listDay = onlyWeekShouYangDao.find("from Week_Line_Ma10_Da_Yu_Ma20  "+SystemConfig.myAppendStrWeek);
		String title = "周K线10日大于20日线的"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	
	
	//	@Scheduled(cron="0/30 * * * * ?")
	@Scheduled(cron = "0 6 7,17 * * ?")
	public void taskShouPanClose() throws Exception {
		List<Stock_Shou_Yang_One_Day> listDay = stockCloseShouYangOndDayDao.find("from Stock_Shou_Yang_One_Day "+SystemConfig.myAppendStrForShiRi);
		String title = "上5日线的（一天收阳）,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title, listDay);
	}
	
	//@Scheduled(cron="0/30 * * * * ?")
	@Scheduled(cron = "0 9 7,17 * * ?")
	public void taskForEveryday() throws Exception {
		List<Stock_Shou_Yang_Two_Day> listDay = stockCloseShouYangTwoDayDao.find("from Stock_Shou_Yang_Two_Day "+SystemConfig.myAppendStrForShiRi);
		String title = "上5日线的（两天收阳）,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title, listDay);
	}
	
	//@Scheduled(cron="0/30 * * * * ?")
	@Scheduled(cron = "01 12 7,17 * * ?")
	public void taskSendDayNotShouYan() throws Exception {
		List<Stock_Shou_Yang_Three_Day> listDay = stockCloseShouYangThreeDayDao.find("from Stock_Shou_Yang_Three_Day "+SystemConfig.myAppendStrForShiRi);
		String title = "上5日线的（三天收阳）,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	@Scheduled(cron = "01 15 7,17 * * ?")
	public void taskSendHolderDayUp() throws Exception {
		List<Chi_Cang_Day_Up> listDay = holderDayUpDao.find("from Chi_Cang_Day_Up "+SystemConfig.myAppendStrForShiRi);
		String title = "历史购买上5日的（1天收阳）,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	
	
	//
	//@Scheduled(cron="0/20 * * ? * *")
	@Scheduled(cron = "01 18 7,17 * * ?")
	public void taskShiRiUp() throws Exception {
		List<Stock_Shi_Ri_Jun_Xian_Up> listDay = shi_Ri_Jun_Xian_UpDao.find("from Stock_Shi_Ri_Jun_Xian_Up "+SystemConfig.myAppendStrForShiRi);
		String title = "10日线向上的,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	
	
	@Scheduled(cron = "01 22 7,17 * * ?")
	public void taskDayMa10BigMa20() throws Exception {
		List<Stock_Day_Ma10_Big_Ma20> listDay = stock_Ma10_DaYu_Ma20Dao.find("from Stock_Ma10_Da_Yu_Ma20 "+SystemConfig.myAppendStrForShiRi);
		String title = "日K线10日线向上且大于20日线的,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	
	@Scheduled(cron = "01 28 7,17 * * ?")
	public void taskWuRiShiRiChongHe() throws Exception {
		List<Stock_Wu_Ri_Shi_Ri_ChongHe> listDay = wuRiShiRiChongHeDao.find("from Stock_Wu_Ri_Shi_Ri_ChongHe "+SystemConfig.myAppendStrForShiRi);
		String title = "五日线和十日线重合向上的,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	
	
	@Scheduled(cron = "01 32 7,17 * * ?")
	public void ma5Vol() throws Exception {
		List<Stock_Wu_Ri_Shi_Ri_ChongHe> listDay = wuRiShiRiChongHeDao.find("from Ma5VolQuShi "+SystemConfig.condintionForVol);
		String title = "五日成交量向上的,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	
	
	
	@Scheduled(cron = "01 36 7,17 * * ?")
	public void ma5BigMa10Vol() throws Exception {
		List<Stock_Wu_Ri_Shi_Ri_ChongHe> listDay = wuRiShiRiChongHeDao.find("from Ma5VolBigMa10Vol "+SystemConfig.condintionForVol);
		String title = "五日成交量向上且大于10日成交量向上的,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	
	
	@Scheduled(cron = "01 40 7,17 * * ?")
	public void weekMa5() throws Exception {
		List<Stock_Wu_Ri_Shi_Ri_ChongHe> listDay = wuRiShiRiChongHeDao.find("from WeekMa5VolQuShi "+SystemConfig.condintionForVol);
		String title = "周五日成交量向上的,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	
	
	@Scheduled(cron = "01 44 7,17 * * ?")
	public void weekMa5BigMa10() throws Exception {
		List<Stock_Wu_Ri_Shi_Ri_ChongHe> listDay = wuRiShiRiChongHeDao.find("from WeekMa5VolBigMa10Vol "+SystemConfig.condintionForVol);
		String title = "周线五日成交量向上且大于10日成交量向上的,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	
}
