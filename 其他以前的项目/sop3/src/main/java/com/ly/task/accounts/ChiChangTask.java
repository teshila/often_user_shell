package com.ly.task.accounts;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ly.core.StockTradeCore;
import com.ly.dao.impl.AccountDao;
import com.ly.dao.impl.ChiCangDao;
import com.ly.dao.impl.StockDao;
import com.ly.pojo.Account;
import com.ly.pojo.Chi_Cang;
import com.ly.pojo.Stock;


@Component
public class ChiChangTask {
	//private static Logger logger = Logger.getLogger(ChiChangTask.class);
	private static final Logger logger = Logger.getLogger("chicang");

	@Autowired
	private ChiCangDao chiCangDao;

	@Autowired
	private StockTradeCore stockTradeBus;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private StockDao stockImpl;

	//https://jingyan.baidu.com/article/73c3ce28558e2ae50343d91a.html  git使用方法
	//"0 15 10 * * ?"
	//每日凌晨更新持仓数量
	//
	//@Scheduled(cron = "0 0 9 ? * MON-FRI")
	//@Scheduled(cron = "0/30 * * * * ?")
	@Scheduled(cron = "0 30 9,23 ? * *")
	public void task() throws Exception {
		logger.debug("定时更新当前账户的持仓信息");
		Chi_Cang cc  = null;
		List<Account> accounts = accountDao.find("from Account");
		if (accounts != null && accounts.size() > 0) {
			for (int i = 0; i < accounts.size(); i++) {
				Account ac = accounts.get(i);
				List listsMap = stockTradeBus.getChiChang(ac);
				logger.debug("股东代码==> " + ac.getAccount() + ",户名==> "  + ac.getName() +"的股东账户持仓情况===> " + listsMap);
				if(listsMap!=null&&listsMap.size()>0){
					for (int j = 0; j < listsMap.size()-1; j++) {
						Map<String,String> temp= (Map) listsMap.get(j);
						cc = new Chi_Cang();
						String currency = temp.get("CURRENCY");//人民币
						String stock_code = temp.get("SECU_CODE");//
						String stock_name = temp.get("SECU_NAME");
						String seat = temp.get("SEAT");
						String day_profit_loss = temp.get("DAY_PROFIT_LOSS");
						String currentPrice = temp.get("CURR_PRICE");
						String share_avl = temp.get("SHARE_AVL");
						String total_profit_loss_prop = temp.get("TOTAL_PROFIT_LOSS_PROP");
						String mkt_qty = temp.get("MKT_QTY");
						String day_profit_loss_prop = temp.get("DAY_PROFIT_LOSS_PROP");
						String exchange_type = temp.get("EXCHANGE_TYPE");
						String total_profit_loss = temp.get("TOTAL_PROFIT_LOSS");
						String secu_cls = temp.get("SECU_CLS");
						String account = temp.get("ACCOUNT");
						String secu_acc = temp.get("SECU_ACC");
						String cost_price = temp.get("COST_PRICE");
						String mkt_val = temp.get("MKT_VAL");
						
						
						
						List<Stock> sbLists = stockImpl.find("from Stock where code = ? ",stock_code);
						cc.setSecu_code(stock_code);
						cc.setSecu_name(stock_name);
						cc.setCode(stock_code);
						cc.setName(stock_name);
						cc.setSeat(seat);
						cc.setDay_profit_loss(day_profit_loss);
						cc.setDay_profit_loss_prop(day_profit_loss_prop);
						cc.setShare_avl(share_avl);
						cc.setMkt_qty(mkt_qty);
						cc.setMkt_val(mkt_val);
						cc.setAccount(account);
						cc.setTotal_profit_loss(total_profit_loss);
						cc.setTotal_profit_loss_prop(total_profit_loss_prop);
						cc.setExchangeType(exchange_type);
						cc.setSecu_acc(secu_acc);
						cc.setCurrency(currency);
						cc.setSecu_cls(secu_cls);
						
						cc.setCurr_price(currentPrice);
						cc.setCost_price(cost_price);
						
						cc.setAccount(ac.getAccount());
						cc.setAccountname(ac.getName());
						
						if(sbLists!=null&&sbLists.size()>0){
							Stock sts = sbLists.get(0);
							
							cc.setPinyin(sts.getPinyin());
							cc.setPrefix(sts.getPrefix());
							
							cc.setHangye(sts.getHangye());
							cc.setDiQu(sts.getDiQu());
							cc.setShiyinglvJing(sts.getShiyinglvJing());
							cc.setZongGuBen(sts.getZongGuBen());
							cc.setLiuTongGu(sts.getLiuTongGu());
							cc.setExchangeType(sts.getExchangeType());
							
							cc.setMarketType(sts.getMarketType());
						}
						
						
						
						chiCangDao.update(cc);
						
					}
				
				}
				
				Thread.sleep(1000*10);
			}
		}
		
	}

	
	/*
	{"errmsg":"查询 我的持仓成功","results":[{"income_balance_ratio":"-31.33","stock_code":"000576","stock_name":"广东甘化","current_amount":"100","enable_amount":"100","last_price":"7.190","cost_price":"10.471","keep_cost_price":null,"income_balance":"-328.05","market_value":"719.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-94.38","stock_code":"000758","stock_name":"中色股份","current_amount":"100","enable_amount":"100","last_price":"3.990","cost_price":"71.034","keep_cost_price":null,"income_balance":"-6704.39","market_value":"399.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-72.19","stock_code":"000979","stock_name":"中弘退","current_amount":"64500","enable_amount":"64500","last_price":"0.260","cost_price":"0.935","keep_cost_price":null,"income_balance":"-43525.07","market_value":"16770.00","position_str":null,"hand_flag":null,"enroute_amount":"64500"},{"income_balance_ratio":"-92.18","stock_code":"000983","stock_name":"西山煤电","current_amount":"100","enable_amount":"100","last_price":"6.150","cost_price":"78.664","keep_cost_price":null,"income_balance":"-7251.38","market_value":"615.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-49.85","stock_code":"002143","stock_name":"印纪传媒","current_amount":"100","enable_amount":"100","last_price":"3.430","cost_price":"6.840","keep_cost_price":null,"income_balance":"-340.99","market_value":"343.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-64.04","stock_code":"002184","stock_name":"海得控制","current_amount":"100","enable_amount":"100","last_price":"10.860","cost_price":"30.197","keep_cost_price":null,"income_balance":"-1933.73","market_value":"1086.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-84.24","stock_code":"002329","stock_name":"皇氏集团","current_amount":"100","enable_amount":"100","last_price":"3.980","cost_price":"25.249","keep_cost_price":null,"income_balance":"-2126.88","market_value":"398.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-84.94","stock_code":"002388","stock_name":"新亚制程","current_amount":"100","enable_amount":"100","last_price":"5.840","cost_price":"38.772","keep_cost_price":null,"income_balance":"-3293.23","market_value":"584.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-28.05","stock_code":"002663","stock_name":"普邦股份","current_amount":"400","enable_amount":"0","last_price":"2.750","cost_price":"3.822","keep_cost_price":null,"income_balance":"-428.72","market_value":"1100.00","position_str":null,"hand_flag":null,"enroute_amount":"400"},{"income_balance_ratio":"2.12","stock_code":"600127","stock_name":"金健米业","current_amount":"0","enable_amount":"0","last_price":"3.230","cost_price":"3.163","keep_cost_price":null,"income_balance":"127.88","market_value":"6137.00","position_str":null,"hand_flag":null,"enroute_amount":"1900"},{"income_balance_ratio":"-72.27","stock_code":"600209","stock_name":"*ST罗顿","current_amount":"100","enable_amount":"100","last_price":"3.710","cost_price":"13.378","keep_cost_price":null,"income_balance":"-966.81","market_value":"371.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-57.90","stock_code":"600249","stock_name":"两面针","current_amount":"100","enable_amount":"100","last_price":"4.280","cost_price":"10.167","keep_cost_price":null,"income_balance":"-1177.40","market_value":"856.00","position_str":null,"hand_flag":null,"enroute_amount":"200"},{"income_balance_ratio":"-62.29","stock_code":"600335","stock_name":"国机汽车","current_amount":"100","enable_amount":"100","last_price":"8.120","cost_price":"21.534","keep_cost_price":null,"income_balance":"-1341.40","market_value":"812.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-48.58","stock_code":"600712","stock_name":"南宁百货","current_amount":"100","enable_amount":"100","last_price":"4.260","cost_price":"8.284","keep_cost_price":null,"income_balance":"-402.42","market_value":"426.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-73.94","stock_code":"600731","stock_name":"湖南海利","current_amount":"100","enable_amount":"100","last_price":"4.820","cost_price":"18.497","keep_cost_price":null,"income_balance":"-1367.71","market_value":"482.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-45.61","stock_code":"601068","stock_name":"中铝国际","current_amount":"200","enable_amount":"200","last_price":"5.670","cost_price":"10.425","keep_cost_price":null,"income_balance":"-951.04","market_value":"1134.00","position_str":null,"hand_flag":null,"enroute_amount":"200"},{"income_balance_ratio":"-84.43","stock_code":"601330","stock_name":"绿色动力","current_amount":"100","enable_amount":"100","last_price":"13.480","cost_price":"86.574","keep_cost_price":null,"income_balance":"-7309.35","market_value":"1348.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-65.15","stock_code":"603336","stock_name":"宏辉果蔬","current_amount":"100","enable_amount":"100","last_price":"18.560","cost_price":"53.254","keep_cost_price":null,"income_balance":"-3469.35","market_value":"1856.00","position_str":null,"hand_flag":null,"enroute_amount":"100"}],"status":1}{"errmsg":"查询 我的持仓成功","results":[{"income_balance_ratio":"-31.33","stock_code":"000576","stock_name":"广东甘化","current_amount":"100","enable_amount":"100","last_price":"7.190","cost_price":"10.471","keep_cost_price":null,"income_balance":"-328.05","market_value":"719.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-94.38","stock_code":"000758","stock_name":"中色股份","current_amount":"100","enable_amount":"100","last_price":"3.990","cost_price":"71.034","keep_cost_price":null,"income_balance":"-6704.39","market_value":"399.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-72.19","stock_code":"000979","stock_name":"中弘退","current_amount":"64500","enable_amount":"64500","last_price":"0.260","cost_price":"0.935","keep_cost_price":null,"income_balance":"-43525.07","market_value":"16770.00","position_str":null,"hand_flag":null,"enroute_amount":"64500"},{"income_balance_ratio":"-92.18","stock_code":"000983","stock_name":"西山煤电","current_amount":"100","enable_amount":"100","last_price":"6.150","cost_price":"78.664","keep_cost_price":null,"income_balance":"-7251.38","market_value":"615.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-49.85","stock_code":"002143","stock_name":"印纪传媒","current_amount":"100","enable_amount":"100","last_price":"3.430","cost_price":"6.840","keep_cost_price":null,"income_balance":"-340.99","market_value":"343.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-64.04","stock_code":"002184","stock_name":"海得控制","current_amount":"100","enable_amount":"100","last_price":"10.860","cost_price":"30.197","keep_cost_price":null,"income_balance":"-1933.73","market_value":"1086.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-84.24","stock_code":"002329","stock_name":"皇氏集团","current_amount":"100","enable_amount":"100","last_price":"3.980","cost_price":"25.249","keep_cost_price":null,"income_balance":"-2126.88","market_value":"398.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-84.94","stock_code":"002388","stock_name":"新亚制程","current_amount":"100","enable_amount":"100","last_price":"5.840","cost_price":"38.772","keep_cost_price":null,"income_balance":"-3293.23","market_value":"584.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-28.05","stock_code":"002663","stock_name":"普邦股份","current_amount":"400","enable_amount":"0","last_price":"2.750","cost_price":"3.822","keep_cost_price":null,"income_balance":"-428.72","market_value":"1100.00","position_str":null,"hand_flag":null,"enroute_amount":"400"},{"income_balance_ratio":"2.12","stock_code":"600127","stock_name":"金健米业","current_amount":"0","enable_amount":"0","last_price":"3.230","cost_price":"3.163","keep_cost_price":null,"income_balance":"127.88","market_value":"6137.00","position_str":null,"hand_flag":null,"enroute_amount":"1900"},{"income_balance_ratio":"-72.27","stock_code":"600209","stock_name":"*ST罗顿","current_amount":"100","enable_amount":"100","last_price":"3.710","cost_price":"13.378","keep_cost_price":null,"income_balance":"-966.81","market_value":"371.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-57.90","stock_code":"600249","stock_name":"两面针","current_amount":"100","enable_amount":"100","last_price":"4.280","cost_price":"10.167","keep_cost_price":null,"income_balance":"-1177.40","market_value":"856.00","position_str":null,"hand_flag":null,"enroute_amount":"200"},{"income_balance_ratio":"-62.29","stock_code":"600335","stock_name":"国机汽车","current_amount":"100","enable_amount":"100","last_price":"8.120","cost_price":"21.534","keep_cost_price":null,"income_balance":"-1341.40","market_value":"812.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-48.58","stock_code":"600712","stock_name":"南宁百货","current_amount":"100","enable_amount":"100","last_price":"4.260","cost_price":"8.284","keep_cost_price":null,"income_balance":"-402.42","market_value":"426.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-73.94","stock_code":"600731","stock_name":"湖南海利","current_amount":"100","enable_amount":"100","last_price":"4.820","cost_price":"18.497","keep_cost_price":null,"income_balance":"-1367.71","market_value":"482.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-45.61","stock_code":"601068","stock_name":"中铝国际","current_amount":"200","enable_amount":"200","last_price":"5.670","cost_price":"10.425","keep_cost_price":null,"income_balance":"-951.04","market_value":"1134.00","position_str":null,"hand_flag":null,"enroute_amount":"200"},{"income_balance_ratio":"-84.43","stock_code":"601330","stock_name":"绿色动力","current_amount":"100","enable_amount":"100","last_price":"13.480","cost_price":"86.574","keep_cost_price":null,"income_balance":"-7309.35","market_value":"1348.00","position_str":null,"hand_flag":null,"enroute_amount":"100"},{"income_balance_ratio":"-65.15","stock_code":"603336","stock_name":"宏辉果蔬","current_amount":"100","enable_amount":"100","last_price":"18.560","cost_price":"53.254","keep_cost_price":null,"income_balance":"-3469.35","market_value":"1856.00","position_str":null,"hand_flag":null,"enroute_amount":"100"}],"status":1}
	 */
}
