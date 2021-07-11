package com.ly.task.sendemail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.dao.impl.ChiCangDayUpDao;
import com.ly.dao.impl.Stock_Shou_Yang_Day_And_Week_Shou_YangDao;
import com.ly.dao.impl.Stock_Shou_Yang_One_DayDao;
import com.ly.dao.impl.Stock_Shou_Yang_Only_Week_Shou_YangDao;
import com.ly.dao.impl.Stock_Shou_Yang_Three_DayDao;
import com.ly.dao.impl.Stock_Shou_Yang_Two_DayDao;
import com.ly.email.EmailTemplate;
import com.ly.pojo.Chi_Cang_Day_Up;
import com.ly.pojo.Stock_Shou_Yang_Day_And_Week_Shou_Yang;
import com.ly.pojo.Stock_Shou_Yang_One_Day;
import com.ly.pojo.Stock_Shou_Yang_Only_Week_Shou_Yang;
import com.ly.pojo.Stock_Shou_Yang_Three_Day;
import com.ly.pojo.Stock_Shou_Yang_Two_Day;

@Component
public class EmailTask {
	
	
	@Autowired
	private Stock_Shou_Yang_Only_Week_Shou_YangDao onlyWeekShouYangDao;

	
	
	@Autowired
	private Stock_Shou_Yang_Day_And_Week_Shou_YangDao zhouAndDayShouYangDao;
	
	
	@Autowired
	private Stock_Shou_Yang_One_DayDao stockCloseShouYangOndDayDao;
	
	@Autowired
	private Stock_Shou_Yang_Two_DayDao stockCloseShouYangTwoDayDao;
	
	
	@Autowired
	private Stock_Shou_Yang_Three_DayDao stockCloseShouYangThreeDayDao;
	
	
	@Autowired
	private ChiCangDayUpDao holderDayUpDao;
	
	
	@Autowired
	private EmailTemplate template;

	public String getTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	
	
	public String getTitle(String title){
		title = title+"生成时间【" + getTime()+"】";
		return title;
	}
	
	
	
	

	//@Scheduled(cron="0/30 * * * * ?")
	@Scheduled(cron = "01 0 17 * * ?")
	public void taskSendDayWeek() throws Exception {
		//	convert (closeprice, decimal(6, 2)) as closeprice,
		List<Stock_Shou_Yang_Only_Week_Shou_Yang> listDay = onlyWeekShouYangDao.find("from Stock_Shou_Yang_Only_Week_Shou_Yang order by convert (closeprice, decimal(6, 2))  asc");
		String title = "市场中所有周线向上的"+listDay.size()+"条";
		title  = getTitle(title);
		//template.send(title,listDay);
	}
	
	
	@Scheduled(cron = "01 03 17 * * ?")
	public void taskSendDayShouYang() throws Exception {
		List<Stock_Shou_Yang_Day_And_Week_Shou_Yang> listDay = zhouAndDayShouYangDao.find("from Stock_Shou_Yang_Day_And_Week_Shou_Yang order by  convert (closeprice, decimal(6, 2))  asc");
		String title = "周收阳以及日收阳的,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title, listDay);
	}
	
	
//	@Scheduled(cron="0/30 * * * * ?")
	@Scheduled(cron = "0 06 17 * * ?")
	public void taskShouPanClose() throws Exception {
		List<Stock_Shou_Yang_One_Day> listDay = stockCloseShouYangOndDayDao.find("from Stock_Shou_Yang_One_Day order by  convert (closeprice, decimal(6, 2))  asc");
		String title = "每天收盘收阳且上5日线的（连续一天涨的）,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title, listDay);
	}
	
	@Scheduled(cron = "0 09 17 * * ?")
	public void taskForEveryday() throws Exception {
		List<Stock_Shou_Yang_Two_Day> listDay = stockCloseShouYangTwoDayDao.find("from Stock_Shou_Yang_Two_Day order by  convert (closeprice, decimal(6, 2))  asc");
		String title = "每天收盘收阳且上5日线的（连续两天涨）,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title, listDay);
	}
	
	@Scheduled(cron = "01 12 17 * * ?")
	public void taskSendDayNotShouYan() throws Exception {
		List<Stock_Shou_Yang_Three_Day> listDay = stockCloseShouYangThreeDayDao.find("from Stock_Shou_Yang_Three_Day order by convert (closeprice, decimal(6, 2))  asc");
		String title = "每天收盘收阳且上5日线的（连续三天收阳）,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	@Scheduled(cron = "01 15 17 * * ?")
	public void taskSendHolderDayUp() throws Exception {
		List<Chi_Cang_Day_Up> listDay = holderDayUpDao.find("from Chi_Cang_Day_Up order by convert (closeprice, decimal(6, 2))  asc");
		String title = "持有中日线涨且上5日线的（连续1天收阳）,共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title,listDay);
	}
	
	
	
	//@Scheduled(cron="0/30 * * * * ?")
/*	@Scheduled(cron = "0 30 17 * * ?")
	public void taskGuaDan() throws Exception {
		List<GuaDan> listDay = guaDanDao.getAll();
		//template.send("挂单涨的【 买入参考,解套做T等指导介入位,一般除妖股和新股外,此列表都是低位】,共"+listDay.size()+"条", listDay);
		String title = "S挂单[预备涨],共"+listDay.size()+"条";
		title  = getTitle(title);
		template.send(title, listDay);
	}*/
}
