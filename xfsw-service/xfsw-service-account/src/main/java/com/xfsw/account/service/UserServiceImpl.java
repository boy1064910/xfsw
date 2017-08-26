package com.xfsw.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.User;
import com.xfsw.account.model.UserInfoModel;
import com.xfsw.account.service.UserLoginRecordService;
import com.xfsw.account.service.UserService;
import com.xfsw.common.classes.BusinessException;
import com.xfsw.common.consts.ErrorConstant;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ArrayUtil;
import com.xfsw.common.util.MD5Util;
import com.xfsw.common.util.RandomUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="userLoginRecordService")
	UserLoginRecordService userLoginRecordService;
		
	@Override
	public User login(String account, String pwd,String ip) throws BusinessException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", account);
		params.put("pwd", pwd);
		User user = (User)commonMapper.get("User.login", params);
		if(user==null){
			throw new BusinessException(ErrorConstant.ACCOUNT_PWD_ERROR,"账号密码错误，请重试！");
		}
		else{
			if(user.getStatus().intValue()==-1){
				throw new BusinessException(ErrorConstant.ACCOUNT_FORBIDDEN,"账号被禁用，如有疑问，请联系平台客服！");
			}
			else{
				//记录登录日志
				userLoginRecordService.record(user.getId(),ip);
			}
		}
		return user;
	}
	
	public User wxLogin(String unionId,String ip) {
		User user = (User) commonMapper.get("User.wxLogin",unionId);
		if(user!=null){
			if(user.getStatus().intValue()==-1){
				throw new BusinessException(ErrorConstant.ACCOUNT_FORBIDDEN,"账号被禁用，如有疑问，请联系平台客服！");
			}
			else{
				//记录登录日志
				userLoginRecordService.record(user.getId(),ip);
			}
		}
		return user;
	}
	
	@Transactional
	public User wxBindAccount(String account,String unionId,String openId,String nickName,String head,String ip) throws BusinessException{
		if(commonMapper.check("User.checkByAccount",account)){
			throw new BusinessException("该手机号已被使用，如需解绑，请联系客服！");
		}
		User user = new User();
		user.setAccount(account);
		user.setPwd(MD5Util.md5(RandomUtil.getCharAndNumr(8)).toUpperCase());
		user.setNickName(nickName);
		user.setHead(head);
		user.setUnionId(unionId);
		user.setMiniOpenId(openId);
		commonMapper.insert("User.wxBindAccount", user);
		//记录登录日志
		userLoginRecordService.record(user.getId(),ip);
		return user;
	}
	
	public boolean checkByRoleId(Integer roleId){
		return commonMapper.check("User.checkByRoleId", roleId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserInfoModel> selectUserInfoListByIds(Integer[] userIds){
		return (List<UserInfoModel>) commonMapper.selectList("User.selectUserInfoListByIds",userIds);
	}
	
	public User getById(Integer id){
		return (User) commonMapper.get(User.class,id);
	}
	
	public User getByAccount(String account){
		return (User) commonMapper.get("User.getByAccount",account);
	}
	
	public void distributeRoleId(Integer userId,Integer roleId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("roleId", roleId);
		params.put("id", userId);
		commonMapper.update("User.distributeRoleId",params);
	}
	
	public void updateRoleIdByOldRoleId(Integer oldRoleId,Integer newRoleId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("oldRoleId", oldRoleId);
		params.put("newRoleId", newRoleId);
		commonMapper.update("User.updateRoleIdByOldRoleId",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> selectUserListByUserIds(Integer[] userIds) {
		if(ArrayUtil.isEmpty(userIds)) return null;
		return (List<User>) commonMapper.selectList("User.selectUserListByUserIds",userIds);
	}

	//================================前端接口================================
	@SuppressWarnings("unchecked")
	@Override
	public List<User> selectListByUserIds(Integer[] userIds) {
		if(ArrayUtil.isEmpty(userIds)) return null;
		return (List<User>) commonMapper.selectList("User.selectListByUserIds",userIds);
	}
	
	public String getHeadByUserId(Integer id){
		return (String) commonMapper.get("User.getHeadByUserId",id);
	}
}
