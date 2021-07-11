package com.ly.task.jsoup;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ly.common.util.DownLoadPic;
import com.ly.common.util.SystemSoupConstrant;
import com.ly.dao.impl.StockDao;
import com.ly.pojo.Stock;


//@Component
public class GetStockQuShiLine {
	private static Logger logger = Logger.getLogger("picQuShi");
	 
	@Autowired
	private StockDao stockDao;
	
	//@Scheduled(cron = "30 55 17 ? * *")
	public void getStockPic() throws InterruptedException{
		long startTime = System.currentTimeMillis(); // 获取开始时间
		Stock sb = null;
		int total = (int) stockDao.findCount(Stock.class);
		int rows = SystemSoupConstrant.rows;
		//int seconds = SystemSoupConstrant.seconds;
		int step = total / rows + 1;
		for (int i = 1; i <= step; i++) {
			List<Stock> sbLists = stockDao.findByPage("from Stock", i, rows);
			
			if(sbLists!=null&&sbLists.size()>0){
				for (int j = 0; j < sbLists.size(); j++) {
					sb = sbLists.get(j);
					String code =  sb.getCode();
					if(j>0&&j%50==0){
						Thread.sleep(1000*5);
					}
					
					logger.debug("当前采集到的图片数据为 ==> " + sb.toString());
					DownLoadPic.getPicsToDistincPath(code);
				}
			}
			
			
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		long time = endTime - startTime;
		double hour = time / 1000 / 60.0 / 60.0;
		logger.debug("采集完成程序运行时间：" + (time) + "毫秒 , 共 " + (time) / 1000 / 60.0 + "分,共" + hour + "小时"); // 输出程序运行时间
	}
}
