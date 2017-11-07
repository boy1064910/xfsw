package com.xfsw.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xfsw.account.model.AuthorityModel;
import com.xfsw.account.service.AuthorityCacheService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.consts.ErrorConstant;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.common.util.DJBHashUtil;
import com.xfsw.common.util.HttpServletRequestUtil;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.session.model.UserSessionModel;
import com.xfsw.session.service.UserSessionService;

/**
 * 公共权限服务拦截器
 * @author 刘希帆
 */
public class UniverseAuthInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(UniverseAuthInterceptor.class);
	
	UserSessionService userSessionService;
	
	AuthorityCacheService authorityCacheService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("权限拦截器，请求路径："+request.getRequestURL().toString());
		String uri = request.getRequestURI();
//		uri = uri.substring(uri.indexOf("/"));
		
		if(request.getParameter("aRandomCode")!=null){
			uri+="?aRandomCode="+request.getParameter("aRandomCode");
		}
		Integer hashId = DJBHashUtil.DJBHashId(uri);
		UserSessionModel userSessionModel = ThreadUserInfoManager.getUserInfo();
		if(!CollectionUtils.isEmpty(userSessionModel.getRoleIdList())&&!ArrayUtils.isEmpty(userSessionModel.getAuthorityIds())){
			logger.debug(JsonUtil.entity2Json(userSessionModel.getAuthorityIds()));
			int index = ArrayUtils.indexOf(userSessionModel.getAuthorityIds(), hashId);
			if(index==-1){
				logger.debug("no auth::"+uri);
				//无此访问权限，程序结束，跳出
				noAuthorityOut(request, response);
				return false;
			}
		}
		return true;
	}
	
	private void noAuthorityOut(HttpServletRequest request,HttpServletResponse response) throws Exception{
		if (HttpServletRequestUtil.isAjaxRequest(request)) {// 属于ajax请求
			ResponseModel resultModel = new ResponseModel();
			resultModel.setCode(ErrorConstant.ERROR_NO_AUTH);
			PrintWriter pw = response.getWriter();
			pw.print(JsonUtil.entity2Json(resultModel));// 输出json格式，登录超时
			pw.close();
		} else {// html请求
			String httpDomain = "http://" + request.getServerName() + request.getContextPath() ;
			response.sendRedirect(httpDomain+"/manager/noAuth.shtml");
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//当为html请求的时候，返回数据携带用户信息
		if (!HttpServletRequestUtil.isAjaxRequest(request)) {
			int hashId = DJBHashUtil.DJBHashId(request.getServletPath());
			AuthorityModel linkAuthorityModel = authorityCacheService.getAuthorityModelById(hashId);
			if(linkAuthorityModel!=null&&linkAuthorityModel.getType().intValue()==2){//屬於功能權限
				modelAndView.addObject("authorityName", linkAuthorityModel.getName());
			}
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

	public AuthorityCacheService getAuthorityCacheService() {
		return authorityCacheService;
	}

	public void setAuthorityCacheService(AuthorityCacheService authorityCacheService) {
		this.authorityCacheService = authorityCacheService;
	}

}