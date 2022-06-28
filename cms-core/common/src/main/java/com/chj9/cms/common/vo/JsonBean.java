package com.chj9.cms.common.vo;

import java.util.HashMap;

public class JsonBean extends HashMap<Object, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code = "0"; //0成功，1失败
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		put("code", code);
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		put("message", message);
		this.message = message;
	}

	public void put(String key, Object value) {
		super.put(key, value);
	}

	public JsonBean() {
		super();
		put("code", code);
	}
	
	
}
