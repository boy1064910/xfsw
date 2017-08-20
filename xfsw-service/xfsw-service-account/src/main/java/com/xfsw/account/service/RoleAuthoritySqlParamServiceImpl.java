package com.xfsw.account.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.RoleAuthoritySql;
import com.xfsw.account.entity.RoleAuthoritySqlParam;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ArrayUtil;

@Service("roleAuthoritySqlParamService")
public class RoleAuthoritySqlParamServiceImpl implements RoleAuthoritySqlParamService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@SuppressWarnings("unchecked")
	public List<RoleAuthoritySqlParam> selectList(Map<String,Object> params){
		return (List<RoleAuthoritySqlParam>) commonMapper.selectList(RoleAuthoritySqlParam.class, params);
	}
	
	@SuppressWarnings("unchecked")
	public List<RoleAuthoritySqlParam> selectList(RoleAuthoritySqlParam roleAuthoritySqlParam){
		return (List<RoleAuthoritySqlParam>) commonMapper.selectList(RoleAuthoritySqlParam.class, roleAuthoritySqlParam);
	}
	
	@Transactional
	public void deleteAndBak(Integer roleAuthoritySqlId,String operator){
		RoleAuthoritySqlParam roleAuthoritySqlParam = new RoleAuthoritySqlParam();
		roleAuthoritySqlParam.setRoleAuthoritySqlId(roleAuthoritySqlId);
		commonMapper.deleteAndBak(RoleAuthoritySqlParam.class, roleAuthoritySqlParam, operator);
	}
	
	@Transactional
	public void deleteAndBakByRoleIdAndAuthorityIds(Integer roleId,Integer[] delAuthorityIds){
		if(ArrayUtil.isEmpty(delAuthorityIds)){
			return;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		params.put("authorityIds", delAuthorityIds);
		commonMapper.insert("RoleAuthoritySqlParam.bakByRoleIdAndAuthorityIds", params);//备份
		commonMapper.delete("RoleAuthoritySqlParam.deleteByRoleIdAndAuthorityIds", params);//删除
	}
	
	public List<RoleAuthoritySqlParam> selectList(Integer roleAuthoritySqlId){
		RoleAuthoritySqlParam roleAuthoritySqlParam = new RoleAuthoritySqlParam();
		roleAuthoritySqlParam.setRoleAuthoritySqlId(roleAuthoritySqlId);
		return this.selectList(roleAuthoritySqlParam);
	}
	
	@Transactional
	public void updateRoleAuthoritySqlParamInfo(
			RoleAuthoritySql roleAuthoritySql,
			Integer[] signs,String[] paramNames,String[] paramSqls,String[] countParamSqls,
			Integer[] editParamIds,Integer[] editSigns,String[] editParamNames,String[] editParamSqls,String[] editCountParamSqls,
			Integer[] delParamIds){
		
		//通過delParamIds數組刪除RoleAuthoritySqlParam參數配置數據
		if(!ArrayUtil.isEmpty(delParamIds)){
			commonMapper.deleteAndBak(RoleAuthoritySqlParam.class, "id", delParamIds, roleAuthoritySql.getLastUpdater());
		}
		
		//批量更新RoleAuthoritySqlParam數據
		if(!ArrayUtil.isEmpty(editParamIds)){
			for(int i=0;i<editParamIds.length;i++){
				RoleAuthoritySqlParam param = new RoleAuthoritySqlParam(
						editParamIds[i], editParamNames[i], editParamSqls[i], 
						editCountParamSqls==null?null:(editCountParamSqls.length==0?null:editCountParamSqls[i]), 
								editSigns[i], roleAuthoritySql.getLastUpdater(),new Date());
				commonMapper.update("RoleAuthoritySqlParam.updateRoleAuthoritySqlParam", param);
			}
		}
		
		if(!ArrayUtil.isEmpty(paramNames)){
			for(int i=0;i<paramNames.length;i++){
				RoleAuthoritySqlParam param = new RoleAuthoritySqlParam(paramNames[i], paramSqls[i], countParamSqls==null?null:(countParamSqls.length==0?null:countParamSqls[i]), signs[i], roleAuthoritySql.getLastUpdater(),new Date(),roleAuthoritySql.getId());
				commonMapper.insert(RoleAuthoritySqlParam.class, param);
			}
		}
	}

	@Override
	public void deleteByRoleId(Integer roleId, String operator) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		String roleAuthoritySqlParamDel = "DELETE FROM RoleAuthoritySqlParam WHERE roleAuthoritySqlId IN (SELECT id FROM RoleAuthoritySql WHERE roleId = #{roleId})";
		commonMapper.deleteAndBak(roleAuthoritySqlParamDel, RoleAuthoritySqlParam.class, params, operator);
	}
	
	public void deleteByRoleAuthoritySqlId(Integer roleAuthoritySqlId,String operator){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleAuthoritySqlId", roleAuthoritySqlId);
		commonMapper.deleteAndBak(RoleAuthoritySqlParam.class, params, operator);
	}
}
