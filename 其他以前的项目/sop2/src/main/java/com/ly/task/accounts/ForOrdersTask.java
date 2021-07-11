package com.ly.task.accounts;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.core.StockTradeCore;
import com.ly.dao.impl.AccountDao;
import com.ly.dao.impl.BuyDao;
import com.ly.dao.impl.OrdersDao;
import com.ly.dao.impl.SellDao;
import com.ly.dao.impl.StockDao;
import com.ly.pinyin.YePinYinUtils;
import com.ly.pojo.Account;
import com.ly.pojo.Buy;
import com.ly.pojo.Orders;
import com.ly.pojo.Sell;
import com.ly.pojo.Stock;

//https://jingyan.baidu.com/article/7f41ecec0d0724593d095c19.html
//用于作为明天的卖出价，每天15点30分之后，查询每天成交的挂单信息
@Component
public class ForOrdersTask {
	private static final Logger logger = Logger.getLogger("orders");

	@Autowired
	private StockTradeCore stockTradeBus;

	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private SellDao sellDao;

	@Autowired
	private BuyDao buyDao;

	
	@Autowired
	private StockDao stockDao;
	
	//
	@SuppressWarnings({ "rawtypes", "unchecked" })
	// @Scheduled(cron="30 02 15 * * ?")
	//@Scheduled(cron = "0/50 * * * * ?")
	@Scheduled(cron="36 02 15 ? * MON-FRI")
	public void taskForDoneDeal() throws Exception {
		List<Account> accounts = accountDao.find("from Account");
		//List<Buy> buys = buyDao.find("from Buy");
		if (accounts != null && accounts.size() > 0) {
			for (int j = 0; j < accounts.size(); j++) {
				Account ac = accounts.get(j);
				List list = stockTradeBus.currentOrder(ac);
				logger.debug("本日挂单记录====>" + list);
				Map<String, String> map = null;
				Orders orders = null;
				if(list!=null&&list.size()>0){
					for (int i = 0; i < list.size(); i++) {

						map = (Map) list.get(i);
						String SECU_CODE = map.get("SECU_CODE");
						String ORDER_AMT = map.get("ORDER_AMT");
						String ENTRUST_STATUS = map.get("ENTRUST_STATUS");
						String WITHDRAWN_QTY = map.get("WITHDRAWN_QTY");
						String exchange_type = map.get("exchange_type");
						String TRD_ID = map.get("TRD_ID");
						String MATCHED_AMT = map.get("MATCHED_AMT");
						String ACCOUNT = map.get("ACCOUNT");
						String QTY = map.get("QTY");
						String OP_REMARK = map.get("OP_REMARK");
						String SECU_ACC = map.get("SECU_ACC");
						String VALID_FLAG = map.get("VALID_FLAG");
						String MARKET = map.get("MARKET");
						String PRICE = map.get("PRICE");
						String MATCHED_PRICE = map.get("MATCHED_PRICE");
						String MATCHED_QTY = map.get("MATCHED_QTY");
						String SECU_NAME = map.get("SECU_NAME");
						String RECALL_TAG = map.get("RECALL_TAG");
						String ENTRUST_STATUS_NUM = map.get("ENTRUST_STATUS_NUM");
						String TRD_NAME = map.get("TRD_NAME");
						String ORDER_ID = map.get("ORDER_ID");
						String TRD_DIRE_NUM = map.get("TRD_DIRE_NUM");
						String MARKET_NAME = map.get("MARKET_NAME");
						String IS_WITHDRAW = map.get("IS_WITHDRAW");
						String VALID_FLAG_NAME = map.get("VALID_FLAG_NAME");
						String ORDER_TIME = map.get("ORDER_TIME");

						orders = new Orders();
						orders.setSecu_code(SECU_CODE);
						orders.setAccount(ACCOUNT);
						orders.setOrder_amt(ORDER_AMT);
						orders.setEntrust_status(ENTRUST_STATUS);
						orders.setWithdrawn_qty(WITHDRAWN_QTY);
						orders.setExchange_type(exchange_type);
						orders.setTrd_id(TRD_ID);
						orders.setMatched_amt(MATCHED_AMT);
						orders.setOp_remark(OP_REMARK);
						orders.setSecu_acc(SECU_ACC);
						orders.setValid_flag(VALID_FLAG);
						orders.setMarket(MARKET);
						orders.setPrice(PRICE);
						orders.setMatched_price(MATCHED_PRICE);
						orders.setMatched_qty(MATCHED_QTY);
						orders.setQty(QTY);
						orders.setSecu_name(SECU_NAME);

						orders.setRecall_tag(RECALL_TAG);
						orders.setEntrust_status_num(ENTRUST_STATUS_NUM);
						orders.setTrd_name(TRD_NAME);
						orders.setOrder_id(ORDER_ID);
						orders.setTrd_dire_num(TRD_DIRE_NUM);
						orders.setMarket_name(MARKET_NAME);
						orders.setIs_withdraw(IS_WITHDRAW);
						orders.setValid_flag_name(VALID_FLAG_NAME);
						orders.setOrder_time(ORDER_TIME);
						
						orders.setCreateTime(new Date().toLocaleString());

						String status_name = ENTRUST_STATUS;
						String business_amount = QTY;
						String code = SECU_CODE;
						String nameStr = SECU_NAME;
						String bs_name = TRD_NAME;
						String entrust_price = PRICE;
						if (nameStr.contains("Ａ")) {
							nameStr = nameStr.replace("Ａ", "A");
						}

						if (nameStr.contains("Ｒ")) {
							nameStr = nameStr.replace("Ｒ", "R");
						}

						YePinYinUtils.convertChineseToPinyin(nameStr);
						String headP = YePinYinUtils.getHeadPinyin();
						orders.setPinyin(headP);

						Sell sell = null;
						Integer buyNum = Integer.valueOf(business_amount);
						if ("全部成交".equals(status_name) && "买入".equals(bs_name)) {
							sell = new Sell();
							sell.setCode(code);
							sell.setName(SECU_NAME);
								
							if (code.indexOf("000") == 0 || code.indexOf("001") == 0) {
								sell.setExchangeType("2");
								sell.setMarketType("4609");
							} else if (code.indexOf("002") == 0) {
								sell.setExchangeType("2");
								sell.setMarketType("4614");
							} else if (code.indexOf("300") == 0) {
								sell.setExchangeType("2");
								sell.setMarketType("4621");
							} else if (code.indexOf("60") == 0) {
								sell.setExchangeType("1");
								sell.setMarketType("4353");
							}
							sell.setYuSheSellPrice("0");

							sell.setBuyPrice(entrust_price);

							
							sellDao.update(sell);
						} else if ("全部成交".equals(status_name) && "卖出".equals(bs_name)) {
							sell = new Sell();
							sell.setCode(code);
							sell.setName(SECU_NAME);
							sellDao.delete(sell);
						}
						
						
						if ("全部撤单".equals(status_name)){
							if(QTY.equals("100")&&!code.equals("601988")){
								Stock sts = stockDao.find("from Stock  where code = ?",code).get(0);
								Buy buy = new Buy();
								buy.setCode(code);
								buy.setName(sts.getName());
								
								buy.setExchangeType(sts.getExchangeType());
								buy.setMarketType(sts.getMarketType());
								buy.setHangye(sts.getHangye());
								buy.setLiuTongGu(sts.getLiuTongGu());
								buy.setDiQu(sts.getDiQu());
								buy.setZongGuBen(sts.getZongGuBen());
								buy.setShiyinglvJing(sts.getShiyinglvJing());
								buy.setPrefix(sts.getPrefix());
								buy.setPinyin(sts.getPinyin());
								
								buyDao.update(buy);
							}
							
							
						}
						
						ordersDao.update(orders);

					}
				}
				
				
			}
		}
	}
}
