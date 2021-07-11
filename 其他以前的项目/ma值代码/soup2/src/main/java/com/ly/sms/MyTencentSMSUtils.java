package com.ly.sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.ly.dao.impl.SMSSendLogDao;
import com.ly.dao.impl.SMS_SenderDao;
import com.ly.pojo.SMS_Recevier_Log;
import com.ly.pojo.SMS_Sender;

@Component
public class MyTencentSMSUtils {

	@Autowired
	private SMS_SenderDao smssenderdao;
	
	@Autowired
	private SMSSendLogDao smsLogDao;

	private static final Logger logger = Logger.getLogger("sendWeiTuoInfo");

	public SMS_Sender getBuyRemaind(List<String> phoneNumbers) {
		SMS_Sender smsSender = null;
		List<SMS_Sender> list = smssenderdao.find("from SMS_Sender");
		if(list!=null){
			smsSender = list.get(0);
			String ctime = smsSender.getTimes();
			Integer time = Integer.valueOf(ctime) - phoneNumbers.size();
			smsSender.setTimes(time + "");
			smssenderdao.update(smsSender);
			return smsSender;
		}else{
			return null;
		}
	}

	public void sendBuy(ArrayList<String> phoneNumbers, String[] params) {
		SMS_Sender sms = getBuyRemaind(phoneNumbers);
		SMS_Recevier_Log smsSendLog = null;
		if (sms!=null) {
			String appidStr = sms.getAppId(); // 1400开头
			String appkey = sms.getAppKey();
			String templateIdStr = sms.getTemplateIdForBuy();
			String smsSign = sms.getSign();
			int appid = Integer.valueOf(appidStr);
			int templateId = Integer.valueOf(templateIdStr);
			SmsSingleSenderResult result = null;
			logger.info("使用的签名  【" + smsSign + "】");
				SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
				for (int i = 0; i < phoneNumbers.size(); i++) {
					try {
						result = ssender.sendWithParam("86", phoneNumbers.get(i), templateId, params, smsSign, "", ""); // 签名参数未提供或者为空时，会使用默认签名发送短信
						logger.info("系统返回===>" + result + "  , 发送的号码为 " + phoneNumbers.get(i));
					}catch (Exception e) {
						// 网络IO错误
						logger.debug(e.getMessage());
						e.printStackTrace();
						
						smsSendLog = new SMS_Recevier_Log();
						smsSendLog.setMsg(e.getMessage());
						smsSendLog.setFlag("0");
						smsSendLog.setPhone(phoneNumbers.get(i));
						
						smsLogDao.save(smsSendLog);
					} 
				}

			} 
			
	}

	public void sendSell(ArrayList<String> phoneNumbers, String[] params) {
		SMS_Sender sms = getBuyRemaind(phoneNumbers);
		SMS_Recevier_Log smsSendLog = null;
		if (sms!=null) {
			String appidStr = sms.getAppId(); // 1400开头
			String appkey = sms.getAppKey();
			String templateIdStr = sms.getTemplateIdForSell();
			String smsSign = sms.getSign();
			int appid = Integer.valueOf(appidStr);
			int templateId = Integer.valueOf(templateIdStr);
			SmsSingleSenderResult result = null;
			logger.info("使用的签名  【" + smsSign + "】");
				SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
				for (int i = 0; i < phoneNumbers.size(); i++) {
					try{
						result = ssender.sendWithParam("86", phoneNumbers.get(i), templateId, params, smsSign, "", ""); // 签名参数未提供或者为空时，会使用默认签名发送短信
						logger.info("系统返回===>" + result);
					}catch (Exception e) {
						e.printStackTrace();
						logger.debug(e.getMessage());
						logger.debug(e.getMessage());
						e.printStackTrace();
						
						smsSendLog = new SMS_Recevier_Log();
						smsSendLog.setMsg(e.getMessage());
						smsSendLog.setFlag("0");
						smsSendLog.setPhone(phoneNumbers.get(i));
						smsSendLog.setTime(new Date().toLocaleString());
						
						smsLogDao.save(smsSendLog);
					} 
				}
		}
	}

}