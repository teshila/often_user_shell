package com.gwssi.addrcode.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gwssi.addrcode.bus.HouseService;
import com.gwssi.addrcode.dao.IPDao;

@RestController
public class GetHouseController {
	private static Logger logger = Logger.getLogger(GetHouseController.class);
	@Autowired
	private HouseService houseService;
	
	
	@Autowired
	private IPDao ipDao;


	@RequestMapping("data2")
	@ResponseBody
	public Map getInfo(String name, String code,HttpServletRequest  req) {
		long startTime = System.currentTimeMillis();
		HashMap map = new HashMap();
		Map ipMap = new HashMap();
		String remoteAddr = getIpPropAddr(req);
		logger.debug(remoteAddr);
		String time = new Date().toLocaleString();
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		ipMap.put("ipuid", uuid);
		ipMap.put("ipadd", remoteAddr);
		ipMap.put("vtime", time);
		
		if (StringUtils.isEmpty(name)) {
			name = "";
		}
		if (StringUtils.isEmpty(code)) {
			code = "";
		}
		
		try {
			List list = houseService.getAddrInfo(name, code);
			long endTime = System.currentTimeMillis();
			double time2 =  endTime-startTime;
			logger.info("请求结束时间  " + time2 + ",耗时 " + (time2/1000)+"秒");
			map.put("code", "0");
			map.put("msg", "获取数据成功");
			map.put("data", list);
			ipDao.save(ipMap);
		} catch (Exception e) {
			map.put("code", "-1");
			map.put("msg", "获取数据失败");
			map.put("data", "");
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	
	public  String getIpPropAddr(HttpServletRequest request) {

		// String ipAddress = request.getHeader("XMQSIP");
		String ipAddress = request.getHeader("remote_addr");

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("remote_addr");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Forwarded-For");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Real-Forwarded-For");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("X-Connecting-Ip");
		}

		// 深信服
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("XMQSIP");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("REMOTE_ADDR");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("x-forwarded-for");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}
