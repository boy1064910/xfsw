package com.xfsw.account.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.service.CommonService;

@Service("accountService")
public class AccountServiceImpl extends CommonService implements AccountService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public ICommonMapper getCommonMapper() {
		return this.commonMapper;
	}

}
