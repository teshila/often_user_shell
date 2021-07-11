package com.ly.vuecontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ly.common.bean.BeanUtils;
import com.ly.dao.Page;
import com.ly.dao.impl.EmailRecevierDao;
import com.ly.dao.impl.EmailRecevierLogDao;
import com.ly.dao.impl.EmailSenderDao;
import com.ly.placeholder.DESUtils;
import com.ly.pojo.Email_Recevier;
import com.ly.pojo.Email_Recevier_Log;
import com.ly.pojo.Email_Sender;
import com.ly.vo.EmailReceverBean;


@RestController
@RequestMapping("pro")
public class EmailController {

	@Autowired
	private EmailRecevierDao emailRecevierDao;
	@Autowired
	private EmailSenderDao emailSenderDao;
	
	@Autowired
	private EmailRecevierLogDao emailRecevierLogDao;
	
	@RequestMapping("/getEmailRecevierList")
	public Page<EmailReceverBean> getInfo(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT t.address, t.NAME, t.isSend, COUNT(t.msg) AS times ");
		sb.append("FROM ( ");
		sb.append("SELECT t1.address, t1.NAME, t1.isSend, t1.addTime, t2.msg ");
		sb.append("FROM Email_Recevier t1 ");
		sb.append("LEFT JOIN Email_Recevier_Log t2 ON t1.address = t2.address ");
		sb.append(") t ");
		sb.append("GROUP BY t.address ");
		sb.append("ORDER BY t.addTime ");
		
		NativeQuery query = emailRecevierDao.getCurrentSession().createNativeQuery(sb.toString());;
		
		List results = emailRecevierDao.getCurrentSession().createNativeQuery(sb.toString()).getResultList();;
		
		List<EmailReceverBean> returnList = new ArrayList();
		EmailReceverBean myBean = null;
		for (Object object : results) {
			Object [] obj = (Object[]) object;
			myBean = new EmailReceverBean();
			myBean.setAddress(obj[0].toString());
			
			if(null!=obj[1]){
				myBean.setName(obj[1].toString());
			}
			
			if(null!=obj[2]){
				myBean.setIsSend(obj[2].toString());
			}
			
			if(null!=obj[3]){
				myBean.setTimes(obj[3].toString());
			}
			returnList.add(myBean);
		}
		
		int total  = (int) emailRecevierDao.findCount(Email_Recevier.class);
		
		Page<EmailReceverBean> pages = new Page<EmailReceverBean>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount(total);
		pages.setPageList(returnList);
		return pages;
	} 
	
	
	@RequestMapping("/getEmailSenderList")
	public Page<Email_Sender> getEmail_Sender(String pageNum,String pageSize){
		int pageNo = 0;
		int row = 10;
		
		if(!StringUtils.isEmpty(pageNum)){
			pageNo = Integer.valueOf(pageNum);
		}
		
		if(!StringUtils.isEmpty(pageSize)){
			row = Integer.valueOf(pageSize);
		}
		List list = emailSenderDao.findByPage("from Email_Sender ", pageNo, row);
		int total  = (int) emailSenderDao.findCount(Email_Sender.class);
		
		Page<Email_Sender> pages = new Page<Email_Sender>();
		pages.setPageNo(pageNo/row+1);
		pages.setStart(pageNo);
		pages.setTotalCount(total);
		pages.setPageList(list);
		return pages;
	} 
	
	
	
	
	@RequestMapping("/delEmailSender")
	public Map delEmailSender(Email_Recevier accWeb){
		Map<String,String> map = new HashMap<String,String>();
		if(accWeb!=null&&StringUtils.isNotEmpty(accWeb.getAddress())){
			try {
				String address = accWeb.getAddress();
				emailSenderDao.delete(Email_Sender.class, address);
			} catch (Exception e) {
				map.put("msg", "保存或更新失败，请稍后重试，失败原因==>" + e.getMessage());
			}
		}else{
			map.put("msg", "保存或更新失败，地址不能为空");
		}
		return map;
	}
	
	@RequestMapping("/addEmailSender")
	public Map addAccount(Email_Sender accWeb){
		Map<String,String> map = new HashMap<String,String>();
		if(accWeb!=null&&StringUtils.isNotEmpty(accWeb.getAddress())){
			try {
				String address = accWeb.getAddress();
				List<Email_Sender> list  = emailSenderDao.find("from Email_Sender t where t.address = ?", address);
				if(list!=null&&list.size()>0){
					Email_Sender ac  = list.get(0);
					accWeb.setPwd(DESUtils.getEncryptString(accWeb.getPwd()));
					BeanUtils.copyProperties(accWeb, ac);
					emailSenderDao.update(ac);
					map.put("msg", "保存成功");
				}else{
					accWeb.setPwd(DESUtils.getEncryptString(accWeb.getPwd()));
					emailSenderDao.save(accWeb);
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
	
	

	@RequestMapping("/updateDefaultSender")
	public Map updateDefaulSender(Email_Sender accWeb){
		Map<String,String> map = new HashMap<String,String>();
		if(accWeb!=null&&StringUtils.isNotEmpty(accWeb.getAddress())){
			String address = accWeb.getAddress();
			try {
				emailSenderDao.updateDefault(address);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			map.put("msg", "保存或更新失败，默认地址不能为空");
		}
		return map;
	}
	
	
	
	@RequestMapping("/addEmailRecevier")
	public Map addEmailRecevier(Email_Recevier accWeb){
		Map<String,String> map = new HashMap<String,String>();
		if(accWeb!=null&&StringUtils.isNotEmpty(accWeb.getAddress())){
			try {
				String address = accWeb.getAddress();
				List<Email_Recevier> list  = emailRecevierDao.find("from Email_Recevier t where t.address = ?", address);
				if(list!=null&&list.size()>0){
					Email_Recevier ac  = list.get(0);
					BeanUtils.copyProperties(accWeb, ac);
					emailRecevierDao.update(ac);
					map.put("msg", "保存成功");
				}else{
					accWeb.setIsSend("1");
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
	
	
	@RequestMapping("/delEmailRecevier")
	public Map deleEmailRecevier(Email_Recevier accWeb){
		Map<String,String> map = new HashMap<String,String>();
		if(accWeb!=null&&StringUtils.isNotEmpty(accWeb.getAddress())){
			try {
				String address = accWeb.getAddress();
				emailRecevierDao.delete(Email_Recevier.class, address);
			} catch (Exception e) {
				map.put("msg", "保存或更新失败，请稍后重试，失败原因==>" + e.getMessage());
			}
		}else{
			map.put("msg", "保存或更新失败，地址不能为空");
		}
		return map;
	}
	
	
	
	@RequestMapping("/getLogByAddress")
	public Map<String, List<Email_Recevier_Log>> getLogByAddress(String address) {
		Map params = new HashMap();
		params.put("address", address);
		List<Email_Recevier_Log> list = emailRecevierLogDao.find("from Email_Recevier_Log t where t.address = ? ", address);
		Map<String, List<Email_Recevier_Log>> maps = new HashMap<String, List<Email_Recevier_Log>>();
		if (list != null && list.size() > 0) {
			maps.put("list", list);
		} else {
			maps.put("list", null);
		}
		return maps;
	}
	

	
}
