package com.xfsw.account;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xfsw.account.model.UserModel;
import com.xfsw.account.service.UserService;
import com.xfsw.common.util.JsonUtil;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-service-tester.xml","classpath*:spring/spring-service-*.xml"})
@Rollback(true)
public class UserServiceTester {

	@Resource
	UserService userService;
	
	@Test
	public void login() {
		try {
			UserModel userModel = userService.login("admin", "E10ADC3949BA59ABBE56E057F20F883E", "127.0.0.1");
			System.out.println(JsonUtil.entity2Json(userModel));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
