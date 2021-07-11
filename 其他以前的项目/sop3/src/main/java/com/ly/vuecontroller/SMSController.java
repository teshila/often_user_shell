package com.ly.vuecontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ly.common.bean.BeanUtils;
import com.ly.common.util.UUIDUtils;
import com.ly.dao.Page;
import com.ly.dao.impl.SMS_RecevierDao;
import com.ly.dao.impl.SMS_SenderDao;
import com.ly.pojo.Email_Sender;
import com.ly.pojo.SMS_Recevier;
import com.ly.pojo.SMS_Sender;
import com.ly.vo.SMSReceverBean;

@RestController
@RequestMapping("pro")
public class SMSController {
	

	@Autowired
	private SMS_RecevierDao emailRecevierDao;
	@Autowired
	private SMS_SenderDao emailSenderDao;
	
	@RequestMapping("/getSMSRecevierList")
	public Page<SMSReceverBean> getInfo(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT t.id,t.phone, t.NAME, t.isSend, COUNT(t.msg) AS times ");
		sb.append("FROM ( ");
		sb.append("SELECT t1.id, t1.phone, t1.NAME, t1.isSend, t1.addTime, t2.msg ");
		sb.append("FROM SMS_Recevier t1 ");
		sb.append("LEFT JOIN SMS_Recevier_Log t2 ON t1.phone = t2.phone ");
		sb.append(") t ");
		sb.append("GROUP BY t.phone ");
		sb.append("ORDER BY t.addTime ");
		
		//NativeQuery query = emailRecevierDao.getCurrentSession().createNativeQuery(sb.toString());;
		
		List results = emailRecevierDao.getCurrentSession().createNativeQuery(sb.toString()).getResultList();;
		List<SMSReceverBean> returnList = new ArrayList();
		int total  = (int) emailRecevierDao.findCount(SMS_Recevier.class);
		
		SMSReceverBean myBean = null;
		for (Object object : results) {
			Object [] obj = (Object[]) object;
			myBean = new SMSReceverBean();
			
			myBean.setId(obj[0].toString());
			
			String phone = obj[1].toString();
			phone = phone.substring(0,3)+"****" + phone.substring(phone.length()-4,phone.length());
			
			
			myBean.setPhone(phone);
			
			if(null!=obj[2]){
				myBean.setName(obj[2].toString());
			}
			//if(null!=obj[2]){
			myBean.setIsSend(obj[3].toString());
			//}
			//if(null!=obj[3]){
			myBean.setTimes(obj[4].toString());
			//}
			
			returnList.add(myBean);
		}
		
		
		
		Page<SMSReceverBean> pages = new Page<SMSReceverBean>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount(total);
		pages.setPageList(returnList);
		return pages;
	} 
	
	
	
	

	@RequestMapping("/getSMSSenderList")
	public Page<SMS_Sender> getEmail_Sender(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		List<SMS_Sender> list = emailSenderDao.findByPage("from SMS_Sender ", pageNo, row);
		int total  = (int) emailSenderDao.findCount(SMS_Sender.class);
		
		List<SMS_Sender> returnList = new ArrayList();
		
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				SMS_Sender ac  = list.get(i);
				String appid = ac.getAppId();
				appid = appid.substring(0,3)+"****" + appid.substring(appid.length()-4,appid.length());
				ac.setAppId(appid);
				
				
				String appKey = ac.getAppKey();
				appKey =appKey.substring(0,3)+"****" + appKey.substring(appKey.length()-4,appKey.length());
				ac.setAppKey(appKey);
				
				
				String tempBuy = ac.getTemplateIdForBuy();
				tempBuy =tempBuy.substring(0,3)+"****" + tempBuy.substring(tempBuy.length()-4,tempBuy.length());
				ac.setTemplateIdForBuy(tempBuy);
				
				String tempSell = ac.getTemplateIdForSell();
				tempSell =tempSell.substring(0,3)+"****" + tempSell.substring(tempSell.length()-4,tempSell.length());
				ac.setTemplateIdForSell(tempSell);
				
				returnList.add(ac);
			}
	 		
		}
		
		Page<SMS_Sender> pages = new Page<SMS_Sender>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount(total);
		pages.setPageList(returnList);
		return pages;
	} 
	
	
	
	

	@RequestMapping("/addSMSRecevier")
	public Map addSMSRecevier(SMS_Recevier accWeb){
		Map<String,String> map = new HashMap<String,String>();
		if(accWeb!=null&&StringUtils.isNotEmpty(accWeb.getPhone())){
			try {
				String address = accWeb.getPhone();
				List<SMS_Recevier> list  = emailRecevierDao.find("from SMS_Recevier t where t.id = ?", address);
				if(list!=null&&list.size()>0){
					SMS_Recevier ac  = list.get(0);
					BeanUtils.copyProperties(accWeb, ac);
					emailRecevierDao.update(ac);
					map.put("msg", "保存成功");
				}else{
					accWeb.setIsSend("1");
					String uuid = UUIDUtils.getUUID();
					accWeb.setId(uuid);
					accWeb.setAddtime(new Date().toLocaleString());
					emailRecevierDao.save(accWeb);
					map.put("msg", "保存成功");
				}
			} catch (Exception e) {
				map.put("msg", "保存或更新失败，请稍后重试，失败原因==>" + e.getMessage());
			}
		}else{
			map.put("msg", "保存或更新失败，地址不能为空");
		}
		return map;
	}
	
	
	@RequestMapping("/delSMSRecevier")
	public Map deleEmailRecevier(SMS_Recevier accWeb){
		Map<String,String> map = new HashMap<String,String>();
		if(accWeb!=null&&StringUtils.isNotEmpty(accWeb.getId())){
			try {
				String address = accWeb.getId();
				emailRecevierDao.delete(SMS_Recevier.class, address);
			} catch (Exception e) {
				map.put("msg", "保存或更新失败，请稍后重试，失败原因==>" + e.getMessage());
			}
		}else{
			map.put("msg", "保存或更新失败，地址不能为空");
		}
		return map;
	}
	
	
}
