package com.xfsw.account;

import java.util.List;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xfsw.account.entity.RoleAuthoritySql;
import com.xfsw.account.service.RoleAuthoritySqlService;
import com.xfsw.common.util.JsonUtil;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-service-tester.xml","classpath*:spring/spring-service-*.xml"})
@Rollback(true)
public class RoleAuthoritySqlServiceTester {

	@Resource
	RoleAuthoritySqlService roleAuthoritySqlService;
	
	@BeforeClass
	public static void before(){
		System.out.println("test start");
	}
	
	@Test
	public void selectList() {
		try {
			RoleAuthoritySql roleAuthoritySql = new RoleAuthoritySql();
			roleAuthoritySql.setCountSql(" '' or 1=1 ");
			List<RoleAuthoritySql> sqlList = roleAuthoritySqlService.selectList(roleAuthoritySql);
			System.out.println(JsonUtil.entity2Json(sqlList));
			System.out.println("over");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
