package com.xfsw.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.RoleAuthoritySql;
import com.xfsw.account.model.RoleAuthoritySqlModel;
import com.xfsw.account.service.RoleAuthoritySqlParamService;
import com.xfsw.account.service.RoleAuthoritySqlService;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ArrayUtil;
import com.xfsw.common.util.ListUtil;

@Service("roleAuthoritySqlService")
public class RoleAuthoritySqlServiceImpl implements RoleAuthoritySqlService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="roleAuthoritySqlParamService")
	RoleAuthoritySqlParamService roleAuthoritySqlParamService;
	
	public RoleAuthoritySql get(RoleAuthoritySql roleAuthoritySql){
		return (RoleAuthoritySql) commonMapper.get(RoleAuthoritySql.class, roleAuthoritySql);
	}
	
	@SuppressWarnings("unchecked")
	public List<RoleAuthoritySql> selectAll(){
		return (List<RoleAuthoritySql>) commonMapper.selectAll(RoleAuthoritySql.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<RoleAuthoritySql> selectList(RoleAuthoritySql roleAuthoritySql){
		return (List<RoleAuthoritySql>) commonMapper.selectList(RoleAuthoritySql.class, roleAuthoritySql);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void deleteAndBak(Integer authorityHashId,String operator){
		RoleAuthoritySql roleAuthoritySql = new RoleAuthoritySql();
		roleAuthoritySql.setAuthorityHashId(authorityHashId);
		
		List<RoleAuthoritySql> roleAuthoritySqlList = (List<RoleAuthoritySql>) commonMapper.selectList(RoleAuthoritySql.class, roleAuthoritySql);
		if(!ListUtil.isEmpty(roleAuthoritySqlList)){
			for(RoleAuthoritySql sql:roleAuthoritySqlList){
				commonMapper.deleteAndBak(RoleAuthoritySql.class, sql, operator);
				roleAuthoritySqlParamService.deleteAndBak(sql.getId(), operator);
			}
		}
	}
	
	@Transactional
	public void deleteAndBakByRoleIdAndAuthorityIds(Integer roleId,Integer[] delAuthorityIds){
		if(ArrayUtil.isEmpty(delAuthorityIds)){
			return;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		params.put("authorityIds", delAuthorityIds);
		commonMapper.insert("RoleAuthoritySql.bakByRoleIdAndAuthorityIds", params);//备份
		commonMapper.delete("RoleAuthoritySql.deleteByRoleIdAndAuthorityIds", params);//删除
	}
	
	@SuppressWarnings("unchecked")
	public List<RoleAuthoritySqlModel> selectRoleAuthoritySqlModelListByRoleId(Integer roleId){
		List<RoleAuthoritySqlModel> modelList = (List<RoleAuthoritySqlModel>) commonMapper.selectList("RoleAuthoritySql.selectRoleAuthoritySqlModelListByRoleId",roleId);
		for(RoleAuthoritySqlModel model:modelList){
			if(model.getRoleAuthoritySqlId()!=null)
				model.setParams(roleAuthoritySqlParamService.selectList(model.getRoleAuthoritySqlId()));
		}
		return modelList;
	}
	
	public RoleAuthoritySql getRoleAuthoritySqlById(Integer id){
		RoleAuthoritySql roleAuthoritySql = (RoleAuthoritySql) commonMapper.get(RoleAuthoritySql.class, id);
		if(roleAuthoritySql==null){
			return null;
		}
		roleAuthoritySql.setRoleAuthoritySqlParamList(roleAuthoritySqlParamService.selectList(roleAuthoritySql.getId()));
		return roleAuthoritySql;
	}
	
	@Transactional
	public void updateRoleAuthoritySql(RoleAuthoritySql roleAuthoritySql,
			Integer[] signs,String[] paramNames,String[] paramSqls,String[] countParamSqls,
			Integer[] editParamIds,Integer[] editSigns,String[] editParamNames,String[] editParamSqls,String[] editCountParamSqls,
			Integer[] delParamIds){
		//插入或者更新RoleAuthoritySql
		commonMapper.update("RoleAuthoritySql.updateRoleAuthoritySql",roleAuthoritySql);
		//插入、更新、删除相关参数数据
		roleAuthoritySqlParamService.updateRoleAuthoritySqlParamInfo(roleAuthoritySql, signs, paramNames, paramSqls, countParamSqls, editParamIds, editSigns, editParamNames, editParamSqls, editCountParamSqls, delParamIds);
	}

	@Override
	public void deleteByRoleId(Integer roleId, String operator) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		String roleAuthoritySqlDel = "DELETE FROM RoleAuthoritySql WHERE roleId = #{roleId}";
		commonMapper.deleteAndBak(roleAuthoritySqlDel, RoleAuthoritySql.class, params, operator);
	}
	
	@Transactional
	public void deleteById(Integer id,String operator){
		commonMapper.deleteAndBak(RoleAuthoritySql.class, id, operator);
		roleAuthoritySqlParamService.deleteByRoleAuthoritySqlId(id, operator);
	}
}
