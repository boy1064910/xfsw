package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.DefaultAuthority;

/**
 * 默认权限服务接口
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface DefaultAuthorityService {

	/**
	 * 查询所有默认权限数据
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<DefaultAuthority> selectAll();
	
	/**
	 * 插入默认权限数据
	 * @param defaultAuthority
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void insertDefaultAuthority(DefaultAuthority defaultAuthority);
	
	/**
	 * 更新默认权限数据
	 * @param defaultAuthority
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void updateDefaultAuthority(DefaultAuthority defaultAuthority);
	
	/**
	 * 根据ID获取默认权限数据
	 * @param id
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	DefaultAuthority getById(Integer id);
}
