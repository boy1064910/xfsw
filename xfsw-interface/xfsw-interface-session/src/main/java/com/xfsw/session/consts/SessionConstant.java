package com.xfsw.session.consts;

/**
 * 登录session相关常量信息 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class SessionConstant {

	/** 平台cookie中sessionid的key */
	public final static String XFSW_SESSION_ID = "xfsw-session";
	/** 平台cookie中sessionid的过期时间 */
	public final static Integer XFSW_SESSION_EXPIRE = 60 * 60 * 2 * 1000;
	/** 平台用户redis key前缀 */
	public final static String XFSW_SESSION_REDIS_PREFIX = "user-session.";
}
