/**
 * 
 */
package com.xfsw.account.service;

import com.xfsw.account.model.UserAuthorityIdsModel;

/**
 * @author {xiaopeng.liu@decked.com.cn} 用户数据服务接口
 */
public interface RoleAuthorityService {

	/**
	 * 查询用户拥有的所有权限ID和菜单权限ID
	 * @param userId
	 * @param tenantId
	 * @return			菜单权限ID按照权限排序规则排列（pid,orderIndex）
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public UserAuthorityIdsModel selectAllAuthorityHashIdsByRoleId(Integer userId,Integer tenantId);
	
//	List<Integer> selectUnionAuthorityIdListByRoleId(Integer roleId);
	
//	List<AuthorityModel> selectAuthorityModelListByPid(Integer pid);

}
