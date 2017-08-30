package com.ptmind.datadeck.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("connectionTokenService")
public class ConnectionTokenServiceImpl implements ConnectionTokenService {

	@Resource(name="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	private final static String TOKEN_PREFIX = "datasource-token-";
	
	public void setToken(String connectionId,String token,int timeout,TimeUnit timeUnit) {
		redisTemplate.opsForValue().set(TOKEN_PREFIX + connectionId,token, timeout,TimeUnit.SECONDS);
	}
	
	public String getTokenByConnectionId(String connectionId) {
		return redisTemplate.opsForValue().get(TOKEN_PREFIX + connectionId);
	}
}
