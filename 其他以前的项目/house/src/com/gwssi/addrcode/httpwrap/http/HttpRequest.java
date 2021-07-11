package com.gwssi.addrcode.httpwrap.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.xpath.DefaultXPath;
import org.springframework.stereotype.Component;

@Component
public class HttpRequest {
	public String sendSoapPost(String xml, String nodeName) {
		String urlString = "http://61.144.226.124:7890/czwSearchAdmin/services/searchTeleWS";
		HttpURLConnection httpConn = null;
		OutputStream out = null;
		String returnXml = "";
		String s = "";
		try {
			httpConn = (HttpURLConnection) new URL(urlString).openConnection();
			httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			httpConn.setRequestProperty("SOAPAction", "");
			httpConn.setRequestMethod("POST");
			/*setConnectTimeout：设置连接主机超时（单位：毫秒） 
			setReadTimeout：设置从主机读取数据超时（单位：毫秒）*/
			httpConn.setConnectTimeout(10000); 
			httpConn.setReadTimeout(10000); 
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.connect();
			out = httpConn.getOutputStream(); // 获取输出流对象
			httpConn.getOutputStream().write(xml.getBytes("UTF-8")); // 将要提交服务器的SOAP请求字符流写入输出流
			out.flush();
			out.close();
			int code = httpConn.getResponseCode(); // 用来获取服务器响应状态
			String tempString = null;
			StringBuffer sb1 = new StringBuffer();
			if (code == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((tempString = reader.readLine()) != null) {
					sb1.append(tempString);
				}
				if (null != reader) {
					reader.close();
				}
			} else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getErrorStream(), "UTF-8"));
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {
					sb1.append(tempString);
				}
				if (null != reader) {
					reader.close();
				}
			}
			// 响应报文
			returnXml = sb1.toString();
			
			if(null!=returnXml&&StringUtils.isNotEmpty(returnXml)){
				Document doc = DocumentHelper.parseText(returnXml);
				DefaultXPath xpath = new DefaultXPath("//" + nodeName);
				// 下面的wsdl文件地址需要自己更改，返回数据中一般都有
				// xpath.setNamespaceURIs(Collections.singletonMap("ns1","http://192.168.1.3:58080/BankYktWebSrv.wsdl"));

				List list = xpath.selectNodes(doc);
				Iterator iterator = list.iterator();
				while (iterator.hasNext()) {
					Element node = (Element) iterator.next();
					s = node.getText();
				}
				
			}
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return s;
		}
		
		
	}

}
