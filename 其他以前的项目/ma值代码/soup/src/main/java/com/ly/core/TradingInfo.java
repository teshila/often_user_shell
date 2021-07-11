package com.ly.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ly.common.http.PingAnWithouAuthInfo;
import com.ly.common.util.SystemSoupConstrant;
import com.ly.dao.impl.StockDao;
import com.ly.dao.impl.Stock_K_line_Day_Data_List_Dao;
import com.ly.dao.impl.Trading_Stock_Yang_Of_Two_Day_UpDao;
import com.ly.pinyin.YePinYinUtils;
import com.ly.pojo.Stock;
import com.ly.pojo.Stock_K_line_Day_DataList;
import com.ly.pojo.Trading_Stock_Yang_Of_Two_Day_Up;


//https://blog.csdn.net/myNameIssls/article/details/54091675
@Component
public class TradingInfo {
	private static Logger logger = Logger.getLogger("tradding");

	@Autowired
	private PingAnWithouAuthInfo pingAnWithouAuthInfo;

	@Autowired
	private StockDao stockDao;


	@Autowired
	private Stock_K_line_Day_Data_List_Dao stock_k_line_day_data_list_dao; 
	
	@Autowired
	private Trading_Stock_Yang_Of_Two_Day_UpDao tradingTwoDayDao;
	
