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
	void reload();
	
}
