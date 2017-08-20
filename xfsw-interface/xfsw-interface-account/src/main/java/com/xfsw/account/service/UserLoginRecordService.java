/**
 * 
 */
package com.xfsw.account.service;

/**
 * 用户登录记录服务接口
 * @author lxp
 */
public interface UserLoginRecordService {

	void record(Integer userId,String ip);
}
