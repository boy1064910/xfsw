package com.xfsw.account.service;

import java.util.List;

public interface RoleLinkAuthorityService {

	void deleteRoleLinkAuthority(Integer authorityId,String operator);
	
	List<Integer> selectAuthorityIdsByRoleId(Integer roleId);
	
	void insertRoleLinkAuthorityList(Integer roleId,List<Integer> addAuthorityIds,String operator);
	
	void deleteAndBakByRoleIdAndAuthorityIds(Integer roleId,Integer[] delAuthorityIds);
	
	void deleteByRoleId(Integer roleId,String operator);
}
