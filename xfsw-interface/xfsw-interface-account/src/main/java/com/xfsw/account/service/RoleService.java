package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.Role;

/**
 * 空间角色服务
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface RoleService {
	
	/**
	 * 查询空间下角色信息
	 * @param tenantId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<Role> selectListByTenantId(Integer tenantId);
	
	/**
	 * 保存角色信息
	 * @param role
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void insertRole(Role role);
	
//	Role get(Integer id);
//	
//	void updateRole(Role role,Integer[] ids,Integer[] types);
//	
//	void addRole(Role role,Integer[] ids,Integer[] types);
//	
//	void deleteRole(Integer roleId,String operator);
//	
//	List<Role> selectAll();
}
