package com.ptmind.datadeck.model;

public class ResponseModel {

	String code;
	Object data;
	
	public ResponseModel(Object data) {
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
