package com.xfsw.account.service;

import java.util.List;

public interface RoleCategoryAuthorityService {

	void delete(Integer authorityId,String operator);
	
	List<Integer> selectAuthorityIdsByRoleId(Integer roleId);
	
	void insertRoleCategoryAuthorityList(Integer roleId,List<Integer> addAuthorityIds,String operator);
	
	void deleteAndBakByRoleIdAndAuthorityIds(Integer roleId,Integer[] delAuthorityIds);
	
	void deleteByRoleId(Integer roleId,String operator);
}
