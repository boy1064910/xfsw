package com.xfsw.root.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.account.entity.DefaultAuthority;
import com.xfsw.account.entity.DefaultLinkAuthority;
import com.xfsw.account.service.DefaultAuthorityService;
import com.xfsw.account.service.DefaultLinkAuthorityService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.common.util.DJBHashUtil;

/**
 * 管理后台-菜单权限管理功能服务接口
 * @author 刘希帆
 */
@Controller
@RequestMapping("/root/default/authority")
public class RootDefaultAuthorityController {
	
	@Resource(name="defaultAuthorityService")
	DefaultAuthorityService defaultAuthorityService;
	
	@Resource(name="defaultLinkAuthorityService")
	DefaultLinkAuthorityService defaultLinkAuthorityService;
	
	@RequestMapping(value="/index")
	public void index(){
		
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ResponseModel list(Integer tenantId){
		return new ResponseModel(defaultAuthorityService.selectAll());
	}
	
	@RequestMapping(value = "/insertDefaultAuthority", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel insertDefaultAuthority(DefaultAuthority defaultAuthority) {
		if (defaultAuthority.getPid() == null)
			defaultAuthority.setPid(0);
		defaultAuthority.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
		defaultAuthority.setLastUpdateTime(new Date());
		defaultAuthorityService.insertDefaultAuthority(defaultAuthority);
		return new ResponseModel();
	}
	
	@RequestMapping(value="/initConfigDefaultLinkAuthority")
	public void initConfigDefaultLinkAuthority(Integer defaultAuthorityId,Model model){
		DefaultAuthority defaultAuthority = defaultAuthorityService.getById(defaultAuthorityId);
		model.addAttribute("id", defaultAuthorityId);
		model.addAttribute("defaultAuthority", defaultAuthority);
	}
	
	@RequestMapping(value="deleteDefaultAuthority",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel deleteDefaultAuthority(Integer defaultAuthorityId,Model model){
		defaultAuthorityService.deleteDefaultAuthority(defaultAuthorityId, ThreadUserInfoManager.getAccount());
		return new ResponseModel();
	}
	
	@RequestMapping(value = "/defaultLinkAuthoritylist")
	@ResponseBody
	public ResponseModel defaultLinkAuthoritylist(Integer defaultAuthorityId) {
		return new ResponseModel(defaultLinkAuthorityService.selectListByDefaultAuthorityId(defaultAuthorityId));
	}
	
	@RequestMapping(value = "/insertDefaultLinkAuthority", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel insertDefaultLinkAuthority(DefaultLinkAuthority defaultLinkAuthority) {
		defaultLinkAuthority.setId(DJBHashUtil.DJBHashId(defaultLinkAuthority.getUrl()));
		defaultLinkAuthority.setLastUpdater(ThreadUserInfoManager.getAccount());
		defaultLinkAuthorityService.insertDefaultLinkAuthority(defaultLinkAuthority);
		return new ResponseModel();
	}
	
	@RequestMapping(value = "/deleteDefaultLinkAuthority", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel deleteDefaultLinkAuthority(Integer id) {
		defaultLinkAuthorityService.deleteById(id, ThreadUserInfoManager.getAccount());
		return new ResponseModel();
	}
	
}
