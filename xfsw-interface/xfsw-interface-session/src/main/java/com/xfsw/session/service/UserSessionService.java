/**
 * 
 */
package com.xfsw.session.service;

import com.xfsw.session.model.UserSessionModel;

/**
 * 平台用户Session-SOA服务
 * @author {xiaopeng.liu@decked.com.cn} 
 */
public interface UserSessionService {

	/**
	 * 判断用户是否登录，返回用户登录信息同时刷新session过期时间
	 * <pre>
	 * 1) sessionId存在，刷新session过期时间
	 * 2) sessionId不存在，返回登录超时异常
	 * </pre>
	 * @param sessionIdValue
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	UserSessionModel getUserSession(String sessionIdValue);
	
	/**
	 * 添加平台用户登录session信息
	 * @param sessionIdValue
	 * @param userSessionModel
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void addUserSession(String sessionIdValue,UserSessionModel userSessionModel);
	
	/**
	 * 清除用户登录信息
	 * @param sessionId
	 */
	void deleteUserSession(String sessionIdValue);
	
//	void refreshUserSessionAuthorityInfo();
//	
//	void refreshSystemUserSessionAuthorityInfoByRoleId(Integer roleId);
	
}
