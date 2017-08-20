package com.xfsw.auth.cache.service;

import com.xfsw.account.entity.RoleAuthoritySql;

/**
 * 系统角色权限sql缓存服务
 * 
 * @author xiaopeng.liu@dekced.com.cn
 * 2016年10月13日上午9:49:53
 */
public interface RoleAuthoritySqlCacheService {

	RoleAuthoritySql get(Integer roleId,Integer authorityHashId);
	
	RoleAuthoritySql reload(Integer roleId,Integer authorityHashId);
	
//	void reloadByRoleId(Integer roleId);
//	
	void authorityHashIdChangeEvent(Integer authorityHashId,Integer oldAuthorityHashId);
	
	void authorityHashIdDeleteEvent(Integer authorityHashId);
	
	void reloadByRoleId(Integer roleId);
	
	void deleteByRoleId(Integer roleId);
}
