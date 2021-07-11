package com.gwssi.addrcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwssi.addrcode.http.HttpRequest;

/**
 * 地址信息核查接口
 * 	  通过房屋编码来精确查找房屋地址
 * 
 */

@Service
public class AddrInfoService {
	private static Logger logger = Logger.getLogger(AddrInfoService.class);
	
	private String url = "http://61.144.226.124:7890/czwSearchAdmin/services/searchTeleWS?wsdl";
	
	@Autowired
	private HttpRequest httpRequest;
	
	
	public Map getAddrInfo(String houseCode) {
		List<Map> listInfo = new ArrayList();
		Map<String, String> map = null;
		Map<String, String> map2 = null;
		Map retMap = new HashMap();
		String retStr = null;
		if (StringUtils.isEmpty(houseCode)) {
		}
		
		String code = houseCode.trim();
		boolean flag = this.isGovmentBusinessPlace(code); //政府办公驻地为真
		if(!flag) {
			// 根据房屋编码精确获取房屋地址,接口2
			//List<Map> listInfo = httpRequest.getHouseByMoreAddress(url,code);
			try {
				//retStr = httpRequest.sendSoapPost(url,code);
				retStr = null;
				logger.info("向网格办发起请求==>" + houseCode);
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				
				if (listInfo != null && listInfo.size() > 0) {
					 for (Map m : listInfo) {
				          map = new HashMap();
				          map.put("houseCode", (String)m.get("houseId"));
				          String houseAddr = (String)m.get("houseAdd");
				          String houseShortAdd = (String)m.get("houseShortAdd");
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
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
		
		}
		return retMap;
		
		
		
	}
	
	
	/**
	 * 是政府办公驻地，则返回真
	 * @param code
	 * @return
	 */
	private boolean isGovmentBusinessPlace(String code) {
		/*boolean flag = false;
		String sql = "select * from DC_HOUSE_CODE_KEYWORDS t where  t.word like  ?  ";
		List params = new ArrayList();
		params.add("%"+code+"%");
		List<Map> queryForList;
		try {
			queryForList = dao.queryForList(sql, params);
			if(queryForList!=null&&queryForList.size()>0) {
				flag = true;
			}
		} catch (OptimusException e) {
			e.printStackTrace();
		}
		return flag;*/
		
		return false;
	}
	
	
	
	
	public static void main(String[] args) {
		String str = "4403040040100600004000215";//4403060010050400021000077   4403040080070300003000003
		System.out.println(str.length());
	}
	
		
}
		

