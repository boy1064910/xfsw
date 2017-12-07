/**
 * 
 */
package com.xfsw.session.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xfsw.account.model.UserAuthorityIdsModel;
import com.xfsw.account.model.UserPublicInfo;
import com.xfsw.common.classes.BusinessException;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.StringUtil;
import com.xfsw.session.consts.SessionConstant;
import com.xfsw.session.model.UserSessionModel;

@Service("userSessionService")
public class UserSessionServiceImpl implements UserSessionService {

	private static Logger logger = LoggerFactory.getLogger(UserSessionServiceImpl.class);
	
	@Resource(name="sessionRedisTemplate")
	RedisTemplate<String, String> sessionRedisTemplate;
	
	@Override
	public void addUserSession(String sessionIdValue,UserSessionModel userSessionModel){
		logger.info("创建用户缓存信息："+sessionIdValue);
		sessionRedisTemplate.opsForValue().set(SessionConstant.XFSW_SESSION_REDIS_PREFIX + sessionIdValue, JsonUtil.entity2Json(userSessionModel),SessionConstant.XFSW_SESSION_EXPIRE, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public UserSessionModel getUserSession(String sessionIdValue) throws BusinessException{
		String key = SessionConstant.XFSW_SESSION_REDIS_PREFIX + sessionIdValue;
		return this.getUserSessionByKey(key);
	}
	
	public void deleteUserSession(String sessionIdValue){
		sessionRedisTemplate.delete(SessionConstant.XFSW_SESSION_REDIS_PREFIX + sessionIdValue);
	}
	
	@Override
	public void refreshUserSessionAuthorityInfo(List<UserAuthorityIdsModel> userAuthorityIdsModelList){
		//后期用户数量多,改成消息机制
		if(CollectionUtils.isEmpty(userAuthorityIdsModelList))
			return;
		
		Map<String,UserSessionModel> allUserSessionModel = this.listUserSession();
		Map<String,List<InnerUserSessionModel>> innerUserSessionModelMap = new HashMap<String,List<InnerUserSessionModel>>();
		for(Entry<String,UserSessionModel> entry:allUserSessionModel.entrySet()){
			String key = entry.getValue().getId()+"-"+entry.getValue().getTenantId();
			if(!innerUserSessionModelMap.containsKey(key)){
				innerUserSessionModelMap.put(key, new ArrayList<InnerUserSessionModel>());
			}
			innerUserSessionModelMap.get(key).add(new InnerUserSessionModel(entry.getKey(), entry.getValue()));
		}
		
		for(UserAuthorityIdsModel userAuthorityIdsModel:userAuthorityIdsModelList){
			List<InnerUserSessionModel> innerUserSessionModelList = innerUserSessionModelMap.get(userAuthorityIdsModel.getUserId()+"-"+userAuthorityIdsModel.getTenantId());
			if(!CollectionUtils.isEmpty(innerUserSessionModelList)){
				for(InnerUserSessionModel innerUserSessionModel:innerUserSessionModelList){
					String sessionKey = innerUserSessionModel.getSessionKey();
					UserSessionModel userSessionModel = innerUserSessionModel.getUserSessionModel();
					userSessionModel.setAuthorityIds(userAuthorityIdsModel.getAuthorityIds());
					userSessionModel.setCategoryAuthorityIds(userAuthorityIdsModel.getCategoryAuthorityIds());
					sessionRedisTemplate.opsForValue().set(sessionKey, JsonUtil.entity2Json(userSessionModel),SessionConstant.XFSW_SESSION_EXPIRE, TimeUnit.MILLISECONDS);
				}
			}
		}
	}
	
	@Override
	public UserPublicInfo getUserPublicInfo(String sessionId) {
		String key = SessionConstant.XFSW_SESSION_REDIS_PREFIX + sessionId;
		UserSessionModel u = this.getUserSessionByKey(key);
		UserPublicInfo userPublicInfo = new UserPublicInfo() {
			{
				setId(u.getId());
				setAccount(u.getAccount());
				setEmail(u.getEmail());
				setHead(u.getHead());
				setNickName(u.getNickName());
				setRegisteTime(u.getRegisteTime());
				setStatus(u.getStatus());
			}
		};
		return userPublicInfo;
	}
	
//	public void refreshUserSessionAuthorityInfo(){
//		//遍历循环redis中的所有系统用户，判断roleId，若一致则直接修改缓存中的用户信息
//		Map<String,User> userSessionMap = this.listUserSession();
//		if(userSessionMap!=null&&userSessionMap.size()>0){
//			//角色权限缓存map
//			Map<Integer,UserAuthorityIdsModel> userAuthorityIdsModelMap = new HashMap<Integer,UserAuthorityIdsModel>();//用户权限ID集合的缓存map
//			for(Map.Entry<String, User> entry:userSessionMap.entrySet()){
//				User user = entry.getValue();
//				if(user.getRoleId()!=null){
//					UserAuthorityIdsModel userAuthorityIdsModel = null;
//					if(userAuthorityIdsModelMap.containsKey(user.getRoleId())){
//						userAuthorityIdsModel = userAuthorityIdsModelMap.get(user.getRoleId());
//					}
//					else{
//						//通过角色ID重新获取权限集合，并且加入到缓存当中
//						userAuthorityIdsModel = roleAuthorityService.selectAllAuthorityHashIdsByRoleId(user.getRoleId());
//						userAuthorityIdsModelMap.put(user.getRoleId(), userAuthorityIdsModel);
//					}
//					user.setCategoryAuthorityIds(userAuthorityIdsModel.getCategoryAuthorityIds());
//					user.setAuthorityIds(userAuthorityIdsModel.getAuthorityIds());
//				}
//				this.addUserSessionByKey(entry.getKey(), user);
//			}
//		}
//	}
//	
//	public void refreshSystemUserSessionAuthorityInfoByRoleId(Integer roleId){
//		UserAuthorityIdsModel userAuthorityIdsModel = roleAuthorityService.selectAllAuthorityHashIdsByRoleId(roleId);
//		
//		//遍历循环redis中的所有系统用户，判断roleId，若一致则直接修改缓存中的用户信息
//		Map<String,User> userSesionMap = this.listUserSession();
//		if(userSesionMap!=null&&userSesionMap.size()>0){
//			for(Map.Entry<String, User> entry:userSesionMap.entrySet()){
//				User user = entry.getValue();
//				if(user.getRoleId()!=null){
//					if(user.getRoleId().intValue()==roleId.intValue()){
//						user.setCategoryAuthorityIds(userAuthorityIdsModel.getCategoryAuthorityIds());
//						user.setAuthorityIds(userAuthorityIdsModel.getAuthorityIds());
//						this.addUserSessionByKey(entry.getKey(), user);
//					}
//				}
//			}
//		}
//	}
//	
	private Map<String,UserSessionModel> listUserSession(){
		Set<String> keysSets = sessionRedisTemplate.keys(SessionConstant.XFSW_SESSION_REDIS_PREFIX+"*");
		if(keysSets!=null&&keysSets.size()>0){
			Map<String,UserSessionModel> userSessionMap = new HashMap<String,UserSessionModel>();
			for(String key:keysSets){
				userSessionMap.put(key, this.getUserSessionByKey(key));
			}
			return userSessionMap;
		}
		else{
			return null;
		}
	}
	
	private UserSessionModel getUserSessionByKey(String key) {
		String userSessionInfo = sessionRedisTemplate.opsForValue().get(key);
		if(!StringUtil.isEmpty(userSessionInfo)){
			//刷新session过期时间
			sessionRedisTemplate.expire(key, SessionConstant.XFSW_SESSION_EXPIRE, TimeUnit.MILLISECONDS);
			return JsonUtil.json2Entity(userSessionInfo, UserSessionModel.class);
		}
		else{
			return null;
		}
	}
	
}

class InnerUserSessionModel{
	String sessionKey;
	UserSessionModel userSessionModel;
	
	public InnerUserSessionModel(String sessionKey, UserSessionModel userSessionModel) {
		super();
		this.sessionKey = sessionKey;
		this.userSessionModel = userSessionModel;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public UserSessionModel getUserSessionModel() {
		return userSessionModel;
	}
	public void setUserSessionModel(UserSessionModel userSessionModel) {
		this.userSessionModel = userSessionModel;
	}
}
