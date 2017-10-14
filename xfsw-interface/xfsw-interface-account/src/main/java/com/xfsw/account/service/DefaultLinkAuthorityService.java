/**
 * 
 */
package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.DefaultLinkAuthority;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface DefaultLinkAuthorityService {

	/**
	 * 根据默认菜单权限查询默认功能权限数据
	 * @param categoryAuthorityId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<DefaultLinkAuthority> selectListByDefaultAuthorityId(Integer defaultAuthorityId);
	
	/**
	 * 生成默认功能权限数据
	 * @param defaultLinkAuthority
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void insertDefaultLinkAuthority(DefaultLinkAuthority defaultLinkAuthority);
	
	/**
	 * 删除默认功能权限数据
	 * @param id
	 * @param operator
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void deleteById(Integer id,String operator);
	
	/**
	 * 通过默认菜单权限ID删除菜单功能权限数据
	 * @param defaultAuthorityId
	 * @param operator
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void deleteByDefaultAuthorityId(Integer defaultAuthorityId,String operator);
	
	/**
	 * 查询所有的默认功能权限数据
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<DefaultLinkAuthority> selectAll();
}
