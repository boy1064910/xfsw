package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.Tenant;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface TenantService {

	List<Tenant> selectAll();
}
