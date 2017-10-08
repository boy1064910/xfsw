package com.xfsw.account;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xfsw.account.service.AuthorityCacheService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-service-tester.xml","classpath*:spring/spring-service-*.xml"})
@Rollback(true)
public class AuthorityCacheServiceTester {
	
	@Resource(name="authorityCacheService")
	private AuthorityCacheService authorityCacheService;
	
	@Test
	public void test() {
//		authorityCacheService.test();
	}
	
}
