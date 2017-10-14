package com.xfsw.root.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.entity.LinkAuthority;
import com.xfsw.account.service.AuthorityCacheService;
import com.xfsw.account.service.CategoryAuthorityService;
import com.xfsw.account.service.LinkAuthorityService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.common.util.DJBHashUtil;
import com.xfsw.session.service.UserSessionService;

/**
 * 管理后台-菜单权限管理功能服务接口
 * @author 刘希帆
 */
@Controller
@RequestMapping("/root/category/authority")
public class RootCategoryAuthorityController {

	@Resource(name = "categoryAuthorityService")
	CategoryAuthorityService categoryAuthorityService;

	@Resource(name = "linkAuthorityService")
	LinkAuthorityService linkAuthorityService;

	@Resource(name = "userSessionService")
	UserSessionService userSessionService;

	@Resource(name = "authorityCacheService")
	AuthorityCacheService authorityCacheService;

	@RequestMapping(value = "/index")
	public void index() {

	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public ResponseModel list(Integer tenantId) {
		return new ResponseModel(categoryAuthorityService.selectListByTenantId(tenantId));
	}

	@RequestMapping(value = "/insertCategoryAuthority", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel insertCategoryAuthority(CategoryAuthority categoryAuthority) {
		if (categoryAuthority.getPid() == null)
			categoryAuthority.setPid(0);
		if (!StringUtils.isEmpty(categoryAuthority.getUrl())) {
			categoryAuthority.setHashId(DJBHashUtil.DJBHashId(categoryAuthority.getUrl()));
		}
		categoryAuthority.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
		categoryAuthority.setLastUpdateTime(new Date());
		categoryAuthorityService.insertCategoryAuthority(categoryAuthority);
		return new ResponseModel();
	}

	@RequestMapping(value = "/initEditCategoryAuthority")
	@ResponseBody
	public ResponseModel initEditCategoryAuthority(Integer id) {
		return new ResponseModel(categoryAuthorityService.getById(id));
	}
	
	@RequestMapping(value="/updateCategoryAuthority", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel updateCategoryAuthority(CategoryAuthority categoryAuthority) {
		if (!StringUtils.isEmpty(categoryAuthority.getUrl())) {
			categoryAuthority.setHashId(DJBHashUtil.DJBHashId(categoryAuthority.getUrl()));
		}
		categoryAuthority.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
		categoryAuthorityService.updateCategoryAuthority(categoryAuthority);
		return new ResponseModel(categoryAuthority);
	}

	@RequestMapping(value = "/initConfigLinkAuthority")
	public void initConfigLinkAuthority(Integer id, Model model) {
		CategoryAuthority categoryAuthority = categoryAuthorityService.getById(id);
		model.addAttribute("id", id);
		model.addAttribute("categoryAuthority", categoryAuthority);
	}

	@RequestMapping(value = "/linkAuthoritylist")
	@ResponseBody
	public ResponseModel linkAuthoritylist(Integer categoryAuthorityId) {
		return new ResponseModel(linkAuthorityService.selectListByCategoryAuthorityId(categoryAuthorityId));
	}

	@RequestMapping(value = "/initEditLinkAuthority")
	@ResponseBody
	public ResponseModel initEditLinkAuthority(Integer id) {
		return new ResponseModel(linkAuthorityService.getById(id));
	}

	@RequestMapping(value = "/updateLinkAuthority", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel updateLinkAuthority(LinkAuthority linkAuthority) {
		linkAuthority.setLastUpdater(ThreadUserInfoManager.getAccount());
		linkAuthority.setOldId(linkAuthority.getId());
		linkAuthority.setId(DJBHashUtil.DJBHashId(linkAuthority.getUrl()));
		linkAuthorityService.updateLinkAuthority(linkAuthority);
		return new ResponseModel(linkAuthority);
	}

	@RequestMapping(value = "/insertLinkAuthority", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel insertLinkAuthority(LinkAuthority linkAuthority) {
		CategoryAuthority categoryAuthority = categoryAuthorityService.getById(linkAuthority.getCategoryAuthorityId());
		linkAuthority.setId(DJBHashUtil.DJBHashId(linkAuthority.getUrl()));
		linkAuthority.setLastUpdater(ThreadUserInfoManager.getAccount());
		linkAuthority.setTenantId(categoryAuthority.getTenantId());
		linkAuthorityService.insertLinkAuthority(linkAuthority);
		return new ResponseModel();
	}

	// @RequestMapping(value="/deleteAuthority")
	// @ResponseBody
	// public ResponseModel deleteAuthority(Integer id){
	// //删除菜单权限数据和角色权限配置信息
	// categoryAuthorityService.deleteAuthority(id,
	// ThreadUserInfoManager.getUserInfo().getAccount());
	// //刷新系统总权限缓存
	// authorityCacheService.refresh();
	// //刷新所有用户的角色权限缓存信息
	// userSessionService.refreshUserSessionAuthorityInfo();
	// return new ResponseModel();
	// }
	//
	//
	// @RequestMapping("/updateCategoryAuthority")
	// @ResponseBody
	// public ResponseModel updateCategoryAuthority(CategoryAuthority
	// authority){
	// if(!StringUtil.isEmpty(authority.getUrl())){
	// authority.setHashId(DJBHashUtil.DJBHashId(authority.getUrl()));
	// }
	// authority.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
	// categoryAuthorityService.updateCategoryAuthority(authority);
	// //刷新总权限缓存暂时用处不大，由于目前权限缓存仅仅用于菜单权限
	// authorityCacheService.refresh();//刷新系统总权限缓存
	// userSessionService.refreshUserSessionAuthorityInfo();//刷新后台用户登录信息缓存（此处包括用户信息、用户的角色对应的所有权限、用户的角色对应的所有菜单权限），主要让用户的公共权限访问授权通过
	// return new ResponseModel(authority);
	// }
	//
	// @RequestMapping(value="/refreshAuthorityCache")
	// @ResponseBody
	// public ResponseModel refreshAuthorityCache(){
	// authorityCacheService.refresh();
	// return new ResponseModel();
	// }

}
