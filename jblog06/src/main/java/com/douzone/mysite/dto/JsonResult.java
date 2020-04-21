package com.douzone.mysite.dto;

public class JsonResult {
	private String result;  // "success" or "fail"
	private Object data;    // if success, set
	private String message; // if fail, Error Message set
	
	// private로 생성자를 만들면 new가 안된다.
	private JsonResult(Object data) {
		result = "success";
		this.data = data;
	}
	private JsonResult(String message) {
		result = "fail";
		this.message = message;
	}

	public static JsonResult  success(Object data) {
		return new JsonResult(data);
	}
	
	public static JsonResult  fail(String message) {
		return new JsonResult(message);
	}
	public String getResult() {
		return result;
	}
	public Object getData() {
		return data;
	}
	public String getMessage() {
		return message;
	}
	
	
	
}
