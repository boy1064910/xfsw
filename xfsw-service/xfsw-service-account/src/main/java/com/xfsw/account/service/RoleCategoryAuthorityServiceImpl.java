package com.xfsw.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.RoleCategoryAuthority;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ArrayUtil;

@Service("roleCategoryAuthorityService")
public class RoleCategoryAuthorityServiceImpl implements RoleCategoryAuthorityService {

	@Resource(name="accountCommonMapper")
	private ICommonMapper commonMapper;
	
	@Resource(name="roleLinkAuthorityService")
	private RoleLinkAuthorityService roleLinkAuthorityService;
	
	@Override
	@Transactional
	public void configAuthority(List<Integer> categoryAuthorityIdList,List<Integer> linkAuthorityIdList,Integer roleId,String operator){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		params.put("authorityIds", categoryAuthorityIdList);
		params.put("lastUpdater", operator);
		commonMapper.insert("RoleCategoryAuthority.insertRoleCategoryAuthorityList", params);
		
		roleLinkAuthorityService.insertRoleLinkAuthorityList(roleId, linkAuthorityIdList, operator);
	}
	
	@Override
	@Transactional
	public void insertRoleCategoryAuthorityList(Integer roleId,List<Integer> addAuthorityIds,String operator){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		params.put("authorityIds", addAuthorityIds);
		params.put("lastUpdater", operator);
		commonMapper.insert("RoleCategoryAuthority.insertRoleCategoryAuthorityList", params);
	}
	
	@Transactional
	public void delete(Integer authorityId,String operator){
		RoleCategoryAuthority roleAuthority = new RoleCategoryAuthority();
		roleAuthority.setAuthorityId(authorityId);
		commonMapper.deleteAndBak(RoleCategoryAuthority.class, roleAuthority, operator);
	}
	
	public List<Integer> selectAuthorityIdsByRoleId(Integer roleId){
		return commonMapper.selectList("RoleCategoryAuthority.selectAuthorityIdsByRoleId",roleId);
	}
	
	@Transactional
	public void deleteAndBakByRoleIdAndAuthorityIds(Integer roleId,Integer[] delAuthorityIds){
		if(ArrayUtil.isEmpty(delAuthorityIds)){
			return;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		params.put("authorityIds", delAuthorityIds);
		commonMapper.insert("RoleCategoryAuthority.bakByRoleIdAndAuthorityIds", params);//备份
		commonMapper.delete("RoleCategoryAuthority.deleteByRoleIdAndAuthorityIds", params);//删除
	}
	
	public void deleteByRoleId(Integer roleId,String operator){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		commonMapper.deleteAndBak(RoleCategoryAuthority.class, params, operator);
	}
}
