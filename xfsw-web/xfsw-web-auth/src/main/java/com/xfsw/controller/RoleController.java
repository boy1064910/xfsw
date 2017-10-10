package com.xfsw.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.account.service.RoleService;
import com.xfsw.common.classes.ResponseModel;

@Controller
@RequestMapping("/manager/role")
public class RoleController {

	@Resource(name="roleService")
	RoleService roleService;
	
	@RequestMapping(value = "/index")
	public void index() {

	}

	@RequestMapping(value="/list")
	@ResponseBody
	public ResponseModel list(Integer tenantId){
		return new ResponseModel(roleService.selectListByTenantId(tenantId));
	}
	
//	@RequestMapping(value = "/initAdd")
//	public void initAdd(Model model) {
//		model.addAttribute("firstAuthorityList", categoryAuthorityService.selectFirstAuthorityModelList());
//	}
//	
//	@RequestMapping(value = "addRole")
//	@ResponseBody
//	public HttpResultModel addRole(Role role,Integer[] ids,Integer[] types){
//		role.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
//		roleService.addRole(role, ids, types);
//		return new HttpResultModel();
//	}
//	
//	@RequestMapping(value = "/initEdit")
//	public void initEdit(Integer id,Model model) {
//		model.addAttribute("role", roleService.get(id));
//		model.addAttribute("firstAuthorityList", categoryAuthorityService.selectFirstAuthorityModelList());
//	}
//	
//	@RequestMapping(value = "editRole")
//	@ResponseBody
//	public HttpResultModel editRole(Role role,Integer[] ids,Integer[] types) {
//		role.setLastUpdater(ThreadUserInfoManager.getUserInfo().getAccount());
//		roleService.updateRole(role,ids,types);
//		// 更新该角色用户权限缓存信息
//		userSessionService.refreshSystemUserSessionAuthorityInfoByRoleId(role.getId());
//		// 更新该角色用户权限SQL配置缓存信息
//		roleAuthoritySqlCacheService.reloadByRoleId(role.getId());
//		return new HttpResultModel();
//	}
//	
//	@RequestMapping(value = "deleteRole")
//	@ResponseBody
//	public HttpResultModel deleteRole(Integer id) {
//		roleService.deleteRole(id, ThreadUserInfoManager.getUserInfo().getAccount());
//		//删除角色权限SQL缓存信息
//		roleAuthoritySqlCacheService.deleteByRoleId(id);
//		return new HttpResultModel();
//	}
}
