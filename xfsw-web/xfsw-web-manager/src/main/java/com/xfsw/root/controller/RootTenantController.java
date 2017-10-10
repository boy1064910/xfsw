package com.xfsw.root.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.account.entity.Tenant;
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
	
}
