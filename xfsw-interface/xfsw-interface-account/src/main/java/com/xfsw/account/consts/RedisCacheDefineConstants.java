package com.xfsw.account.consts;

/**
 * redis缓存定义常量类
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class RedisCacheDefineConstants {

	/**所有菜单权限缓存前缀*/
	public final static String XFSW_ALL_CATEGORY_AUTHORITY = "authority_all_category";
	/**权限缓存过期时间*/
	public final static Integer XFSW_ALL_CATEGORY_AUTHORITY_CACHE_EXPIRED_TIME = 7*24*3600*1000;//一个星期
	
	/**所有权限（菜单不包括一级菜单、功能）缓存前缀*/
	public final static String XFSW_ALL_AUTHORITY = "authority_all";
	/**所有权限过期时间*/
	public final static Integer XFSW_ALL_AUTHORITY_CACHE_EXPIRED_TIME = 7*24*3600*1000;//一个星期
}
