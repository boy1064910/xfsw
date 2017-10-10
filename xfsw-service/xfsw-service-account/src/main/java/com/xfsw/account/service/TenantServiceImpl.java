package com.xfsw.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.account.entity.Tenant;
import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;
import com.xfsw.common.mapper.ICommonMapper;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Service("tenantService")
public class TenantServiceImpl implements TenantService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;

	@Override
	public List<Tenant> selectAll() {
		return commonMapper.selectAll(Tenant.class);
	}

	@Override
	public DataTableResponseModel selectPageInfo(DataTablePageInfo pageInfo) {
		return commonMapper.selectPage("Tenant.selectCount", "Tenant.selectPageInfo", pageInfo, null);
	}

	@Override
	public void insertTenant(Tenant tenant) {
		commonMapper.insert(Tenant.class, tenant);
	}

	@Override
	public void updateTenant(Tenant tenant) {
		commonMapper.update("Tenant.updateTenant", tenant);
	}

	@Override
	public Tenant getById(Integer id) {
		return commonMapper.get(Tenant.class, id);
	}
}
