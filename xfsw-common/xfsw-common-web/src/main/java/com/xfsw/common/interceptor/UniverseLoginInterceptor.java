package com.xfsw.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.consts.ErrorConstant;
import com.xfsw.common.enums.RequestClient;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.common.util.CookieUtil;
import com.xfsw.common.util.HttpServletRequestUtil;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.StringUtil;
import com.xfsw.session.consts.SessionConstant;
import com.xfsw.session.model.UserSessionModel;
import com.xfsw.session.service.UserSessionService;

/**
 * 公共登录服务拦截器
 * @author 刘希帆
 */
public class UniverseLoginInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(UniverseLoginInterceptor.class);
	
	UserSessionService userSessionService;
	
	String urlPrefix;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("请求的url:"+request.getRequestURI());
		//获取请求来源客户端
		RequestClient requestClient = RequestClient.valuesOf(request.getHeader("X-REQUESTED-CLIENT"));
		//session id信息
		String sessionId = null;
		switch(requestClient) {
			//默认为浏览器，从cookie中获取sessionId相关信息
			case Default:{
				sessionId = CookieUtil.getCookie(SessionConstant.XFSW_SESSION_ID, request);
				break;
			}
			//微信小程序
			case WxMiniProgram:{
				sessionId = request.getHeader(SessionConstant.XFSW_SESSION_ID);
				break;
			}
		}
		
		if(StringUtil.isEmpty(sessionId)){
			loginTimeout(request,response,sessionId);
			return false;
		}
		//获取用户信息同时刷新session过期时间
		UserSessionModel userSessionModel = userSessionService.getUserSession(sessionId);
		if(userSessionModel==null){
			loginTimeout(request,response,sessionId);
			return false;
		}
		
		switch(requestClient) {
			case Default:{
				//刷新cookie过期时间
				CookieUtil.refreshCookie(request, response, SessionConstant.XFSW_SESSION_ID, SessionConstant.XFSW_SESSION_EXPIRE, HttpServletRequestUtil.getDomain(request), "/");
				break;
			}
			default:{
				break;
			}
		}
		//为当前线程保存用户信息
		ThreadUserInfoManager.setUserInfo(userSessionModel);
		return true;
	}
	
	/**
	 * 登录超时处理逻辑
	 * @author liuxiaopeng
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void loginTimeout(HttpServletRequest request,HttpServletResponse response,String sessionId) throws Exception{
		if(HttpServletRequestUtil.isAjaxRequest(request)){
			ResponseModel resultModel = new ResponseModel(ErrorConstant.ACCOUNT_SESSION_NOT_EXIST);
			PrintWriter pw = response.getWriter();
			pw.print(JsonUtil.entity2Json(resultModel));// 输出json格式，登录超时
			pw.close();
		}
		else{
			String url = request.getRequestURL().toString();
			int protocolIndex = url.indexOf(request.getHeader("Host"));
			String protocol = url.substring(0, protocolIndex);
			String loginUrl = "/login.html";
			String returnUrl = protocol + request.getHeader("Host") + loginUrl + "?returnUrl=" + protocol + request.getHeader("Host") + urlPrefix + request.getRequestURI();
			// 带上returnUrl，登录页登录成功之后重新跳转进入原来的请求页面
			if (!StringUtil.isEmpty(request.getQueryString()))// 携带参数的请求链接
				returnUrl += "?" + request.getQueryString();
			response.sendRedirect(returnUrl);
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (!HttpServletRequestUtil.isAjaxRequest(request)) {
			UserSessionModel userSessionModel = ThreadUserInfoManager.getUserInfo();
			modelAndView.addObject("userSessionModel",userSessionModel);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}

	public UserSessionService getUserSessionService() {
		return userSessionService;
	}

	public void setUserSessionService(UserSessionService userSessionService) {
		this.userSessionService = userSessionService;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

}