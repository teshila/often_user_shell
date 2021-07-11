package com.bak;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;


//https://blog.csdn.net/yz2015/article/details/81747068
/**
 * @description: CKEditor编辑器上传图片返回格式
 * @author: yz
 * @create: 2018/8/16 18:31
 */
public class FileResponse extends HashMap<String, Object> {

	Map<String, Object> msgMap = new HashMap<>();

	public String error(int code, String msg) {
		FileResponse result = new FileResponse();
		msgMap.put("message", msg);
		result.put("uploaded", code);
		result.put("error", msgMap);
		return JSON.toJSONString(result);
	}

	public String success(int code, String fileName, String url, String msg) {
		FileResponse result = new FileResponse();
		if (!StringUtils.isEmpty(msg)) {
			msgMap.put("message", msg);
			result.put("error", msgMap);
		}
		result.put("uploaded", code);
		result.put("fileName", fileName);
		result.put("url", url);
		return JSON.toJSONString(result);
	}
}
