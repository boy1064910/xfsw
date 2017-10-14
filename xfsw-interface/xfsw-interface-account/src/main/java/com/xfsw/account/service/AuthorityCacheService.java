package com.xfsw.account.service;

import java.util.Map;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.model.AuthorityModel;
import com.xfsw.common.classes.BusinessException;

public interface AuthorityCacheService {

	/**
	 * 获取权限信息
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	AuthorityModel getAuthorityModelById(Integer id) throws BusinessException;
	
	/**
	 * 获取菜单权限数据
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	Map<String,CategoryAuthority> getCategorytAuthorityMap();
	
	/**
	 * 刷新菜单权限缓存信息信息
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void reloadCategoryAuthorityIntoAuthorityCache();
	
	/**
	 * 刷新功能权限缓存信息信息 
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void reloadLinkAuthorityIntoAuthorityCache();
}
