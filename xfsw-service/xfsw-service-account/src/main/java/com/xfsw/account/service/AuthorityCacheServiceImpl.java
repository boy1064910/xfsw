package com.xfsw.account.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xfsw.account.consts.RedisCacheDefineConstants;
import com.xfsw.account.model.AuthorityModel;
import com.xfsw.common.classes.BusinessException;
import com.xfsw.common.consts.ErrorConstant;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.ListUtil;

@Service("authorityCacheService")
public class AuthorityCacheServiceImpl implements AuthorityCacheService {

	private static Logger logger = LoggerFactory.getLogger(AuthorityCacheServiceImpl.class);
	
	@Resource(name="authRedisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	public AuthorityModel getAuthorityModelById(Integer id){
		if(id ==null)
			return null;
		
		Map<String,AuthorityModel> authorityModelMap = loadAuthorityCache(false, true);
		if(authorityModelMap.containsKey(id.toString())) {
			return authorityModelMap.get(id.toString());
		}
		return null;
	}
	
	
	/**
	 * 加载权限进入缓存（菜单权限不包括一级菜单）
	 * @param forced			是否强制加载
	 * @param returnData		是否需要返回缓存数据，false则返回null
	 * @return				map,key为权限Hash ID
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	private Map<String,AuthorityModel> loadAuthorityCache(boolean forced,boolean returnData) throws BusinessException {
		boolean isReload = false;
		if(forced) {
			isReload = true;
		}
		else {
			RedisCallback<Boolean> callback = (RedisConnection connection) -> {
				return connection.exists(RedisCacheDefineConstants.XFSW_ALL_AUTHORITY.getBytes());
			};
			boolean isExsit = redisTemplate.execute(callback);
			if(!isExsit){
				isReload = true;
			}
		}
		
		Map<String,AuthorityModel> resultMap = null;
		if(isReload) {
			//获取所有的权限（菜单权限不包括一级菜单）
			List<AuthorityModel> authorityModelList = commonMapper.selectList("Authority.selectAllAuthorityModel");
			Map<String,AuthorityModel> authorityTreeMap = new HashMap<String,AuthorityModel>();
			if(!ListUtil.isEmpty(authorityModelList)){
				for(int i=0;i<authorityModelList.size();i++){
					AuthorityModel authorityModel = (AuthorityModel) authorityModelList.get(i);
					authorityTreeMap.put(authorityModel.getId().toString(), authorityModel);
				}
			}
			redisTemplate.opsForValue().set(RedisCacheDefineConstants.XFSW_ALL_AUTHORITY, JsonUtil.entity2Json(authorityTreeMap), RedisCacheDefineConstants.XFSW_ALL_CATEGORY_AUTHORITY_CACHE_EXPIRED_TIME, TimeUnit.MILLISECONDS);
			logger.debug("All authority has loaded!");
		}
		else {
			if(returnData) {
				String info = redisTemplate.opsForValue().get(RedisCacheDefineConstants.XFSW_ALL_AUTHORITY);
				resultMap = new HashMap<String,AuthorityModel>();
				try {
					Map<String,Object> dataMap = JsonUtil.json2Map(info);
					for(Entry<String,Object> entry:dataMap.entrySet()) {
						AuthorityModel authorityModel = new AuthorityModel();
						BeanUtils.copyProperties(authorityModel, entry.getValue());
						resultMap.put(entry.getKey().toString(), authorityModel);
					}
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new BusinessException(ErrorConstant.ERROR_SYSTEM_KNOWN,"copy the cache data of all authority into entity error!",e);
				}
			}
		}
		return resultMap;
	}
	
}
