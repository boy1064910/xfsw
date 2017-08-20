package com.xfsw.auth.cache.service;

import java.util.List;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.model.AuthorityModel;

public interface AuthorityCacheService {

	/**
	 * 通过菜单权限ID数组获取菜单数据
	 * @param categoryAuthorityIds
	 * @return
	 * @author liuxifan
	 */
	List<CategoryAuthority> getCategoryAuthorityList(Integer[] categoryAuthorityIds);
	
	AuthorityModel getAuthorityModelById(Integer id);
	
	void refresh();
}
