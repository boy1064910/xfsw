package com.xfsw.root.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.account.service.LinkAuthorityService;
import com.xfsw.common.classes.ResponseModel;

/**
 * 
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Controller
@RequestMapping("/root/link/authority")
public class RootLinkAuthorityController {
	
	@Resource(name="linkAuthorityService")
	LinkAuthorityService linkAuthorityService;
	
	@RequestMapping(value="/index")
	public void index(){
		
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ResponseModel list(Integer categoryAuthorityId){
		return new ResponseModel(linkAuthorityService.selectListByCategoryAuthorityId(categoryAuthorityId));
	}
	
	
}
