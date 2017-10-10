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
}
