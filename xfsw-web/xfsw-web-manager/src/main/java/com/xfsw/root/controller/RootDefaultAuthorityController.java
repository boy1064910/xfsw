package com.xfsw.root.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.account.service.DefaultAuthorityService;
import com.xfsw.common.classes.ResponseModel;

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
}
