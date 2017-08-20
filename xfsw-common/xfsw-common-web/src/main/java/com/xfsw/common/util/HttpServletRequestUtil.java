/**
 * 
 */
package com.xfsw.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class HttpServletRequestUtil {

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("http_client_ip");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1)
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		return ip;
	}

	public static int getIp(HttpServletRequest request) {
		String ip = getIpAddr(request);
		if (ip.split(".").length == 3)
			ip = ip + ".0";
		String[] strs = ip.split("\\.");
		return (Integer.parseUnsignedInt(strs[0]) << 24) + (Integer.parseUnsignedInt(strs[1]) << 16) + (Integer.parseUnsignedInt(strs[2]) << 8) + (Integer.parseUnsignedInt(strs[3]));
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With") == null ? request.getHeader("X-REQUESTED-WITH") : request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
	
	public static String getDomain(HttpServletRequest request){
		//刷新cookie过期时间
		String domain = request.getServerName();
		String[] domainArray = domain.split("\\.");
		String primaryDomain = "";
		if(domainArray.length>1){
			int length = domainArray.length;
			primaryDomain = domainArray[length-2]+"."+domainArray[length-1];
		}
		else{
			primaryDomain = domain;
		}
		return primaryDomain;
	}
}
