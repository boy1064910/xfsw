package com.xfsw.root.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.account.entity.DefaultAuthority;
import com.xfsw.account.service.DefaultAuthorityService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;

/**
 * 管理后台-菜单权限管理功能服务接口
 * @author 刘希帆
 */
@Controller
@RequestMapping("/root/default/authority")
public class RootDefaultAuthorityController {
	
	@Resource(name="defaultAuthorityService")
	DefaultAuthorityService defaultAuthorityService;
	
	@RequestMapping(value="/index")
	public void index(){
		
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ResponseModel list(Integer tenantId){
		return new ResponseModel(defaultAuthorityService.selectAll());
	}
	
	@RequestMapping(value = "/insertCategoryAuthority", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel insertCategoryAuthority(DefaultAuthority defaultAuthority) {
		if (defaultAuthority.getPid() == null)
			defaultAuthority.setPid(0);
		defaultAuthority.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
		defaultAuthority.setLastUpdateTime(new Date());
		defaultAuthorityService.insertDefaultAuthority(defaultAuthority);
		return new ResponseModel();
	}
}
