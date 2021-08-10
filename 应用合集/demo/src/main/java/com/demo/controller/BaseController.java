package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

	private Map resultCode = new HashMap();
	
	public BaseController() {
		resultCode.put("001", "11");
		
	}

	public Map getResultCode() {
		return resultCode;
	}

	public void setResultCode(Map resultCode) {
		this.resultCode = resultCode;
	}
	
}
