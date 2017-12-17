package com.xfsw.account.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.xfsw.account.entity.User;
import com.xfsw.account.entity.UserTenant;
import com.xfsw.account.entity.UserTenantRole;
import com.xfsw.account.model.UserAuthorityIdsModel;
import com.xfsw.account.model.UserModel;
import com.xfsw.account.model.UserTenantModel;
import com.xfsw.account.model.wx.WxOpenIdExtra;
import com.xfsw.account.model.wx.WxUserInfo;
import com.xfsw.common.classes.BusinessException;
import com.xfsw.common.consts.ErrorConstant;
import com.xfsw.common.enums.RequestClient;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.MD5Util;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name = "accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="roleAuthorityService")
	RoleAuthorityService roleAuthorityService;
	
	@Resource(name="userLoginRecordService")
	UserLoginRecordService userLoginRecordService;
		
	@Override
	public UserModel login(String account, String pwd,String ip){
		User queryUser = new User();
		queryUser.setAccount(account);
		queryUser.setPwd(pwd);
		
		List<User> userList = commonMapper.selectList("User.login", queryUser);
		if(CollectionUtils.isEmpty(userList)){
			throw new BusinessException(ErrorConstant.ACCOUNT_PWD_ERROR,"账号密码错误，请重试！");
		}
		else{
			if(userList.size()>1) {
				logger.error("存在两个相同的账号："+account);
				throw new BusinessException(ErrorConstant.ACCOUNT_EXCEPTION,"账号存在异常，请联系平台客服！");
			}
			User user = userList.get(0);
			if(user.getStatus().intValue()==-1){
				throw new BusinessException(ErrorConstant.ACCOUNT_FORBIDDEN,"账号被禁用，如有疑问，请联系平台客服！");
			}
			else{
				UserModel userModel = new UserModel(user);
				//读取空间信息
				List<UserTenantModel> userTenantModelList = commonMapper.selectList("UserTenant.selectListByUserId", userModel.getId());
				if(!CollectionUtils.isEmpty(userTenantModelList)) {
					userTenantModelList.forEach((tenant) -> {
						//读取空间角色信息
						tenant.setRoleIdList(commonMapper.selectList("UserTenantRole.selectRoleIdListByUserIdAndTenantId",tenant));
						//读取用户空间角色下的权限信息
						UserAuthorityIdsModel userAuthorityIdsModel = roleAuthorityService.selectAllAuthorityHashIdsByUserInfo(tenant.getUserId(), tenant.getTenantId());
						tenant.setAuthorityIds(userAuthorityIdsModel.getAuthorityIds());
						tenant.setCategoryAuthorityIds(userAuthorityIdsModel.getCategoryAuthorityIds());
					});
					userModel.setUserTenantRoleList(userTenantModelList);
				}
				
				//记录登录日志
				userLoginRecordService.record(user.getId(),ip);
				return userModel;
			}
		}
	}
	
	@Override
	public UserModel login(WxUserInfo wxUserInfo,RequestClient requestClient,String ip) {
		List<User> userList = commonMapper.selectList("User.wxLogin", wxUserInfo.getUnionId());
		if(CollectionUtils.isEmpty(userList)){//微信用户尚未注册
			//生成用户信息
			User user = new User();
			user.setHead(wxUserInfo.getAvatarUrl());
			user.setNickName(wxUserInfo.getNickName());
			user.setUnionId(wxUserInfo.getUnionId());
			
			WxOpenIdExtra openIdExtra = new WxOpenIdExtra();
			switch(requestClient){
				case WxMiniProgram:{
					openIdExtra.setMiniOpenId(wxUserInfo.getOpenId());
					break;
				}
				default:{
					break;
				}
			}
			user.setOpenIdExtra(JsonUtil.entity2Json(openIdExtra));
			user.setRegisteTime(new Date());
			commonMapper.insert(User.class, user);
			
			//记录登录日志
			userLoginRecordService.record(user.getId(),ip);
			return new UserModel(user);
		}
		else{//已存在微信用户
			if(userList.size()>1) {
				logger.error("存在两个相同的账号："+wxUserInfo.getUnionId());
				throw new BusinessException(ErrorConstant.ACCOUNT_EXCEPTION,"账号存在异常，请联系平台客服！");
			}
			
			User user = userList.get(0);
			if(user.getStatus().intValue()==-1){
				throw new BusinessException(ErrorConstant.ACCOUNT_FORBIDDEN,"账号被禁用，如有疑问，请联系平台客服！");
			}
			else{
				//记录登录日志
				userLoginRecordService.record(user.getId(),ip);
				return new UserModel(user);
			}
		}
	}
	
	public void switchTenant(Integer userTenantId) {
		commonMapper.update("UserTenant.switchTenant",userTenantId);
	}
	
	public List<User> selectTenantUserList(Integer tenantId){
		String sql = "SELECT u.* FROM User u,UserTenant ut WHERE u.id = ut.userId AND ut.tenantId = #{tenantId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("tenantId", tenantId);
		return commonMapper.selectListBySql(sql, params, User.class);
	}
	
	@Override
	@Transactional
	public void insertTenantUser(User user,Integer roleId,Integer tenantId,String operator){
		user.setRegisteTime(new Date());
		user.setPwd(MD5Util.md5(user.getPwd()).toUpperCase());
		commonMapper.insert(User.class, user);
		
		UserTenant userTenant = new UserTenant();
		userTenant.setUserId(user.getId());
		userTenant.setTenantId(tenantId);
		userTenant.setLastUpdater(operator);
		userTenant.setLastUpdateTime(new Date());
		commonMapper.insert(UserTenant.class, userTenant);
		
		UserTenantRole userTenantRole = new UserTenantRole();
		userTenantRole.setUserId(user.getId());
		userTenantRole.setLastUpdater(operator);
		userTenantRole.setLastUpdateTime(new Date());
		userTenantRole.setTenantId(tenantId);
		userTenantRole.setRoleId(roleId);
		commonMapper.insert(UserTenantRole.class, userTenantRole);
	}
	
}
