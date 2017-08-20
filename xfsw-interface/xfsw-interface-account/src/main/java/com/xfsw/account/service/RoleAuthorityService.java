/**
 * 
 */
package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.model.AuthorityModel;
import com.xfsw.account.model.UserAuthorityIdsModel;

/**
 * @author {xiaopeng.liu@decked.com.cn} 用户数据服务接口
 */
public interface RoleAuthorityService {

	/**
	 * 查询菜单权限和链接权限的hash id集合，按照hash id正序排序
	 * @param roleId
	 * @return
	 * @author xiaopeng.liu@decked.com.cn 2016年8月16日下午12:43:54
	 */
	public UserAuthorityIdsModel selectAllAuthorityHashIdsByRoleId(Integer roleId);
	
	List<Integer> selectUnionAuthorityIdListByRoleId(Integer roleId);
	
	List<AuthorityModel> selectAuthorityModelListByPid(Integer pid);

}
