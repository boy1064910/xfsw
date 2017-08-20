package com.xfsw.account.service;

import java.util.List;
import java.util.Map;

import com.xfsw.account.entity.RoleAuthoritySql;
import com.xfsw.account.entity.RoleAuthoritySqlParam;


public interface RoleAuthoritySqlParamService {
	
	List<RoleAuthoritySqlParam> selectList(Map<String,Object> params);
	
	List<RoleAuthoritySqlParam> selectList(RoleAuthoritySqlParam roleAuthoritySqlParam);
	
	void deleteAndBak(Integer roleAuthoritySqlId,String operator);
	
	void deleteAndBakByRoleIdAndAuthorityIds(Integer roleId,Integer[] delAuthorityIds);
	
	List<RoleAuthoritySqlParam> selectList(Integer roleAuthoritySqlId);
	
	void updateRoleAuthoritySqlParamInfo(
			RoleAuthoritySql roleAuthoritySql,
			Integer[] signs,String[] paramNames,String[] paramSqls,String[] countParamSqls,
			Integer[] editParamIds,Integer[] editSigns,String[] editParamNames,String[] editParamSqls,String[] editCountParamSqls,
			Integer[] delParamIds);
	
	void deleteByRoleId(Integer roleId,String operator);
	
	void deleteByRoleAuthoritySqlId(Integer roleAuthoritySqlId,String operator);
}
