package com.gwssi.addrcode.httpwrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gwssi.addrcode.httpwrap.http.HttpRequest;

/**
 * 地址信息核查接口
 * 	  通过房屋编码来精确查找房屋地址
 * 
 */

@Component
public class AddrInfoReqWrap{
	private static Logger logger = Logger.getLogger(AddrInfoReqWrap.class);
	
	@Autowired
	private HttpRequest httpRequest;
	//接口2
	public  List<Map> getAddrInfo(String name,String code) {
			List list = new ArrayList();
			try {
				StringBuilder sb = new StringBuilder("");
				sb.append("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:ser='service.webservice.audaque.com'>");
				sb.append("<soapenv:Header/>");
				sb.append("<soapenv:Body>");
				sb.append("<ser:Houses_MoreAddress>");
				sb.append("<key>1VU4F0JGL8DKCOJ5</key>");
				sb.append("<name>"+name+"</name>");
				sb.append("<code>"+code+"</code>");
				sb.append("<page>1</page>");
				sb.append("<rows>100</rows>");
				sb.append("</ser:Houses_MoreAddress>");
				sb.append("</soapenv:Body>");
				sb.append("</soapenv:Envelope>");
				
				String dataXml = sb.toString();
				String retStr = httpRequest.sendSoapPost(dataXml,"Houses_MoreAddress");
				logger.info("向网格办发起请求==>" + name +"  ===> " +code+ " , 返回的数据为 ：===> "  +retStr);
				/*JSONArray arrays =  JSONObject.parseArray(retStr);
				JSONObject object = JSONObject.parseObject(arrays.get(0).toString());
				
				
				List<Map> listInfo = JSONObject.parseArray(object.get("rows").toString(),Map.class);
				
				
				if (listInfo != null && listInfo.size() > 0) {
					 for (Map m : listInfo) {
				          map = new HashMap();
				          map.put("houseCode", (String)m.get("HOUSE_CODE"));
				          String houseAddr = (String)m.get("DETAIL_HOUSE_FULL_ADDR");
				          String houseShortAdd = (String)m.get("HOUSE_ADDR");
				          String realAddr = (String)m.get("realAddr");
				          

				          if ((houseAddr != null) && (houseAddr.contains("广东省"))) {
				            houseAddr = houseAddr.substring(3);
				            map.put("houseAddr", houseAddr);
				            map.put("houseShortAdd", houseShortAdd);
				            map.put("realAddr", realAddr);
				          } else {
				            map.put("houseAddr", houseAddr);
				            map.put("houseShortAdd", houseShortAdd);
				            map.put("realAddr", realAddr);
				          }
				          list.add(map);
				        }
				}*/
				
				JSONArray array = JSONObject.parseArray(retStr);
				if (array != null && array.size() != 0) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject jsonObject = array.getJSONObject(i);
						Object obj = jsonObject.get("rows");
						// obj为空，则跳出本次循环
						if (obj == null) {
							continue;
						}
						String jsonStr = JSONObject.toJSONString(obj);
						JSONArray parseArray = JSONObject.parseArray(jsonStr);
						for (int j = 0; j < parseArray.size(); j++) {
							Map map = new HashMap();
							map.put("rownum", String.valueOf(i + 1));
							map.put("houseId", parseArray.getJSONObject(j).get("HOUSE_CODE"));
							map.put("houseAdd", parseArray.getJSONObject(j).get("DETAIL_HOUSE_FULL_ADDR"));
							map.put("houseShortAdd", parseArray.getJSONObject(j).get("HOUSE_ADDR"));
							//map.put("realAddr", parseArray.getJSONObject(j).get("REAL_ADDR"));
							list.add(map);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return list;
	}
	
	
	public static void main(String[] args) {
		String str = "4403040040100600004000215";//4403060010050400021000077   4403040080070300003000003
		System.out.println(str.length());
	}
	
		
}
		

