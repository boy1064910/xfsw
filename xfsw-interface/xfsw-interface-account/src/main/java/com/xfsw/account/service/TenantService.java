package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.Tenant;
import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;

/**
 * 空间服务接口
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface TenantService {

	/**
	 * 查询所有空间信息
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<Tenant> selectAll();
	
	/**
	 * 空间信息分页查询
	 * @param pageInfo
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	DataTableResponseModel selectPageInfo(DataTablePageInfo pageInfo);
	
	/**
	 * 新增空间信息
	 * @param tenant
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void insertTenant(Tenant tenant);
	
	/**
	 * 更新空间信息
	 * @param tenant
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void updateTenant(Tenant tenant);
	
	/**
	 * 根据ID获取空间信息
	 * @param id
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	Tenant getById(Integer id);
	
	/**
	 * 初始化空间权限数据(复制所有默认权限-->菜单权限和默认功能权限-->功能权限)
	 * @param tenantId
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void configDefaultAuthority(Integer tenantId);
}
