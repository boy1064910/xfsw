package com.xfsw.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xfsw.account.entity.User;
import com.xfsw.account.model.UserAuthorityIdsModel;
import com.xfsw.account.model.UserModel;
import com.xfsw.account.model.UserTenantModel;
import com.xfsw.common.classes.BusinessException;
import com.xfsw.common.consts.ErrorConstant;
import com.xfsw.common.mapper.ICommonMapper;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="roleAuthorityService")
	RoleAuthorityService roleAuthorityService;
	
	@Resource(name="userLoginRecordService")
	UserLoginRecordService userLoginRecordService;
		
	@Override
	public UserModel login(String account, String pwd,String ip) throws BusinessException {
		User queryUser = new User();
		queryUser.setAccount(account);
		queryUser.setPwd(pwd);
		return this.login(queryUser, ip);
	}
	
	public void switchTenant(Integer userTenantId) {
		commonMapper.update("UserTenant.switchTenant",userTenantId);
	}
	
	public List<User> selectTenantUserList(Integer tenantId){
		String sql = "SELECT u.* FROM User u,UserTenant ut WHERE u.id = ut.userId AND ut.tenantId = #{tenantId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("tenantId", tenantId);
		return commonMapper.selectListBySql(sql, User.class);
	}
	
//	public UserModel wxLogin(String unionId,String ip) {
//		User queryUser = new User();
//		queryUser.setUnionId(unionId);
//		return this.login(queryUser, ip);
//	}
//	
//	
//	@Transactional
//	public User wxBindAccount(String account,String unionId,String openId,String nickName,String head,String ip) throws BusinessException{
//		if(commonMapper.check("User.checkByAccount",account)){
//			throw new BusinessException("该手机号已被使用，如需解绑，请联系客服！");
//		}
//		User user = new User();
//		user.setAccount(account);
//		user.setPwd(MD5Util.md5(RandomUtil.getCharAndNumr(8)).toUpperCase());
//		user.setNickName(nickName);
//		user.setHead(head);
//		user.setUnionId(unionId);
//		user.setMiniOpenId(openId);
//		commonMapper.insert("User.wxBindAccount", user);
//		//记录登录日志
//		userLoginRecordService.record(user.getId(),ip);
//		return user;
//	}
//	
//	public boolean checkByRoleId(Integer roleId){
//		return commonMapper.check("User.checkByRoleId", roleId);
//	}
//	
////	@SuppressWarnings("unchecked")
////	public List<UserInfoModel> selectUserInfoListByIds(Integer[] userIds){
////		return (List<UserInfoModel>) commonMapper.selectList("User.selectUserInfoListByIds",userIds);
////	}
//	
//	public User getById(Integer id){
//		return (User) commonMapper.get(User.class,id);
//	}
//	
//	public User getByAccount(String account){
//		return (User) commonMapper.get("User.getByAccount",account);
//	}
//	
//	public void distributeRoleId(Integer userId,Integer roleId){
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("roleId", roleId);
//		params.put("id", userId);
//		commonMapper.update("User.distributeRoleId",params);
//	}
//	
//	public void updateRoleIdByOldRoleId(Integer oldRoleId,Integer newRoleId){
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("oldRoleId", oldRoleId);
//		params.put("newRoleId", newRoleId);
//		commonMapper.update("User.updateRoleIdByOldRoleId",params);
//	}
//	
//	public List<User> selectUserListByUserIds(Integer[] userIds) {
//		if(ArrayUtil.isEmpty(userIds)) return null;
//		return commonMapper.selectList("User.selectUserListByUserIds",userIds);
//	}
//
//	//================================前端接口================================
//	@Override
//	public List<User> selectListByUserIds(Integer[] userIds) {
//		if(ArrayUtil.isEmpty(userIds)) return null;
//		return commonMapper.selectList("User.selectListByUserIds",userIds);
//	}
//	
//	public String getHeadByUserId(Integer id){
//		return (String) commonMapper.get("User.getHeadByUserId",id);
//	}
	
	
	
	/**
	 * 登录处理逻辑
	 * @param queryUser
	 * @param ip
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	private UserModel login(User queryUser,String ip) {
		List<User> userList = commonMapper.selectList("User.login", queryUser);
		if(CollectionUtils.isEmpty(userList)){
			throw new BusinessException(ErrorConstant.ACCOUNT_PWD_ERROR,"账号密码错误，请重试！");
		}
		else{
			if(userList.size()>1) {
				//TODO 记录错误日志
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
				if(CollectionUtils.isEmpty(userTenantModelList)) {
					throw new BusinessException(ErrorConstant.ACCOUNT_NOT_BIND_TENANT,"账号尚未开通使用空间，请联系平台客服！");
				}
				userTenantModelList.forEach((tenant) -> {
					//读取空间角色信息
					tenant.setRoleIdList(commonMapper.selectList("UserTenantRole.selectRoleIdListByUserIdAndTenantId",tenant));
					//读取用户空间角色下的权限信息
					UserAuthorityIdsModel userAuthorityIdsModel = roleAuthorityService.selectAllAuthorityHashIdsByRoleId(tenant.getUserId(), tenant.getTenantId());
					tenant.setAuthorityIds(userAuthorityIdsModel.getAuthorityIds());
					tenant.setCategoryAuthorityIds(userAuthorityIdsModel.getCategoryAuthorityIds());
				});
				userModel.setUserTenantRoleList(userTenantModelList);
				
				//记录登录日志
				userLoginRecordService.record(user.getId(),ip);
				return userModel;
			}
		}
	}
}
