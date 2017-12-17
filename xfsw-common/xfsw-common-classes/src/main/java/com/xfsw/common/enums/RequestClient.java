/**
 * 
 */
package com.xfsw.common.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public enum RequestClient {

	/**默认，浏览器*/
	Default,
	/**微信小程序*/
	WxMiniProgram;
	
	/**
	 * 获取请求客户端类型
	 * @param arg
	 * @return
	 * @author xiaopeng.liu
	 * @version
	 */
	public static RequestClient valuesOf(String arg) {
		RequestClient requestClient = null;
		if(StringUtils.isEmpty(arg)){
			return RequestClient.Default;
		}
		requestClient = RequestClient.valueOf(arg);
		if(requestClient==null) {
			return RequestClient.Default;
		}
		return requestClient;
	}
}
