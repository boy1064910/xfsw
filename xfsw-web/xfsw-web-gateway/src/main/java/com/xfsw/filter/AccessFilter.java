/**
 * 
 */
package com.xfsw.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class AccessFilter extends ZuulFilter {

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		ctx.addZuulRequestHeader("COMPLETE-DOMAIN", request.getServerName());//设置整理域名

		// 刷新cookie过期时间
		String domain = request.getServerName();
		String[] domainArray = domain.split("\\.");
		String primaryDomain = "";
		if (domainArray.length > 1) {
			int length = domainArray.length;
			primaryDomain = domainArray[length - 2] + "." + domainArray[length - 1];
		} else {
			primaryDomain = domain;
		}
		ctx.addZuulRequestHeader("MAIN-DOMAIN", primaryDomain);//设置主域名
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
