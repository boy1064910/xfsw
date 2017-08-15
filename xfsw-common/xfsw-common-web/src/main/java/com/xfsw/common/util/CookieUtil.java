package com.xfsw.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie操作工具类 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class CookieUtil {

	/**
	 * 设置cookie内容
	 * @param response	@See javax.servlet.http.HttpServletResponse
	 * @param cookieName	cookie名称
	 * @param cookieValue	cookie值
	 * @param cookieMaxAge	cookie存活时间
	 * @param cookieDomain	cookie domain
	 * @param cookiePath	cookie path
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge, String cookieDomain, String cookiePath) {
		Cookie cookie = new Cookie(cookieName, String.valueOf(cookieValue));
		cookie.setMaxAge(Integer.valueOf(cookieMaxAge));// cookie时间
		cookie.setDomain(cookieDomain);
		cookie.setPath(cookiePath);
		response.addCookie(cookie);
	}

	/**
	 * 读取cookie内容	
	 * @param cookieName	cookieName
	 * @param request	@See javax.servlet.http.HttpServletRequest
	 * @return	cookie值
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public static String getCookie(String cookieName, HttpServletRequest request) {
		String cookieValue = null;
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					cookieValue = cookie.getValue();
					break;
				}
			}
		}
		return cookieValue;
	}

	/**
	 * 刷新cookie时间
	 * @param request	@See javax.servlet.http.HttpServletRequest
	 * @param response	@See javax.servlet.http.HttpServletResponse
	 * @param cookieName	cookie name
	 * @param cookieMaxAge	cookie存活时间
	 * @param cookieDomain	cookie domain
	 * @param cookiePath	cookie path
	 * @version 0.0.1
	 */
	public static void refreshCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, int cookieMaxAge, String cookieDomain, String cookiePath) {
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					cookie.setMaxAge(Integer.valueOf(cookieMaxAge));// cookie时间
					cookie.setDomain(cookieDomain);
					cookie.setPath(cookiePath);
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	public static void clearCookie(HttpServletRequest request, HttpServletResponse response, int cookieMaxAge, String cookieDomain, String cookiePath) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = new Cookie(cookies[i].getName(), null);
			cookie.setMaxAge(0);
			cookie.setDomain(cookieDomain);
			cookie.setPath(cookiePath);// 根据你创建cookie的路径进行填写
			response.addCookie(cookie);
		}
	}

	public static void delCookie(String cookieName, HttpServletRequest request, HttpServletResponse response, String cookieDomain, String cookiePath) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)) {
					Cookie cookie = new Cookie(cookies[i].getName(), null);
					cookie.setMaxAge(0);
					cookie.setDomain(cookieDomain);
					cookie.setPath(cookiePath);// 根据你创建cookie的路径进行填写
					response.addCookie(cookie);
					break;
				}
			}
		}
	}
}
