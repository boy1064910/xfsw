package com.xfsw.account.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

@Service("defaultAuthorityService")
public class DefaultAuthorityServiceImpl implements DefaultAuthorityService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	

}
