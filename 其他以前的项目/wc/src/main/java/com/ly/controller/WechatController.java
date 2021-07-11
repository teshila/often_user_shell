package com.ly.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weichat.acess.sign.SignUtil;
import com.weichat.reply.MessgageReplyService;

@RequestMapping("/wechat")
@Controller
public class WechatController {
	private static Logger logger = Logger.getLogger(WechatController.class);
	
	@Autowired
	MessgageReplyService wechatService;
	
	//https://www.cnblogs.com/accumulater/p/6151953.html?utm_source=itdadao&utm_medium=referral
    //eb360c1826a20ebfd576fdb66deb2626
    //https://blog.csdn.net/qq594913801/article/details/79542994
	//String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2de043826bd11f5b&secret=eb360c1826a20ebfd576fdb66deb2626";
	
/*	@RequestMapping(value = "/wx_check")
	public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
		logger.error("WechatController   ----   WechatController");
 
		System.out.println("========WechatController========= ");
		logger.info("请求进来了...");
 
//		Enumeration pNames = request.getParameterNames();
//		while (pNames.hasMoreElements()) {
//			String name = (String) pNames.nextElement();
//			String value = request.getParameter(name);
//			// out.print(name + "=" + value);
// 
//			String log = "name =" + name + "     value =" + value;
//			logger.error(log);
//		}
 
		String signature = request.getParameter("signature");/// 微信加密签名
		String timestamp = request.getParameter("timestamp");/// 时间戳
		String nonce = request.getParameter("nonce"); /// 随机数
		String echostr = request.getParameter("echostr"); // 随机字符串
		PrintWriter out = response.getWriter();
 
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
 
	}*/
	
	
	
	
    
    /**
     * 微信接入
     * @param wc
     * @return
     * @throws UnsupportedEncodingException 
     * @throws IOException 
     */
	@RequestMapping(value = "/connect", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public void connectWeixin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8"); // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
		response.setCharacterEncoding("UTF-8"); // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
			boolean isGet = request.getMethod().toLowerCase().equals("get");
			logger.debug("==============> " + isGet);

			if (isGet) {
				String signature = request.getParameter("signature");// 微信加密签名
				String timestamp = request.getParameter("timestamp");// 时间戳
				String nonce = request.getParameter("nonce");// 随机数
				String echostr = request.getParameter("echostr");// 随机字符串

				// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
				if (SignUtil.checkSignature(signature, timestamp, nonce)) {
					logger.info("Connect the weixin server is successful.");
					response.getWriter().write(echostr);
				} else {
					logger.error("Failed to verify the signature!");
				}
			} else {
				String respMessage = "异常消息！";

				try {
					respMessage = wechatService.weixinPost(request);
					// respMessage= new
					// String(respMessage.getBytes("iso-8859-1"),"utf-8");

					out.write(respMessage);
					logger.info("The request completed successfully");
					logger.info("to weixin server " + respMessage);
				} catch (Exception e) {
					logger.error("Failed to convert the message from weixin!");
				}

			}
		} catch (Exception e) {
			logger.error("Connect the weixin server is error.");
			logger.error(e.getMessage());
		} finally {
			out.close();
		}
	}
 
}
