package com.xfsw.account;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xfsw.account.service.CategoryAuthorityService;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.TimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-service-tester.xml", "classpath*:spring/spring-service-*.xml" })
@Rollback(true)
public class CategoryAuthorityServiceTester {

	@Resource(name = "categoryAuthorityService")
	private CategoryAuthorityService categoryAuthorityService;

	@Test
	public void selectListByIds() {
		long startTime = System.currentTimeMillis();
		Integer[] ids = new Integer[] { 1, 6, 9, 11, 2, 3, 4, 5, 7, 8, 10, 13, 14, 12, 16, 15, 17 };
		System.out.println(JsonUtil.entity2Json(categoryAuthorityService.selectListByIds(ids)));
		TimeUtil.loggerLostTime(startTime);
	}

}
