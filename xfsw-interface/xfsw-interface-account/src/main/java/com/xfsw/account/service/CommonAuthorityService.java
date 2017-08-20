package com.xfsw.account.service;

import com.xfsw.account.entity.CommonAuthority;

public interface CommonAuthorityService {

	CommonAuthority getById(Integer id);
	
	void insertCommonAuthority(CommonAuthority commonAuthority);
	
	void updateCommonAuthority(CommonAuthority commonAuthority);
	
	void deleteById(Integer id,String operator);
}
