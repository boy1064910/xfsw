/**
 * 
 */
package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.model.AuthorityModel;

/**
 * 菜单权限服务接口
 * @author liuxifan
 *
 */
public interface CategoryAuthorityService {

	/**
	 * 查询所有权限数据
	 * @return
	 * @author liuxifan
	 */
	List<CategoryAuthority> selectAll();
	
	void insertAuthority(CategoryAuthority authority);
	
	CategoryAuthority get(Integer id);
	
	void deleteAuthority(Integer id,String operator);
	
	void updateCategoryAuthority(CategoryAuthority authority);
	
	List<AuthorityModel> selectFirstAuthorityModelList();
}
