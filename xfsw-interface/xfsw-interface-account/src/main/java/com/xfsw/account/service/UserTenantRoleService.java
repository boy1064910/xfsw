/**
 * 
 */
package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.UserTenantRole;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface UserTenantRoleService {

	/**
	 * 根据角色ID查询用户和空间信息
	 * @param roleId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<UserTenantRole> selectListByRoleId(Integer roleId);
}
