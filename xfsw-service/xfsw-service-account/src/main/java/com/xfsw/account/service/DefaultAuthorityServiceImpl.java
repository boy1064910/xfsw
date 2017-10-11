package com.xfsw.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.account.entity.DefaultAuthority;
import com.xfsw.common.mapper.ICommonMapper;

@Service("defaultAuthorityService")
public class DefaultAuthorityServiceImpl implements DefaultAuthorityService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;

	@Override
	public List<DefaultAuthority> selectAll() {
		return commonMapper.selectAll(DefaultAuthority.class);
	}

	@Override
	public void insertDefaultAuthority(DefaultAuthority defaultAuthority) {
		commonMapper.insert(DefaultAuthority.class, defaultAuthority);
	}

	@Override
	public void updateDefaultAuthority(DefaultAuthority defaultAuthority) {
		
	}

	@Override
	public DefaultAuthority getById(Integer id) {
		return commonMapper.get(DefaultAuthority.class, id);
	}
	

}
