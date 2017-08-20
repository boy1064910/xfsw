package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.Role;

public interface RoleService {

	Role get(Integer id);
	
	void updateRole(Role role,Integer[] ids,Integer[] types);
	
	void addRole(Role role,Integer[] ids,Integer[] types);
	
	void deleteRole(Integer roleId,String operator);
	
	List<Role> selectAll();
}
