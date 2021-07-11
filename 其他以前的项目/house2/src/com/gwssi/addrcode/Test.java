package com.gwssi.addrcode;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gwssi.addrcode.http.HttpRequest;

public class Test {

	
/*	public static void main(String[] args) throws DocumentException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml"); 
		
		AddrInfoService as = ac.getBean(AddrInfoService.class);
		
		
		as.getAddrInfo("12356");
		
		
	}*/
	
	
	public static void main(String[] args) {
		String url = "http://61.144.226.124:7890/czwSearchAdmin/services/searchTeleWS";
 
		StringBuilder sb = new StringBuilder("");

		sb.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='service.webservice.audaque.com'>");
		sb.append("<soapenv:Header/>");
		sb.append("<soapenv:Body>");
		sb.append("<ser:Houses_MoreAddress>");
		sb.append("<key>1VU4F0JGL8DKCOJ5</key>");
		sb.append("<name></name>");
		sb.append("<code>4403060070043400005000379</code>");
		sb.append("<page>1</page>");
		sb.append("<rows>100</rows>");
		sb.append("</ser:Houses_MoreAddress>");
		sb.append("</soapenv:Body>");
		sb.append("</soapenv:Envelope>");
		
		
		String dataXml = sb.toString();
		String contentType = "text/xml; charset=utf-8";
		String soapAction = "";
		//String resultXml = HttpRequest.sendSoapPost(url, dataXml, contentType,soapAction);
		//System.out.println(resultXml);
		
		//getWebservicesBody(resultXml,"Houses_MoreAddress");
	}
}
