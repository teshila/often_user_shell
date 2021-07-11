package com.ly.vuecontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ly.dao.impl.SpecialSettingDao;
import com.ly.pojo.Special_Setting;

@RestController
@RequestMapping("pro")
public class SpecialSettingController {

	@Autowired
	private SpecialSettingDao settingDao;

	@RequestMapping("getSpBuySettingList")
	public Map getSetting() {
		Map ret = new HashMap();
		List<Special_Setting> list = settingDao.find("from Special_Setting t where t.type=1");
		ret.put("list", list);
		return ret;
	}

	@RequestMapping("getSpSellSettingList")
	public Map getSellSetting() {
		Map ret = new HashMap();
		List<Special_Setting> list = settingDao.find("from Special_Setting t where t.type=2");
		ret.put("list", list);
		return ret;
	}

	@RequestMapping("updateSpSetting")
	public Map updateSellSetting(String percent) {
		Map ret = new HashMap();
		if(!StringUtils.isEmpty(percent)){
			try {
				settingDao.clearDefault();
				settingDao.updateBuyDefault(percent);
				ret.put("msg", "保存成功");
			} catch (Exception e) {
				ret.put("msg", "保存失败");
				e.printStackTrace();
			}
		}else{
			ret.put("msg", "买入比例不能为空");
		}
		
		return ret;
	}
}
