package com.ptmind.datadeck.entity.datasource.config.request;

/**
 * 请求参数
 * @author xiaopeng.liu
 * @version
 */
public class RequestParam {

	String key;//请求参数名称
	String value;//请求参数数据
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
