/**
 * 
 */
package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.model.AuthorityModel;

/**
 * 菜单权限服务接口
 * @author liuxifan
 *
 */
public interface AuthorityService {

	/**
	 * 查询所有菜单权限和链接权限数据
	 * @return
	 * @author liuxifan
	 */
	List<AuthorityModel> selectAll();
}
