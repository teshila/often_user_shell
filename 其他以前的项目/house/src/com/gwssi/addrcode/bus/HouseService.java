package com.gwssi.addrcode.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwssi.addrcode.dao.GovermentDao;
import com.gwssi.addrcode.dao.IPDao;
import com.gwssi.addrcode.httpwrap.AddrInfoReqWrap;

@Service
public class HouseService {
	
	@Autowired
	private AddrInfoReqWrap addrInfoReqWrap;
	
	@Autowired
	private GovermentDao govDao;

	public List getAddrInfo(String name, String code) {
		List<Map> listInfo = addrInfoReqWrap.getAddrInfo(name, code);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		
		Map govMap = new HashMap();
		

		if (listInfo != null && listInfo.size() > 0) {
			for (Map m : listInfo) {
				map = new HashMap();
				map.put("houseCode", (String) m.get("houseId"));
				String houseAddr = (String) m.get("houseAdd");
				String houseShortAdd = (String) m.get("houseShortAdd");
				String realAddr = (String) m.get("realAddr");

				if ((houseAddr != null) && (houseAddr.contains("广东省"))) {
					houseAddr = houseAddr.substring(3);
					govMap.put("word", houseAddr);
					int i = govDao.getIsGover(govMap);
					if(i>0){
						map.put("houseAddr", "");
						map.put("houseShortAdd", "");
						map.put("realAddr", "");
					}else{
						map.put("houseAddr", houseAddr);
						map.put("houseShortAdd", houseShortAdd);
						map.put("realAddr", realAddr);
					}
					
				} else {
					int i = govDao.getIsGover(govMap);
					if(i>0){
						map.put("houseAddr", "");
						map.put("houseShortAdd", "");
						map.put("realAddr", "");
					}else{
						map.put("houseAddr", houseAddr);
						map.put("houseShortAdd", houseShortAdd);
						map.put("realAddr", realAddr);
					}
					
				}
				
				list.add(map);
			}
		}
		return list;
	}
}
