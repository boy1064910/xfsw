/**
 * 
 */
package com.xfsw.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.entity.DefaultAuthority;
import com.xfsw.account.entity.DefaultLinkAuthority;
import com.xfsw.account.entity.LinkAuthority;
import com.xfsw.account.entity.Tenant;
import com.xfsw.account.service.CategoryAuthorityService;
import com.xfsw.account.service.DefaultAuthorityService;
import com.xfsw.account.service.DefaultLinkAuthorityService;
import com.xfsw.account.service.TenantService;
import com.xfsw.common.util.JsonUtil;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-service-tester.xml","classpath*:spring/spring-service-*.xml"})
@Rollback(true)
public class TenantServiceTester {

	@Resource
	TenantService tenantService;
	
	@Resource
	CategoryAuthorityService categoryAuthorityService;
	
	@Resource(name="defaultAuthorityService")
	DefaultAuthorityService defaultAuthorityService;
	
	@Resource(name="defaultLinkAuthorityService")
	DefaultLinkAuthorityService defaultLinkAuthorityService;
	
	@Test
	public void test(){
		Integer roleId = 2;
		Tenant tenant = tenantService.getById(3);
		String operator = "tester";
		Date currentTime = new Date();
		
		List<DefaultAuthority> defaultAuthorityList = defaultAuthorityService.selectAll();
		List<CategoryAuthority> parentCategoryAuthorityList = new ArrayList<CategoryAuthority>();
		List<CategoryAuthority> categoryAuthorityList = new ArrayList<CategoryAuthority>();
		for(DefaultAuthority defaultAuthority:defaultAuthorityList){
			CategoryAuthority categoryAuthority = new CategoryAuthority(defaultAuthority,tenant.getId(),tenant.getCode());
			categoryAuthority.setLastUpdater(operator);
			categoryAuthority.setLastUpdateTime(currentTime);
			if(defaultAuthority.getPid().intValue()==0){
				parentCategoryAuthorityList.add(categoryAuthority);
			}
			else{
				categoryAuthorityList.add(categoryAuthority);
			}
		}
		
		List<DefaultLinkAuthority> defaultLinkAuthorityList = defaultLinkAuthorityService.selectAll();
		List<LinkAuthority> linkAuthorityList = new ArrayList<LinkAuthority>();
		for(DefaultLinkAuthority defaultLinkAuthority:defaultLinkAuthorityList) {
			LinkAuthority linkAuthority = new LinkAuthority(defaultLinkAuthority,tenant.getId(),tenant.getCode());
			linkAuthority.setLastUpdater(operator);
			linkAuthority.setLastUpdateTime(currentTime);
			linkAuthorityList.add(linkAuthority);
		}
		System.out.println(JsonUtil.entity2Json(defaultAuthorityList));
		System.out.println(JsonUtil.entity2Json(defaultLinkAuthorityList));
		
		categoryAuthorityService.initAuthority(parentCategoryAuthorityList, categoryAuthorityList, linkAuthorityList,roleId,operator);
	}
}