	public void getInfo() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String timeStr = sdf.format(now);
		Stock sb = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String timeStrs = df.format(new Date());
		List<Trading_Stock_Yang_Of_Two_Day_Up> tradingTwoDayUpPrams = null;
		int total = (int) stockDao.findCount(Stock.class);
		tradingTwoDayDao.truncateTable(Trading_Stock_Yang_Of_Two_Day_Up.class);
		try {
			// int rows = 200;
			int rows = SystemSoupConstrant.rows;
			//int seconds = SystemSoupConstrant.seconds;
			int seconds = 3;
			int step = total / rows + 1;
			List<Map<String, String>> stocksMaps = null;
			Map<String, String> stockMapInf = null;
			for (int i = 1; i <= step; i++) {
				stocksMaps = new ArrayList<Map<String, String>>();
				logger.info("当前迭代次数==========>  " + i + " ,还需要" + ((step - i) + 1) + " 次迭代爬虫");
				if (i > 1) {
					Thread.sleep(seconds);
				}
				List<Stock> sbLists = stockDao.findByPage("from Stock", i, rows);
				if (sbLists != null && sbLists.size() > 0) {
					tradingTwoDayUpPrams = new ArrayList<Trading_Stock_Yang_Of_Two_Day_Up>();
					
					for (int j = 0; j < sbLists.size(); j++) {
						stockMapInf = new HashMap<String, String>();
						stockMapInf.put("code", sbLists.get(j).getCode());
						stockMapInf.put("marketType", sbLists.get(j).getMarketType());
						stocksMaps.add(stockMapInf);
					}

					List list = pingAnWithouAuthInfo.getStockInfoByBatch(stocksMaps);
					if (list != null && list.size() > 0) {
						for (int j = 0; j < list.size(); j++) {
							sb = sbLists.get(j);
							Map map = (Map) list.get(j);

							String code = (String) map.get("code");
							String codeType = (String) map.get("codeType");
							String stockName = (String) map.get("stockName");
							if (stockName.contains("Ａ")) {
								stockName = stockName.replace("Ａ", "A");
							}
							YePinYinUtils.convertChineseToPinyin(stockName);
							String headP = YePinYinUtils.getHeadPinyin();

							String newPrice = (String) map.get("newPrice");
							String prevClose = (String) map.get("prevClose");
							String open = (String) map.get("open");
							String maxPrice = (String) map.get("maxPrice");
							String minPrice = (String) map.get("minPrice");
							String totalHand = (String) map.get("totalHand");
							String sellPrice1 = (String) map.get("sellPrice1");
							String sellPrice2 = (String) map.get("sellPrice2");
							String sellPrice3 = (String) map.get("sellPrice3");
							String sellPrice4 = (String) map.get("sellPrice4");
							String sellPrice5 = (String) map.get("sellPrice5");

							
							
							//logger.info("=====>本次价格== >" + newPrice + " 代码 ==>  " + code + " 名称 ==> " + stockName);
							if (newPrice.contains("-")) {
								newPrice = "0.0";
							}

							if (prevClose.contains("-")) {
								prevClose = "0.0";
							}

							if (open.contains("-")) {
								open = "0.0";
							}

							if (sellPrice1.contains("-")) {
								sellPrice1 = "0.0";
							}

							if (sellPrice2.contains("-")) {
								sellPrice2 = "0.0";
							}

							if (sellPrice3.contains("-")) {
								sellPrice3 = "0.0";
							}

							if (sellPrice4.contains("-")) {
								sellPrice4 = "0.0";
							}

							if (sellPrice5.contains("-")) {
								sellPrice5 = "0.0";
							}

							String sellCount1 = (String) map.get("sellCount1");
							String sellCount2 = (String) map.get("sellCount2");
							String sellCount3 = (String) map.get("sellCount3");
							String sellCount4 = (String) map.get("sellCount4");
							String sellCount5 = (String) map.get("sellCount5");

							if (sellCount1.contains("-")) {
								sellCount1 = "0.0";
							}

							if (sellCount2.contains("-")) {
								sellCount2 = "0.0";
							}

							if (sellCount3.contains("-")) {
								sellCount3 = "0.0";
							}

							if (sellCount4.contains("-")) {
								sellCount4 = "0.0";
							}

							if (sellCount5.contains("-")) {
								sellCount5 = "0.0";
							}

							String buyCount1 = (String) map.get("buyCount1");
							String buyCount2 = (String) map.get("buyCount2");
							String buyCount3 = (String) map.get("buyCount3");
							String buyCount4 = (String) map.get("buyCount4");
							String buyCount5 = (String) map.get("buyCount5");

							if (buyCount1.contains("-")) {
								buyCount1 = "0.0";
							}

							if (buyCount2.contains("-")) {
								buyCount2 = "0.0";
							}

							if (buyCount3.contains("-")) {
								buyCount3 = "0.0";
							}

							if (buyCount4.contains("-")) {
								buyCount4 = "0.0";
							}

							if (buyCount5.contains("-")) {
								buyCount5 = "0.0";
							}

							String buyPrice1 = (String) map.get("buyPrice1");
							String buyPrice2 = (String) map.get("buyPrice2");
							String buyPrice3 = (String) map.get("buyPrice3");
							String buyPrice4 = (String) map.get("buyPrice4");
							String buyPrice5 = (String) map.get("buyPrice5");

							if (buyPrice1.contains("-")) {
								buyPrice1 = "0.0";
							}

							if (buyPrice2.contains("-")) {
								buyPrice2 = "0.0";
							}

							if (buyPrice3.contains("-")) {
								buyPrice3 = "0.0";
							}

							if (buyPrice4.contains("-")) {
								buyPrice4 = "0.0";
							}
							if (buyPrice5.contains("-")) {
								buyPrice5 = "0.0";
							}

							String totalAmount = (String) map.get("totalAmount");
							String upPrice = (String) map.get("upPrice");
							String downPrice = (String) map.get("downPrice");

							if (totalAmount.contains("-")) {
								totalAmount = "0.0";
							}

							Double buyCount1Num = Double.valueOf(buyCount1);
							Double buyCount2Num = Double.valueOf(buyCount2);
							Double buyCount3Num = Double.valueOf(buyCount3);
							Double buyCount4Num = Double.valueOf(buyCount4);
							Double buyCount5Num = Double.valueOf(buyCount5);

							Double buyPrice1Double = Double.valueOf(buyPrice1);
							Double buyPrice2Double = Double.valueOf(buyPrice2);
							Double buyPrice3Double = Double.valueOf(buyPrice3);
							Double buyPrice4Double = Double.valueOf(buyPrice4);
							Double buyPrice5Double = Double.valueOf(buyPrice5);

							Double sell1CountNum = Double.valueOf(sellCount1);
							Double sell2CountNum = Double.valueOf(sellCount2);
							Double sell3CountNum = Double.valueOf(sellCount3);
							Double sell4CountNum = Double.valueOf(sellCount4);
							Double sell5CountNum = Double.valueOf(sellCount5);

							Double sellPrice1Double = Double.valueOf(sellPrice1);
							Double sellPrice2Double = Double.valueOf(sellPrice2);
							Double sellPrice3Double = Double.valueOf(sellPrice3);
							Double sellPrice4Double = Double.valueOf(sellPrice4);
							Double sellPrice5Double = Double.valueOf(sellPrice5);

							Double buyTotal = buyCount1Num + buyCount2Num + buyCount3Num + buyCount4Num + buyCount5Num;
							Double sellTotal = sell1CountNum + sell2CountNum + sell3CountNum + sell4CountNum + sell5CountNum;

							Double closePrice = Double.valueOf(newPrice);
							Double prevclose = Double.valueOf(prevClose);
							Double openPrice = Double.valueOf(open);

							Double fiveDayPrice = 0.0;
							Double fiveDayPrice1 = 0.0;
							double totalIsZhang = closePrice - openPrice;
							double isZhang = closePrice - prevclose;
							Double yesterDayOpen = 0.0;
							
							List listCodes = stock_k_line_day_data_list_dao.find("from Stock_K_line_Day_DataList t where t.code = ? ", code);
						
							Stock_K_line_Day_DataList sts = null;

							if(listCodes!=null&&listCodes.size()>0){
								sts = (Stock_K_line_Day_DataList) listCodes.get(0);
								
								if(sts!=null){
									String fiveDayLinePriceStr1 = sts.getMa5();
									fiveDayPrice1 = Double.valueOf(fiveDayLinePriceStr1);
									String yestOpen = sts.getOpenPrice();
									yesterDayOpen = Double.valueOf(yestOpen);
								}else{
									fiveDayPrice1 = closePrice;
								}
								
							}else{
								fiveDayPrice1 = closePrice;
								yesterDayOpen = closePrice;
							}
							
							
							//获取日线数据
							List<Map<String,String>> resultList = pingAnWithouAuthInfo.getDayLineMaInfo(code, codeType);
							if (j > 1&&j%50==0) {
								Thread.sleep(5 * seconds);
							}
							if(resultList!=null&&resultList.size()>0){
								//System.out.println(resultList.get(resultList.size()-1));
								Map ret = resultList.get(resultList.size()-1);
								String ma5PriceStr = (String)ret.get("ma5");
								
								fiveDayPrice = Double.valueOf(ma5PriceStr);
							}
							
							
							double isBiggerThanFiveDay = closePrice - fiveDayPrice;
							double isBiggerThanFiveDay1 = prevclose - fiveDayPrice1;
							double twoDayIsBiggerThanFiveDay = fiveDayPrice - fiveDayPrice1;
							Double isTwoDayShouYang = prevclose - yesterDayOpen;
							Double sellIsBig = sellTotal - buyTotal;
							boolean flag = newPrice.equals(upPrice);
							if (flag) {
								sellIsBig = 0.8;
							}
							
							logger.debug("代码 ==> " +code + ",名称==> " + stockName + " , 挂单之中 sellIsBig==>"+sellIsBig + "  totalIsZhang==>" +totalIsZhang + "  isZhang==>" +isZhang + "  isBiggerThanFiveDay==>" +isBiggerThanFiveDay);
							
							if(totalIsZhang>0&&isTwoDayShouYang>0&&twoDayIsBiggerThanFiveDay>0&&isBiggerThanFiveDay>0&&isBiggerThanFiveDay1>0){
								Trading_Stock_Yang_Of_Two_Day_Up  tradingTwoDayUp = new Trading_Stock_Yang_Of_Two_Day_Up();
								tradingTwoDayUp.setCode(code);
								tradingTwoDayUp.setName(stockName);
								tradingTwoDayUp.setClosePrice(newPrice);

								tradingTwoDayUp.setPinyin(headP);
								tradingTwoDayUp.setPrevClose(prevClose);
								tradingTwoDayUp.setTime(timeStrs);
								
								
								tradingTwoDayUp.setHangye(sb.getHangye());
								tradingTwoDayUp.setLiuTongGu(sb.getLiuTongGu());
								tradingTwoDayUp.setDiQu(sb.getDiQu());
								tradingTwoDayUp.setZongGuBen(sb.getZongGuBen());
								tradingTwoDayUp.setShiyinglvJing(sb.getShiyinglvJing());
								tradingTwoDayUp.setBenRiTotalHand(totalHand);
								
								tradingTwoDayUpPrams.add(tradingTwoDayUp);
								
							}

						}
					}

					if (tradingTwoDayUpPrams != null && tradingTwoDayUpPrams.size() > 0) {
						tradingTwoDayDao.batchSave(tradingTwoDayUpPrams);
					}
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		long endTime = System.currentTimeMillis(); // 获取结束时间
		long time = endTime - startTime;
		double hour = time / 1000 / 60.0 / 60.0;
		logger.debug("程序运行时间：" + (time) + "毫秒 , 共 " + (time) / 1000 / 60.0 + "分,共" + hour + "小时"); // 输出程序运行时间
	}

	

}
