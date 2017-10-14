/**
 * 
 */
package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.entity.LinkAuthority;
import com.xfsw.common.classes.BusinessException;

/**
 * 菜单权限服务接口
 * @author liuxifan
 *
 */
public interface CategoryAuthorityService {

	/**
	 * 通过菜单权限ID数组获取菜单数据
	 * @param categoryAuthorityIds
	 * @return
	 * @author liuxifan
	 */
	List<CategoryAuthority> selectListByIds(Integer[] categoryAuthorityIds) throws BusinessException;
	
	/**
	 * 查询空间下的菜单权限信息
	 * @param tenantId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<CategoryAuthority> selectListByTenantId(Integer tenantId);
	
	/**
	 * 通过ID获取菜单权限信息
	 * @param id
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	CategoryAuthority getById(Integer id);
	
	/**
	 * 新增空间菜单权限
	 * @param categoryAuthority
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void insertCategoryAuthority(CategoryAuthority categoryAuthority);
	
	/**
	 * 更新空间菜单权限
	 * @param categoryAuthority
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void updateCategoryAuthority(CategoryAuthority categoryAuthority);
	
	/**
	 * 初始化空间权限信息
	 * @param parentCategoryAuthorityList
	 * @param categoryAuthorityList
	 * @param linkAuthorityList
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void initAuthority(List<CategoryAuthority> parentCategoryAuthorityList,List<CategoryAuthority> categoryAuthorityList,List<LinkAuthority> linkAuthorityList);
	
//	void insertAuthority(CategoryAuthority authority);
//	
//	CategoryAuthority get(Integer id);
//	
//	void deleteAuthority(Integer id,String operator);
//	
//	
//	List<AuthorityModel> selectFirstAuthorityModelList();
}
