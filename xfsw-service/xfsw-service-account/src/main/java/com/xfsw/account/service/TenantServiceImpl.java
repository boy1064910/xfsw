package com.xfsw.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.account.entity.Tenant;
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
}
