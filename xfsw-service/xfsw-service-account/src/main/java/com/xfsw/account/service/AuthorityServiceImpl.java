package com.xfsw.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.account.model.AuthorityModel;
import com.xfsw.account.service.AuthorityService;
import com.xfsw.common.mapper.ICommonMapper;

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@SuppressWarnings("unchecked")
	public List<AuthorityModel> selectAll(){
		return (List<AuthorityModel>) commonMapper.selectList("Authority.selectAuthorityList");
	}
}
