package com.xfsw.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.DefaultAuthority;
import com.xfsw.common.mapper.ICommonMapper;

@Service("defaultAuthorityService")
public class DefaultAuthorityServiceImpl implements DefaultAuthorityService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="defaultLinkAuthorityService")
	DefaultLinkAuthorityService defaultLinkAuthorityService;

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
	
	@Override
	@Transactional
	public void deleteDefaultAuthority(Integer defaultAuthorityId,String operator){
		commonMapper.deleteAndBak(DefaultAuthority.class, defaultAuthorityId, operator);
		defaultLinkAuthorityService.deleteByDefaultAuthorityId(defaultAuthorityId, operator);
	}

}
