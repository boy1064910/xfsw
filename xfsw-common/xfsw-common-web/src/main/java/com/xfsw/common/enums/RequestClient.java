/**
 * 
 */
package com.xfsw.common.enums;

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
		RequestClient requestClient = RequestClient.valueOf(arg);
		if(requestClient==null) {
			requestClient = RequestClient.Default;
		}
		return requestClient;
	}
}
