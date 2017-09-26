package com.ptmind.datadeck.service;

import java.util.concurrent.TimeUnit;

/**
 * 提供关于token相关的方法（包括缓存过期重新获取token）
 * @author xiaopeng.liu
 * @version
 */
public interface ConnectionTokenService {

	void setToken(String connectionId,String token,int timeout,TimeUnit timeUnit);
	
	String getTokenByConnectionId(String connectionId);
}
