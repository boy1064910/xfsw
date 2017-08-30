package com.ptmind.datadeck.entity.datasource.config.request;

/**
 * 连接步骤第三方api请求信息
 * @author xiaopeng.liu
 * @version 3.0.0
 */
public class RequestSetting {

	String url;
	String method;
	RequestParam[] params;
	Boolean needToken = false;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public RequestParam[] getParams() {
		return params;
	}
	public void setParams(RequestParam[] params) {
		this.params = params;
	}
	public Boolean getNeedToken() {
		return needToken;
	}
	public void setNeedToken(Boolean needToken) {
		this.needToken = needToken;
	}
}
