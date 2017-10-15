package com.xfsw.account.service;

import java.util.List;
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
	
	/**
	 * 查询父权限下所有权限数据（菜单权限和功能权限）（不走缓存）
	 * @param pid
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<AuthorityModel> selectAuthorityModelListByPid(Integer pid,Integer tenantId);
}
