package com.ly.common.dateutil;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
 
public class GetWeekOfDay {
	//private static Logger day = Logger.getLogger("day");
	private static Logger day = Logger.getLogger(GetWeekOfDay.class);
	public static  Map<String,String> getMondayAndFridayTime(String timeStr){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = format.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getWeekDate(date);
	}
	
	/**
     * 当前时间所在一周的周一和周日时间
     * @param time 当前时间
     * @return
     */
    public static Map<String,String> getWeekDate(Date date) {
        Map<String,String> map = new HashMap<String,String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 
         Calendar cal = Calendar.getInstance();  
         cal.setTime(date);
         cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
         int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
         if(dayWeek==1){
             dayWeek = 8;
         }
         //System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
         day.debug("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
         cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
         Date mondayDate = cal.getTime();
         String weekBegin = sdf.format(mondayDate);  
         //System.out.println("所在周星期一的日期：" + weekBegin);  
         day.debug("所在周星期一的日期：" + weekBegin);  
 
 
       //  cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
         cal.add(Calendar.DATE, 2 +cal.getFirstDayOfWeek());
         Date sundayDate = cal.getTime();
         String weekEnd = sdf.format(sundayDate);  
         //System.out.println("所在周星期日的日期：" + weekEnd);
        // System.out.println("所在周星期五的日期：" + weekEnd);
         day.debug("所在周星期五的日期：" + weekEnd);
 
         map.put("mondayDate", weekBegin);
         //map.put("sundayDate", weekEnd);
         map.put("FridayDate", weekEnd);
        return map;
    }
	
}