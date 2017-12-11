package com.xfsw.common.classes;

import java.io.Serializable;

/**
 * 通信数据包返回结构体最外层公共数据结构
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class ResponseModel implements Serializable {

	private static final long serialVersionUID = 535281142961018368L;
	private int code = 200;//返回代码
	private String msg;//说明或者错误信息描述
	private Object data;//数据
    
	public ResponseModel() {
		this.code = 200;
	}
	
	public ResponseModel(int code) {
		this.code = code;
	}
	
	public ResponseModel(Object data) {
		this.code = 200;
		this.data = data;
	}
	
	public ResponseModel(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public ResponseModel(int code,String msg,Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
