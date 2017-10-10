package com.xfsw.account.service;

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
	 * 刷新权限缓存信息
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void reloadCache();
	
	/**
	 * 刷新菜单权限缓存信息信息
	 * @param categoryAuthorityId
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void reloadCategoryAuthorityCache(Integer categoryAuthorityId);
	
	/**
	 * 刷新菜单权限缓存信息信息
	 * @param categoryAuthorityId			新增的菜单权限ID
	 * @param removeCategoryAuthorityId		删除的菜单权限ID
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void reloadCategoryAuthorityCache(Integer categoryAuthorityId,Integer removeCategoryAuthorityId);
	
	/**
	 * 刷新功能权限缓存信息信息 
	 * @param linkAuthorityId
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void reloadLinkAuthorityCache(Integer linkAuthorityId);
	
	/**
	 * 刷新功能权限缓存信息信息
	 * @param linkAuthorityId			新增的功能权限ID
	 * @param removeLinkAuthorityId		删除的功能权限ID
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void reloadLinkAuthorityCache(Integer linkAuthorityId,Integer removeLinkAuthorityId);
	
}
