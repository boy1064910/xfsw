/**
 * 
 */
package com.xfsw.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xfsw.account.entity.User;
import com.xfsw.account.model.UserAuthorityIdsModel;
import com.xfsw.account.service.RoleAuthorityService;
import com.xfsw.account.service.UserService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.util.CookieUtil;
import com.xfsw.common.util.HttpServletRequestUtil;
import com.xfsw.common.util.RandomUtil;
import com.xfsw.common.util.StringUtil;
import com.xfsw.session.consts.SessionConstant;
import com.xfsw.session.model.UserSessionModel;
import com.xfsw.session.service.UserSessionService;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@RestController
@RequestMapping("/manager/entry")
public class ManagerEntryController {

	@Resource(name = "userService")
	UserService userService;

	@Resource(name = "userSessionService")
	UserSessionService userSessionService;

	@Resource(name = "roleAuthorityService")
	RoleAuthorityService roleAuthorityService;
	
	@RequestMapping("/login")
	@ResponseBody
	public ResponseModel login(String account, String pwd, HttpServletRequest request, HttpServletResponse response) {
		UserSessionModel userSessionModel = null;
		String sessionidValue = CookieUtil.getCookie(SessionConstant.XFSW_SESSION_ID, request);
		if (sessionidValue != null) {// 如果cookie-dpsessionid不为空，并且cookie-dpsessionid值存在redis中
			userSessionModel = userSessionService.getUserSession(sessionidValue);
			if(userSessionModel!=null){ 
				//刷新cookie过期时间
				CookieUtil.refreshCookie(request, response, SessionConstant.XFSW_SESSION_ID, SessionConstant.XFSW_SESSION_EXPIRE, HttpServletRequestUtil.getDomain(request), "/");
				new ResponseModel(userSessionModel);
			}
		}
		// session 不存在，验证用户名密码
		String ip = HttpServletRequestUtil.getIpAddr(request);
		User user = userService.login(account, pwd, ip);

		if (user.getRoleId() != null) {
			// 获取用户权限数据列表
			UserAuthorityIdsModel userAuthorityIdsModel = roleAuthorityService.selectAllAuthorityHashIdsByRoleId(user.getRoleId());
			user.setAuthorityIds(userAuthorityIdsModel.getAuthorityIds());
			user.setCategoryAuthorityIds(userAuthorityIdsModel.getCategoryAuthorityIds());
		}

		// 记录登录session信息
		String sessionIdValue = System.nanoTime() + RandomUtil.getCharAndNumr(8);
		userSessionModel = new UserSessionModel(user.getId(), user.getAccount(), user.getHead(), user.getNickName(), user.getEmail());
		userSessionService.addUserSession(sessionIdValue, userSessionModel);
		
		CookieUtil.setCookie(response, SessionConstant.XFSW_SESSION_ID, sessionIdValue, SessionConstant.XFSW_SESSION_EXPIRE, HttpServletRequestUtil.getDomain(request), "/");
		return new ResponseModel(userSessionModel);
	}

	@RequestMapping("/logout")
	@ResponseBody
	public ResponseModel logout(HttpServletRequest request,HttpServletResponse response,String path) {
		// 获取cookie-dpsessionid的值
		String sessionidValue = CookieUtil.getCookie(SessionConstant.XFSW_SESSION_ID, request);
		if (!StringUtil.isEmpty(sessionidValue)) {
			// 调用session-redis服务，删除用户的session信息
			userSessionService.deleteUserSession(sessionidValue);
		}
		// 清除cookie信息
		CookieUtil.delCookie(SessionConstant.XFSW_SESSION_ID, request, response, HttpServletRequestUtil.getDomain(request), path);
		return new ResponseModel();
	}
}
