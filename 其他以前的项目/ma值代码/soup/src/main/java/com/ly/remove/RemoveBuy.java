package com.ly.remove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.ly.dao.impl.Stock_Shou_Yang_Day_And_Week_Shou_YangDao;
import com.ly.dao.impl.Stock_Shou_Yang_One_DayDao;
import com.ly.dao.impl.Stock_Shou_Yang_Only_Week_Shou_YangDao;
import com.ly.dao.impl.Stock_Shou_Yang_Three_DayDao;
import com.ly.dao.impl.Stock_Shou_Yang_Two_DayDao;

@EnableAsync
@Component
public class RemoveBuy {
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
	
	/*@Async // 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行
	public void taskAdd(String code) {
		onlyWeekShouYangDao.updateByHql("update Stock_Shou_Yang_Only_Week_Shou_Yang set isAddToBuy = 0", params);
	}*/
	
}
