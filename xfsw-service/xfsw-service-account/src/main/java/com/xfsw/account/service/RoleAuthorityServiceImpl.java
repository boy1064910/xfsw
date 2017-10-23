package com.xfsw.account.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.xfsw.account.entity.UserTenantRole;
import com.xfsw.account.model.UserAuthorityIdsModel;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ListUtil;

@Service("roleAuthorityService")
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

	@Resource(name="accountCommonMapper")
	private ICommonMapper commonMapper;
	
	@Resource(name="userTenantRoleService")
	UserTenantRoleService userTenantRoleService;
	
	@Override
	public UserAuthorityIdsModel selectAllAuthorityHashIdsByUserInfo(Integer userId,Integer tenantId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("tenantId", tenantId);
		//读取该角色下所有权限（公共、菜单、请求）
		List<?> authorityIdList = commonMapper.selectList("RoleAuthority.selectAllAuthorityHashIdsByRoleId", params);
		Integer[] authorityIds = null;
		if(!ListUtil.isEmpty(authorityIdList)){
			authorityIds = new Integer[authorityIdList.size()];
			authorityIdList.toArray(authorityIds);
		}
		
		//读取该角色所有菜单权限
		List<?> categoryAuthorityList = commonMapper.selectList("RoleAuthority.selectCategoryAuthorityByRoleId",params);
		Integer[] categoryAuthorityIds = null;
		if(!ListUtil.isEmpty(categoryAuthorityList)){
			categoryAuthorityIds = new Integer[categoryAuthorityList.size()];
			categoryAuthorityList.toArray(categoryAuthorityIds);
		}
		return new UserAuthorityIdsModel(userId,tenantId,authorityIds,categoryAuthorityIds);
	}
	
	@Override
	public List<UserAuthorityIdsModel> selectAllUserAuthorityByRoleId(Integer roleId){
		List<UserTenantRole> userTenantRoleList = userTenantRoleService.selectListByRoleId(roleId);
		if(CollectionUtils.isEmpty(userTenantRoleList)){
			return null;
		}
		List<UserAuthorityIdsModel> userAuthorityIdsModelList = new ArrayList<UserAuthorityIdsModel>();
		for(UserTenantRole userTenantRole:userTenantRoleList){
			userAuthorityIdsModelList.add(this.selectAllAuthorityHashIdsByUserInfo(userTenantRole.getUserId(), userTenantRole.getTenantId()));
		}
		return userAuthorityIdsModelList;
	}
	
	@Override
	public List<Integer> selectUnionAuthorityIdListByRoleId(Integer roleId){
		return commonMapper.selectList("RoleAuthority.selectUnionAuthorityIdListByRoleId",roleId);
	}
	
}
