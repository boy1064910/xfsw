/**
 * 
 */
package com.xfsw.common.consts;

/**
 * TODO 正在拆分,后续拆分结束该类删除
 * cookie常量类
 * @author liuxiaopeng
 */
public class CommonConstant {

	/**字典缓存前缀*/
	public final static String XFSW_ALL_DICTIONARY = "dictionary.";
	/**所有权限过期时间*/
	public final static Integer XFSW_ALL_DICTIONARY_CACHE_EXPIRED_TIME = 7*24*3600*1000;//一个星期
	
	/** 购物车id key前缀 */
	public final static String SHOPPING_CAR_SESSION_REDIS_PREFIX = "car_";
	/** 购物车session过期时间(毫秒) */
	public final static long SHOPPING_CAR_SESSION_EXPRIED_TIME = 7*24*3600*1000;
}
