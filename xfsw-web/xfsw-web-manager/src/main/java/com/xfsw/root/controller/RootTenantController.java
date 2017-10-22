package com.xfsw.root.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.entity.DefaultAuthority;
import com.xfsw.account.entity.DefaultLinkAuthority;
import com.xfsw.account.entity.LinkAuthority;
import com.xfsw.account.entity.Role;
import com.xfsw.account.entity.Tenant;
import com.xfsw.account.entity.User;
import com.xfsw.account.service.CategoryAuthorityService;
import com.xfsw.account.service.DefaultAuthorityService;
import com.xfsw.account.service.DefaultLinkAuthorityService;
import com.xfsw.account.service.RoleAuthorityService;
import com.xfsw.account.service.RoleService;
import com.xfsw.account.service.TenantService;
import com.xfsw.account.service.UserService;
import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.session.service.UserSessionService;

/**
 * 空间管理
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
	
	@Resource(name="roleService")
	RoleService roleService;
	
	@Resource(name="roleAuthorityService")
	RoleAuthorityService roleAuthorityService;
	
	@Resource(name="userService")
	UserService userService;
	
	@Resource(name="userSessionService")
	UserSessionService userSessionService;
	
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
	
	@RequestMapping(value="/initConfigRole")
	public void initConfigRole(Integer tenantId,Model model){
		Tenant tenant = tenantService.getById(tenantId);
		model.addAttribute("tenant", tenant);
	}
	
	@RequestMapping(value="/roleList")
	@ResponseBody
	public ResponseModel roleList(Integer tenantId){
		return new ResponseModel(roleService.selectListByTenantId(tenantId));
	}
	
	/**
	 * 初始化默认权限给选择的角色
	 * TODO 后期调整为界面可配置选择
	 * @param tenantId
	 * @param roleId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@RequestMapping(value = "/configDefaultAuthority", method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel configDefaultAuthority(Integer tenantId,Integer roleId) {
		Tenant tenant = tenantService.getById(tenantId);
		String operator = ThreadUserInfoManager.getAccount();
		Date currentTime = new Date();
		
		List<DefaultAuthority> defaultAuthorityList = defaultAuthorityService.selectAll();
		if(CollectionUtils.isEmpty(defaultAuthorityList)){
			return new ResponseModel();
		}
		
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
		categoryAuthorityService.initAuthority(parentCategoryAuthorityList, categoryAuthorityList, linkAuthorityList,roleId,operator);
		return new ResponseModel();
	}
	
	/**
	 * 给空间下的角色配置权限信息
	 * @param model
	 * @param tenantId
	 */
	@RequestMapping(value = "/initAddRole")
	public void initAddRole(Model model,Integer tenantId,Integer roleId) {
		model.addAttribute("firstAuthorityList", categoryAuthorityService.selectFirstAuthorityModelList(tenantId));
		model.addAttribute("tenantId",tenantId);
		if(roleId!=null){
			model.addAttribute("role",roleService.getById(roleId));
		}
	}
	
	/**
	 * 进入编辑角色页面
	 * @param model
	 * @param tenantId
	 * @param roleId
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@RequestMapping(value = "/initEditRole")
	public void initAddRole(Model model,Integer roleId) {
		Role role = roleService.getById(roleId);
		if(role!=null){
			model.addAttribute("tenantId",role.getTenantId());
			model.addAttribute("firstAuthorityList", categoryAuthorityService.selectFirstAuthorityModelList(role.getTenantId()));
			model.addAttribute("role",role);
		}
	}
	
	/**
	 * 查询角色下的权限集合ID
	 * @param roleId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@RequestMapping(value = "/selectAuthorityListByRoleId")
	@ResponseBody
	public ResponseModel selectAuthorityListByRoleId(Integer roleId) {
		return new ResponseModel(roleAuthorityService.selectUnionAuthorityIdListByRoleId(roleId));
	}
	
	/**
	 * 添加角色并配置权限
	 * @param role
	 * @param ids
	 * @param types
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@RequestMapping(value = "/addRole")
	@ResponseBody
	public ResponseModel addRole(Role role,Integer[] ids,Integer[] types){
		role.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
		roleService.addRole(role, ids, types);
		return new ResponseModel();
	}
	
	/**
	 * 更新角色信息
	 * @param role
	 * @param ids
	 * @param types
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@RequestMapping(value = "/updateRole")
	@ResponseBody
	public ResponseModel updateRole(Role role,Integer[] ids,Integer[] types){
		role.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
		roleService.updateRole(role, ids, types);
		//刷新登录session
		userSessionService.refreshUserSessionAuthorityInfo(role.getId());
		return new ResponseModel();
	}
	
	@RequestMapping(value="/initConfigUser")
	public void initConfigUser(Integer tenantId,Model model){
		Tenant tenant = tenantService.getById(tenantId);
		model.addAttribute("tenant", tenant);
	}
	
	@RequestMapping(value="/userList")
	@ResponseBody
	public ResponseModel userList(Integer tenantId){
		return new ResponseModel(userService.selectTenantUserList(tenantId));
	}
	
	@RequestMapping(value="/insertUser")
	@ResponseBody
	public ResponseModel insertUser(User user,Integer roleId,Integer tenantId){
		userService.insertTenantUser(user,roleId,tenantId,ThreadUserInfoManager.getAccount());
		return new ResponseModel();
	}
	
	
}
