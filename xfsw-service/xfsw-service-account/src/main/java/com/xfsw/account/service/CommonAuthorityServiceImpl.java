package com.xfsw.account.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.CommonAuthority;
import com.xfsw.account.service.CommonAuthorityService;
import com.xfsw.common.mapper.ICommonMapper;

@Service("commonAuthorityService")
public class CommonAuthorityServiceImpl implements CommonAuthorityService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;

	@Override
	public CommonAuthority getById(Integer id) {
		return (CommonAuthority) commonMapper.get(CommonAuthority.class, id);
	}

	@Override
	public void insertCommonAuthority(CommonAuthority commonAuthority) {
		commonMapper.insert("CommonAuthority.insertCommonAuthority", commonAuthority);
	}

	@Override
	public void updateCommonAuthority(CommonAuthority commonAuthority) {
		commonMapper.update("CommonAuthority.updateCommonAuthority", commonAuthority);
	}
	
	@Transactional
	public void deleteById(Integer id,String operator){
		commonMapper.deleteAndBak(CommonAuthority.class, id, operator);
	}
	
}
