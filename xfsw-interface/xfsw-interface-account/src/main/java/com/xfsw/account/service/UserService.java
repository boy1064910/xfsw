package com.xfsw.account.service;

import com.xfsw.account.model.UserModel;
import com.xfsw.common.classes.BusinessException;

/**
 * 账号服务接口
 * @author lxp
 */
public interface UserService {

	/**
	 * 用户登录
	 * @param account
	 * @param pwd
	 * @param ip
	 * @return
	 * @throws BusinessException	业务异常信息
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	UserModel login(String account,String pwd,String ip) throws BusinessException;
	
	/**
	 * 用户切换使用空间
	 * @param userTenantId
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void switchTenant(Integer userTenantId);
	
	/**
	 * 微信登录和注册逻辑，使用微信的unionId进行登录，调用本接口之前请先做好相关的业务逻辑校验
	 * @param unionId	微信平台用户的unionId
	 * @param ip		用户请求登录的IP地址
	 * @return
	 * @author liuxifan
	 */
//	UserModel wxLogin(String unionId,String ip);
	
	/**
	 * 微信绑定账号逻辑，调用之前请先做好相关业务的逻辑校验，同时记录登录日志
	 * @param account
	 * @param unionId	微信开放平台unionId
	 * @param openId	用户相对于微信小程序的openId
	 * @param nickName
	 * @param head
	 * @param ip
	 * @return
	 * @author liuxifan
	 */
//	User wxBindAccount(String account,String unionId,String openId,String nickName,String head,String ip) throws BusinessException;
	
//	boolean checkByRoleId(Integer roleId);
	
//	/**
//	 * 通过用户ID数组获取用户基础信息数组
//	 * @param userIds
//	 * @return
//	 * @author liuxifan
//	 */
//	List<UserInfoModel> selectUserInfoListByIds(Integer[] userIds);
	
//	User getById(Integer id);
	
//	User getByAccount(String account);
	
	/**
	 * 分配角色
	 * @param userId
	 * @param roleId
	 * @author liuxifan
	 */
//	void distributeRoleId(Integer userId,Integer roleId);
	
	/**
	 * 更新用户角色ID
	 * @param oldRoleId
	 * @param newRoleId
	 * @author liuxifan
	 */
//	void updateRoleIdByOldRoleId(Integer oldRoleId,Integer newRoleId);
	
//	List<User> selectUserListByUserIds(Integer[] userIds);
	
	//=========================前端接口=========================
	/**
	 * 通过用户ID数组查询数据（id,head）
	 * @param userIds
	 * @return
	 */
//	List<User> selectListByUserIds(Integer[] userIds);
	
//	String getHeadByUserId(Integer id);
}
