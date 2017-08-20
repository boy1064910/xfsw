/**
 * 
 */
package com.xfsw.session.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xfsw.common.classes.BusinessException;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.StringUtil;
import com.xfsw.session.consts.SessionConstant;
import com.xfsw.session.model.UserSessionModel;

@Service("userSessionService")
public class UserSessionServiceImpl implements UserSessionService {

//	private static Logger logger = LoggerFactory.getLogger(UserSessionServiceImpl.class);
	
	@Resource(name="sessionRedisTemplate")
	RedisTemplate<String, String> redisTemplate;
	
//	@Resource(name="roleAuthorityService")
//	RoleAuthorityService roleAuthorityService;
	
	public void addUserSession(String sessionIdValue,UserSessionModel userSessionModel){
		redisTemplate.opsForValue().set(SessionConstant.XFSW_SESSION_REDIS_PREFIX + sessionIdValue, JsonUtil.entity2Json(userSessionModel),SessionConstant.XFSW_SESSION_EXPIRE, TimeUnit.MILLISECONDS);
	}
	
	public UserSessionModel getUserSession(String sessionIdValue) throws BusinessException{
		String userSessionInfo = redisTemplate.opsForValue().get(SessionConstant.XFSW_SESSION_REDIS_PREFIX + sessionIdValue);
		if(!StringUtil.isEmpty(userSessionInfo)){
			//刷新session过期时间
			redisTemplate.expire(SessionConstant.XFSW_SESSION_REDIS_PREFIX + sessionIdValue, SessionConstant.XFSW_SESSION_EXPIRE, TimeUnit.MILLISECONDS);
			return (UserSessionModel) JsonUtil.json2Entity(userSessionInfo, UserSessionModel.class);
		}
		else{
			return null;
		}
	}
	
//	public UserSessionModel getUserSessionByKey(String key) throws BusinessException{
//		String userSessionInfo = redisTemplate.opsForValue().get(key);
//		if(!StringUtil.isEmpty(userSessionInfo)){
//			//刷新session过期时间
//			redisTemplate.expire(key, CommonConstant.XFSW_PLATFORM_SESSION_EXPIRE, TimeUnit.MILLISECONDS);
//			return (UserSessionModel) JsonUtil.json2Entity(userSessionInfo, UserSessionModel.class);
//		}
//		else{
//			return null;
//		}
//	}
	
//	public void addUserSessionByKey(String key,UserSessionModel user){
//		redisTemplate.opsForValue().set(key, JsonUtil.entity2Json(user),CommonConstant.XFSW_PLATFORM_SESSION_EXPIRE, TimeUnit.MILLISECONDS);
//	}
//
//	public void deleteUserSession(String sessionIdValue){
//		redisTemplate.delete(CommonConstant.XFSW_PLATFORM_USER_SESSION_REDIS_PREFIX + sessionIdValue);
//	}
	
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
//	private Map<String,User> listUserSession(){
//		Set<String> keysSets = redisTemplate.keys(CommonConstant.XFSW_PLATFORM_USER_SESSION_REDIS_PREFIX+"*");
//		if(keysSets!=null&&keysSets.size()>0){
//			Map<String,User> userSessionMap = new HashMap<String,User>();
//			for(String key:keysSets){
//				userSessionMap.put(key, this.getUserSessionByKey(key));
//			}
//			return userSessionMap;
//		}
//		else{
//			return null;
//		}
//	}
	
}
