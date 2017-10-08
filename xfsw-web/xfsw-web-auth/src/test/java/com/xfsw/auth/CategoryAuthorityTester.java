package com.xfsw.auth;

import org.junit.Test;

import com.xfsw.common.util.DJBHashUtil;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class CategoryAuthorityTester {

	@Test
	public void generateHashId() {
		System.out.println(DJBHashUtil.DJBHashId("/xfsw-web-manager/root/category/authority/index.shtml"));
	}
}
