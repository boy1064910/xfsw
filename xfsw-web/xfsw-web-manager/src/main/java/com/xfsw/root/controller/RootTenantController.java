package com.xfsw.root.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.entity.DefaultAuthority;
import com.xfsw.account.entity.DefaultLinkAuthority;
import com.xfsw.account.entity.LinkAuthority;
import com.xfsw.account.entity.Tenant;
import com.xfsw.account.service.CategoryAuthorityService;
import com.xfsw.account.service.DefaultAuthorityService;
import com.xfsw.account.service.DefaultLinkAuthorityService;
import com.xfsw.account.service.TenantService;
import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Controller
@RequestMapping("/root/tenant")
public class RootTenantController {

	@Resource(name="tenantService")
	TenantService tenantService;
	
	@Resource(name="defaultAuthorityService")
	DefaultAuthorityService defaultAuthorityService;
	
	@Resource(name="defaultLinkAuthorityService")
	DefaultLinkAuthorityService defaultLinkAuthorityService;
	
	@Resource(name="categoryAuthorityService")
	CategoryAuthorityService categoryAuthorityService;
	
	@RequestMapping(value="/index")
	public void index(){
		
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ResponseModel list(){
		return new ResponseModel(tenantService.selectAll());
	}
	
	@RequestMapping(value="/pageInfo")
	@ResponseBody
	public DataTableResponseModel pageInfo(DataTablePageInfo pageInfo){
		return tenantService.selectPageInfo(pageInfo);
	}
	
	@RequestMapping(value = "/insertTenant", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel insertTenant(Tenant tenant) {
		tenant.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
		tenant.setLastUpdateTime(new Date());
		tenantService.insertTenant(tenant);
		return new ResponseModel();
	}
	
	@RequestMapping(value = "/initEditTenant")
	@ResponseBody
	public ResponseModel initEditTenant(Integer id) {
		return new ResponseModel(tenantService.getById(id));
	}
	
	@RequestMapping(value = "/updateTenant", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel updateTenant(Tenant tenant) {
		tenant.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
		tenant.setLastUpdateTime(new Date());
		tenantService.updateTenant(tenant);
		return new ResponseModel(tenant);
	}
	
	@RequestMapping(value = "/configDefaultAuthority", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel configDefaultAuthority(Integer tenantId) {
		Tenant tenant = tenantService.getById(tenantId);
		String operator = ThreadUserInfoManager.getAccount();
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
		categoryAuthorityService.initAuthority(parentCategoryAuthorityList, categoryAuthorityList, linkAuthorityList);
		return new ResponseModel();
	}
	
}
