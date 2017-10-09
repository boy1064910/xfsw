package com.xfsw.root.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.entity.LinkAuthority;
import com.xfsw.account.service.AuthorityCacheService;
import com.xfsw.account.service.CategoryAuthorityCacheService;
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
	
	@Resource(name="categoryAuthorityCacheService")
	CategoryAuthorityCacheService categoryAuthorityCacheService;
	
	@Resource(name="linkAuthorityService")
	LinkAuthorityService linkAuthorityService;
	
	@Resource(name="userSessionService")
	UserSessionService userSessionService;
	
	@Resource(name="authorityCacheService")
	AuthorityCacheService authorityCacheService;
	
	@RequestMapping(value="/index")
	public void index(){
		
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ResponseModel list(Integer tenantId){
		return new ResponseModel(categoryAuthorityCacheService.selectListByTenantId(tenantId));
	}
	
	@RequestMapping(value="/initConfigLinkAuthority")
	public void initConfigLinkAuthority(Integer id,Model model){
		CategoryAuthority categoryAuthority = categoryAuthorityCacheService.getById(id);
		model.addAttribute("id", id);
		model.addAttribute("categoryAuthority",categoryAuthority);
	}
	
	@RequestMapping(value="/linkAuthoritylist")
	@ResponseBody
	public ResponseModel linkAuthoritylist(Integer categoryAuthorityId){
		return new ResponseModel(linkAuthorityService.selectListByCategoryAuthorityId(categoryAuthorityId));
	}
	
	@RequestMapping(value="/initEditLinkAuthority")
	@ResponseBody
	public ResponseModel initEditLinkAuthority(Integer id){
		return new ResponseModel(linkAuthorityService.getById(id));
	}
	
	@RequestMapping(value="/updateLinkAuthority")
	@ResponseBody
	public ResponseModel updateLinkAuthority(LinkAuthority linkAuthority){
		linkAuthority.setLastUpdater(ThreadUserInfoManager.getAccount());
		linkAuthority.setOldId(linkAuthority.getId());
		linkAuthority.setId(DJBHashUtil.DJBHashId(linkAuthority.getUrl()));
		linkAuthorityService.updateLinkAuthority(linkAuthority);
		
		//刷新权限缓存信息
		authorityCacheService.reload();
		//TODO 刷新用户session中权限缓存信息
		
		return new ResponseModel(linkAuthority);
	}
	
	@RequestMapping(value="/insertLinkAuthority")
	@ResponseBody
	public ResponseModel insertLinkAuthority(LinkAuthority linkAuthority){
		
		CategoryAuthority categoryAuthority = categoryAuthorityCacheService.getById(linkAuthority.getCategoryAuthorityId());
		
		linkAuthority.setId(DJBHashUtil.DJBHashId(linkAuthority.getUrl()));
		linkAuthority.setLastUpdater(ThreadUserInfoManager.getAccount());
		linkAuthority.setTenantId(categoryAuthority.getTenantId());
		linkAuthorityService.insertLinkAuthority(linkAuthority);
		//刷新权限缓存信息
		authorityCacheService.reload();
		return new ResponseModel();
	}
	
//	@RequestMapping("/insertAuthority")
//	@ResponseBody
//	public ResponseModel insertAuthority(CategoryAuthority authority){
//		if(authority.getPid()==null)
//			authority.setPid(0);
//		if(!StringUtil.isEmpty(authority.getUrl())){
//			authority.setHashId(DJBHashUtil.DJBHashId(authority.getUrl()));
//		}
//		authority.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
//		categoryAuthorityService.insertAuthority(authority);
//		
//		// 刷新总权限缓存暂时用处不大，由于目前权限缓存仅仅用于菜单权限
//		authorityCacheService.refresh();
//		userSessionService.refreshUserSessionAuthorityInfo();//刷新用户登录信息（用户信息、对应角色的权限）
//		return new ResponseModel();
//	}
//	
//	@RequestMapping(value="/deleteAuthority")
//	@ResponseBody
//	public ResponseModel deleteAuthority(Integer id){
//		//删除菜单权限数据和角色权限配置信息
//		categoryAuthorityService.deleteAuthority(id, ThreadUserInfoManager.getUserInfo().getAccount());
//		//刷新系统总权限缓存
//		authorityCacheService.refresh();
//		//刷新所有用户的角色权限缓存信息
//		userSessionService.refreshUserSessionAuthorityInfo();
//		return new ResponseModel();
//	}
//	
//	@RequestMapping(value="/initEditCategoryAuthority")
//	@ResponseBody
//	public ResponseModel initEditCategoryAuthority(Integer id){
//		return new ResponseModel(categoryAuthorityService.get(id));
//	}
//	
//	@RequestMapping("/updateCategoryAuthority")
//	@ResponseBody
//	public ResponseModel updateCategoryAuthority(CategoryAuthority authority){
//		if(!StringUtil.isEmpty(authority.getUrl())){
//			authority.setHashId(DJBHashUtil.DJBHashId(authority.getUrl()));
//		}
//		authority.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
//		categoryAuthorityService.updateCategoryAuthority(authority);
//		//刷新总权限缓存暂时用处不大，由于目前权限缓存仅仅用于菜单权限
//		authorityCacheService.refresh();//刷新系统总权限缓存
//		userSessionService.refreshUserSessionAuthorityInfo();//刷新后台用户登录信息缓存（此处包括用户信息、用户的角色对应的所有权限、用户的角色对应的所有菜单权限），主要让用户的公共权限访问授权通过
//		return new ResponseModel(authority);
//	}
//	
//	@RequestMapping(value="/refreshAuthorityCache")
//	@ResponseBody
//	public ResponseModel refreshAuthorityCache(){
//		authorityCacheService.refresh();
//		return new ResponseModel();
//	}
	
	
}
