package com.ptmind.datadeck.entity.datasource.auth.request;

/**
 * 授权相关请求参数结构体
 * @author xiaopeng.liu
 * @version
 */
public class AuthRequestParam {

	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
