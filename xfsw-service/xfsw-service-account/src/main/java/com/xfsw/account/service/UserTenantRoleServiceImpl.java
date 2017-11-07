/**
 * 
 */
package com.xfsw.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.xfsw.account.entity.UserTenantRole;
import com.xfsw.common.mapper.ICommonMapper;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Service("userTenantRoleService")
public class UserTenantRoleServiceImpl implements UserTenantRoleService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public List<UserTenantRole> selectListByRoleId(Integer roleId) {
		String sql = "SELECT * FROM UserTenantRole WHERE roleId = #{roleId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		return commonMapper.selectListBySql(sql, params, UserTenantRole.class);
	}

	@Override
	public List<UserTenantRole> selectListByRoleIdList(List<Integer> roleIdList){
		if(CollectionUtils.isEmpty(roleIdList)){
			return null;
		}
		return commonMapper.selectList("UserTenantRole.selectListByRoleIds", roleIdList);
	}
}
