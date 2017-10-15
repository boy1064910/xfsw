package com.xfsw.account.service;

import java.util.List;

public interface RoleCategoryAuthorityService {

	/**
	 * 角色配置菜单权限信息
	 * @param categoryAuthorityIdList
	 * @param linkAuthorityIdList
	 * @param roleId
	 * @param operator
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void configAuthority(List<Integer> categoryAuthorityIdList,List<Integer> linkAuthorityIdList,Integer roleId,String operator);
	
	/**
	 * 保存角色菜单权限关系信息
	 * @param roleId
	 * @param addAuthorityIds
	 * @param operator
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void insertRoleCategoryAuthorityList(Integer roleId,List<Integer> addAuthorityIds,String operator);
	
	void delete(Integer authorityId,String operator);
	
	List<Integer> selectAuthorityIdsByRoleId(Integer roleId);
	
	void deleteAndBakByRoleIdAndAuthorityIds(Integer roleId,Integer[] delAuthorityIds);
	
	void deleteByRoleId(Integer roleId,String operator);
}
