package com.ptmind.datadeck.service;

import java.util.concurrent.TimeUnit;

public interface ConnectionTokenService {

	void setToken(String connectionId,String token,int timeout,TimeUnit timeUnit);
	
	String getTokenByConnectionId(String connectionId);
}
