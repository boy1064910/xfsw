/**
 * 
 */
package com.xfsw.bootstrap;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@Component
public class FrontEndFilter extends ZuulFilter {

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return null;
	}

}
