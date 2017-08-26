/**
 * 
 */
package com.xfsw.account.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.account.entity.UserLoginRecord;
import com.xfsw.account.service.UserLoginRecordService;
import com.xfsw.common.mapper.ICommonMapper;

@Service("userLoginRecordService")
public class UserLoginRecordServiceImpl implements UserLoginRecordService {

	@Resource(name = "accountCommonMapper")
	ICommonMapper commonMapper;
	
	public void record(Integer userId,String ip){
		UserLoginRecord userLoginRecord = new UserLoginRecord(userId,ip);
		commonMapper.insert(UserLoginRecord.class, userLoginRecord);
	}
}
