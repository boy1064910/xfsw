package com.xfsw.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.account.model.AuthorityModel;
import com.xfsw.account.model.UserAuthorityIdsModel;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ListUtil;

@Service("roleAuthorityService")
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

	@Resource(name="accountCommonMapper")
	private ICommonMapper commonMapper;
	
	public UserAuthorityIdsModel selectAllAuthorityHashIdsByRoleId(Integer roleId){
		//读取该角色下所有权限（公共、菜单、请求）
		List<?> authorityIdList = commonMapper.selectList("RoleAuthority.selectAllAuthorityHashIdsByRoleId", roleId);
		Integer[] authorityIds = null;
		if(!ListUtil.isEmpty(authorityIdList)){
			authorityIds = new Integer[authorityIdList.size()];
			authorityIdList.toArray(authorityIds);
		}
		
		//读取该角色所有菜单权限
		List<?> categoryAuthorityList = commonMapper.selectList("RoleAuthority.selectCategoryAuthorityByRoleId",roleId);
		Integer[] categoryAuthorityIds = null;
		if(!ListUtil.isEmpty(categoryAuthorityList)){
			categoryAuthorityIds = new Integer[categoryAuthorityList.size()];
			categoryAuthorityList.toArray(categoryAuthorityIds);
		}
		return new UserAuthorityIdsModel(authorityIds,categoryAuthorityIds);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> selectUnionAuthorityIdListByRoleId(Integer roleId){
		return (List<Integer>) commonMapper.selectList("RoleAuthority.selectUnionAuthorityIdListByRoleId",roleId);
	}
	
	@SuppressWarnings("unchecked")
	public List<AuthorityModel> selectAuthorityModelListByPid(Integer pid){
		return (List<AuthorityModel>) commonMapper.selectList("RoleAuthority.selectAuthorityModelListByPid",pid);
	}
}
