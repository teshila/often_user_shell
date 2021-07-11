package com.ly.test;
import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.ly.common.ma.FinanceIndicatorsTools;
import com.ly.dao.impl.Stock_K_line_Day_Data_List_Dao;
import com.ly.pojo.Stock_K_line_Day_DataList;



public class FinanceIndicatorsTest  {
 
    
 
    
    public static void main(String[] args) {
    	Logger logger = LoggerFactory.getLogger(FinanceIndicatorsTest.class);
    	ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:app.xml");
    	
    	
    	Stock_K_line_Day_Data_List_Dao  stock_K_line_Day_Data_List_Dao = ac.getBean(Stock_K_line_Day_Data_List_Dao.class);
    	
    	/**
         * 整理数据最好是5*daysize或者是3.45*(N+1）天的数据
         */
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            int manDaySize = 26;
            int kuaiDaySize = 12;
            int avgDaySize = 9;
                //数据库查询日交易数据
                //List<Stock_K_line_Day_DataList> tradeList =  stock_K_line_Day_Data_List_Dao.findLastSizeBySecCode("600004",manDaySize*5);
                List<Stock_K_line_Day_DataList> tradeList =  stock_K_line_Day_Data_List_Dao.find("from Stock_K_line_Day_DataList where code = ? order by date desc", "000983");
                if(tradeList != null ) {
                    // 反转lists,实现交易日期从小到大排列
                    Collections.reverse(tradeList);
                    int length = tradeList.size();
 
                    for(int i=0;i<length;i++){
                        List<BigDecimal > list = new ArrayList<>();
                        int localLength = tradeList.size();
                        BigDecimal F007N = null;
                        String dateStr =  tradeList.get(localLength-1).getDate();
                        
                        System.out.println(dateStr);
                        Date lastDate = strToDate(dateStr);
                        String lastFormatDate = sdf.format(lastDate);
 
                        for(int jx =0;jx < localLength;jx++){
                        	String closePriceStr = tradeList.get(jx).getClosePrice();
                        	double closePrice = Double.valueOf(closePriceStr);
                            F007N =  new BigDecimal(closePrice);
                            if(F007N != null    ){
                                list.add(F007N);
                            }
 
                        }
 
                        HashMap result = FinanceIndicatorsTools.getMACDV1(list,12,26,9);
                        logger.info("测试计算移动平均线"+lastFormatDate+ JSON.toJSONString(result));
                        tradeList.remove(tradeList.size()-1);
                        length = tradeList.size();
                    }
 
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error("测试计算移动平均线异常",e);
            }
 
 
	}
 
        
 
    
    
    	public static Date strToDate(String strDate) {
    	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    	    ParsePosition pos = new ParsePosition(0);
    	    Date strtodate = formatter.parse(strDate, pos);
    	    return strtodate;
    	 }
    	
    	
    	/*public static void main(String[] args) throws ParseException {
    	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
    	    Date date = simpleDateFormat.parse("2019-09-02");
    	    System.out.println(date);

    	    System.out.println(simpleDateFormat.format(date));
    	}*/
    }