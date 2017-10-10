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
	

}
