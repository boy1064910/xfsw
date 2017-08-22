package com.xfsw.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.consts.ErrorConstant;
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

//	private static Logger logger = LoggerFactory.getLogger(UniverseLoginInterceptor.class);
	
	UserSessionService userSessionService;
	
	String urlPrefix;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String sessionId = "";
		if(HttpServletRequestUtil.isAjaxRequest(request)){//ajax请求
			sessionId = request.getHeader(SessionConstant.XFSW_SESSION_ID);
		}
		else{
			sessionId = CookieUtil.getCookie(SessionConstant.XFSW_SESSION_ID, request);
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
		if(!HttpServletRequestUtil.isAjaxRequest(request)){
			//刷新cookie过期时间
			CookieUtil.refreshCookie(request, response, SessionConstant.XFSW_SESSION_ID, SessionConstant.XFSW_SESSION_EXPIRE, HttpServletRequestUtil.getDomain(request), "/");
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
				returnUrl += request.getQueryString();
			response.sendRedirect(returnUrl);
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (!HttpServletRequestUtil.isAjaxRequest(request)) {
			UserSessionModel userSessionModel = ThreadUserInfoManager.getUserInfo();
			modelAndView.addObject("userNickName", userSessionModel.getNickName());
			modelAndView.addObject("userHead",userSessionModel.getHead());
			modelAndView.addObject("userId",userSessionModel.getId());
			modelAndView.addObject("userAccount",userSessionModel.getAccount());
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