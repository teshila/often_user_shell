package com.ly.task.jsoup.week;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.common.util.SystemSoupConstrant;
import com.ly.dao.impl.StockDao;
import com.ly.dao.impl.Stock_K_line_Week_Data_List_Dao;
import com.ly.pojo.Stock;
import com.ly.pojo.Stock_K_line_Week_DataList;
import com.ly.task.jsoup.week.cal.CalWeekLineUpAsync;
import com.ly.task.jsoup.week.http.GetWeekLineInfoByPingAnHttp;

@Component
public class JsoupWeekInfo {
	private static Logger week = Logger.getLogger("week");

	@Autowired
	private GetWeekLineInfoByPingAnHttp stockGetDayDataBigInt;

	@Autowired
	private StockDao stockImpl;

	@Autowired
	private Stock_K_line_Week_Data_List_Dao stock_K_line_Week_Data_List_Dao;
	
	@Autowired
	private CalWeekLineUpAsync calWeekLineUpAsync;
	

	// @Scheduled(cron="0 22 17 ? * MON-FRI")
	// @Scheduled(cron="*/10 * * * * ?")
	@Scheduled(cron = "40 40 15 ? * MON-FRI")
	public void task() throws Exception {
		stock_K_line_Week_Data_List_Dao.truncateTable(Stock_K_line_Week_DataList.class);
		long startTime = System.currentTimeMillis(); // 获取开始时间
		Long total = stockImpl.findCount(Stock.class);
		int rows = SystemSoupConstrant.rows;
		int seconds = SystemSoupConstrant.seconds;
		int step = (int) (total / rows + 1);
		Stock sb = null;
		Stock_K_line_Week_DataList weekInfo = null;
		List<Stock_K_line_Week_DataList> infoParamList = null;
		for (int i = 1; i <= step; i++) {
			int pageNo = i;
			int pageSize = rows;
			if (i > 1) {
				Thread.sleep(seconds);
			}
			week.info("当前迭代次数==========>  " + i + " ,还需要" + ((step - i) + 1) + " 次迭代爬虫");
			List<Stock> sbLists = stockImpl.findByPage("from Stock", pageNo, pageSize);
			if (sbLists != null && sbLists.size() > 0) {
				for (int j = 0; j < sbLists.size(); j++) {
					infoParamList = new ArrayList<Stock_K_line_Week_DataList>();
					sb = sbLists.get(j);
					week.debug("需要获取MA5数据的股票代码【 " + sb.getCode() + " 】，名称 【" + sb.getName() + "】,所属市场编码 【 " + sb.getMarketType() + " 】");
					try {
						List<Map<String, String>> resultList = stockGetDayDataBigInt.getWeekLineInfo(sb.getCode(), sb.getMarketType());

						week.debug("当前爬虫到第" + (j + 1) + "条数据,共" + sbLists.size() + "条数据，还有" + (sbLists.size() - (j + 1)) + "条数据没爬虫到，当前代码【" + sb.getCode() + "】,名称【" + sb.getName() + "】返回信息" + resultList);
						if (resultList != null && resultList.size() > 0) {
							for (Map<String, String> temp : resultList) {
								weekInfo = new Stock_K_line_Week_DataList();
								weekInfo.setCode(sb.getCode());
								weekInfo.setName(sb.getName());
								weekInfo.setPinyin(sb.getPinyin());

								weekInfo.setExchangeType(sb.getExchangeType());
								weekInfo.setMarketType(sb.getMarketType());
								weekInfo.setOpenPrice(temp.get("openPrice"));
								weekInfo.setClosePrice(temp.get("closePrice"));
								weekInfo.setMinPrice(temp.get("minPrice"));
								weekInfo.setMaxPrice(temp.get("maxPrice"));
								weekInfo.setMa5(temp.get("ma5"));
								weekInfo.setMa10(temp.get("ma10"));
								weekInfo.setMa30(temp.get("ma30"));
								weekInfo.setDate(temp.get("date"));

								weekInfo.setHangye(sb.getHangye());
								weekInfo.setLiuTongGu(sb.getLiuTongGu());
								weekInfo.setDiQu(sb.getDiQu());
								weekInfo.setZongGuBen(sb.getZongGuBen());
								weekInfo.setShiyinglvJing(sb.getShiyinglvJing());
								weekInfo.setTotalHand(temp.get("total"));

								infoParamList.add(weekInfo);

							}

						}

						stock_K_line_Week_Data_List_Dao.batchSave(infoParamList);

					} catch (Exception e) {
						e.getStackTrace();
					}
				}
				
				
			}
		}
		calWeekLineUpAsync.task01();
		calWeekLineUpAsync.task02();
		
		long endTime = System.currentTimeMillis(); // 获取结束时间
		long time = endTime - startTime;
		double hour = time / 1000 / 60.0 / 60.0;
		week.debug("系统运行完成,程序运行时间：" + (time) + "毫秒 , 共 " + (time) / 1000 / 60.0 + "分,共" + hour + "小时"); // 输出程序运行时间
	}

}
