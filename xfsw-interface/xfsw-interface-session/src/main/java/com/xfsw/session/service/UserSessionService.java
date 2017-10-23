/**
 * 
 */
package com.xfsw.session.service;

import java.util.List;

import com.xfsw.account.model.UserAuthorityIdsModel;
import com.xfsw.common.classes.BusinessException;
import com.xfsw.session.model.UserSessionModel;

/**
 * 平台用户Session-SOA服务
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface UserSessionService {

	/**
	 * 添加平台用户登录session信息
	 * @param sessionId
	 * @param user
	 * @author liuxifan
	 */
	void addUserSession(String sessionIdValue,UserSessionModel userSessionModel);
	
	/**
	 * 判断用户是否登录，返回用户登录信息同时刷新session过期时间
	 * 1) sessionId存在，刷新session过期时间
	 * 2) sessionId不存在，返回登录超时异常
	 * @param sessionId
	 * @return
	 * @author liuxifan
	 */
	UserSessionModel getUserSession(String sessionIdValue) throws BusinessException;
	
	/**
	 * 清除用户登录信息
	 * @param sessionId
	 */
	void deleteUserSession(String sessionIdValue);
	
	/**
	 * 刷新角色下的用户权限信息
	 * @param roleId
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void refreshUserSessionAuthorityInfo(List<UserAuthorityIdsModel> userAuthorityIdsModelList);
//	
//	void refreshSystemUserSessionAuthorityInfoByRoleId(Integer roleId);
	
}
