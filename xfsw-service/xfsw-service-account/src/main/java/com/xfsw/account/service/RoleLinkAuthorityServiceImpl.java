package com.xfsw.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.RoleLinkAuthority;
import com.xfsw.account.service.RoleLinkAuthorityService;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ArrayUtil;
import com.xfsw.common.util.ListUtil;

@Service("roleLinkAuthorityService")
public class RoleLinkAuthorityServiceImpl implements RoleLinkAuthorityService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public void deleteRoleLinkAuthority(Integer authorityId,String operator) {
		RoleLinkAuthority roleLinkAuthority = new RoleLinkAuthority();
		roleLinkAuthority.setAuthorityId(authorityId);
		commonMapper.deleteAndBak(RoleLinkAuthority.class, roleLinkAuthority, operator);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> selectAuthorityIdsByRoleId(Integer roleId){
		return (List<Integer>) commonMapper.selectList("RoleLinkAuthority.selectAuthorityIdsByRoleId",roleId);
	}
	
	public void insertRoleLinkAuthorityList(Integer roleId,List<Integer> addAuthorityIds,String operator){
		if(ListUtil.isEmpty(addAuthorityIds)) return;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		params.put("authorityIds", addAuthorityIds);
		params.put("lastUpdater", operator);
		commonMapper.insert("RoleLinkAuthority.insertRoleLinkAuthorityList", params);
	}
	
	@Transactional
	public void deleteAndBakByRoleIdAndAuthorityIds(Integer roleId,Integer[] delAuthorityIds){
		if(ArrayUtil.isEmpty(delAuthorityIds)){
			return;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		params.put("authorityIds", delAuthorityIds);
		commonMapper.insert("RoleLinkAuthority.bakByRoleIdAndAuthorityIds", params);//备份
		commonMapper.delete("RoleLinkAuthority.deleteByRoleIdAndAuthorityIds", params);//删除
	}
	
	public void deleteByRoleId(Integer roleId,String operator){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		commonMapper.deleteAndBak(RoleLinkAuthority.class, params, operator);
	}
}
