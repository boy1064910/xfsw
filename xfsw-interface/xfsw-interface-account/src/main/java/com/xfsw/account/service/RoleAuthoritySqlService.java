package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.RoleAuthoritySql;
import com.xfsw.account.model.RoleAuthoritySqlModel;


public interface RoleAuthoritySqlService {
	
	RoleAuthoritySql get(RoleAuthoritySql roleAuthoritySql);
	
	List<RoleAuthoritySql> selectAll();
	
	List<RoleAuthoritySql> selectList(RoleAuthoritySql roleAuthoritySql);
	
	void deleteAndBak(Integer authorityHashId,String operator);
	
	void deleteAndBakByRoleIdAndAuthorityIds(Integer roleId,Integer[] delAuthorityIds);
	
	List<RoleAuthoritySqlModel> selectRoleAuthoritySqlModelListByRoleId(Integer roleId);
	
	RoleAuthoritySql getRoleAuthoritySqlById(Integer id);
	
	void updateRoleAuthoritySql(RoleAuthoritySql roleAuthoritySql,
			Integer[] signs,String[] paramNames,String[] paramSqls,String[] countParamSqls,
			Integer[] editParamIds,Integer[] editSigns,String[] editParamNames,String[] editParamSqls,String[] editCountParamSqls,
			Integer[] delParamIds);
	
	void deleteByRoleId(Integer roleId,String operator);
	
	void deleteById(Integer id,String operator);
}
