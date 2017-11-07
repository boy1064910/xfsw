package com.xfsw.account.service;

import java.util.List;

public interface RoleLinkAuthorityService {

	/**
	 * 角色配置菜单权限
	 * @param roleId
	 * @param addAuthorityIds
	 * @param operator
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void insertRoleLinkAuthorityList(Integer roleId,List<Integer> addAuthorityIds,String operator);
	
	void deleteRoleLinkAuthority(Integer authorityId,String operator);
	
	List<Integer> selectAuthorityIdsByRoleId(Integer roleId);
	
	void deleteAndBakByRoleIdAndAuthorityIds(Integer roleId,Integer[] delAuthorityIds);
	
	void deleteByRoleId(Integer roleId,String operator);
	
	/**
	 * 根据菜单ID查询关联的角色ID数组
	 * @param authorityId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<Integer> selectRoleIdListByLinkAuthorityId(Integer linkAuthorityId);
}
