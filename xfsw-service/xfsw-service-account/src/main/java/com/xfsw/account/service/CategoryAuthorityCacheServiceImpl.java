package com.xfsw.account.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.consts.RedisCacheDefineConstants;
import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.entity.LinkAuthority;
import com.xfsw.common.classes.BusinessException;
import com.xfsw.common.consts.ErrorConstant;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ArrayUtil;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.ListUtil;

@Service("categoryAuthorityCacheService")
public class CategoryAuthorityCacheServiceImpl implements CategoryAuthorityCacheService {

	private static Logger logger = LoggerFactory.getLogger(CategoryAuthorityCacheServiceImpl.class);
	
	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="authRedisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource(name="roleCategoryAuthorityService")
	RoleCategoryAuthorityService roleCategoryAuthorityService;
	
	@Resource(name="linkAuthorityService")
	LinkAuthorityService linkAuthorityService;
	
	@Resource(name="authorityCacheService")
	AuthorityCacheService authorityCacheService;
	
	@Override
	public List<CategoryAuthority> selectListByIds(Integer[] categoryAuthorityIds){
		if(ArrayUtil.isEmpty(categoryAuthorityIds)) 
			return null;
		
		Map<String,CategoryAuthority> categoryAuthorityMap = loadCategoryAuthorityCache(false, true);
		List<CategoryAuthority> categoryAuthorityList = new ArrayList<CategoryAuthority>();
		for(Integer id : categoryAuthorityIds){
			if(categoryAuthorityMap.containsKey(id.toString())){
				categoryAuthorityList.add(categoryAuthorityMap.get(id.toString()));
			}
		}
		return categoryAuthorityList;
	}
	
	@Override
	public List<CategoryAuthority> selectListByTenantId(Integer tenantId) {
		if(tenantId==null)
			return null;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("tenantId", tenantId);
		return commonMapper.selectList(CategoryAuthority.class, params);
	}
	
	@Override
	public CategoryAuthority getById(Integer id) {
		return commonMapper.get(CategoryAuthority.class, id);
	}
	
	@Override
	public void insertCategoryAuthority(CategoryAuthority categoryAuthority) {
		commonMapper.insert(CategoryAuthority.class, categoryAuthority);
		this.loadCategoryAuthorityCache();
		authorityCacheService.reloadCategoryAuthorityCache(categoryAuthority.getHashId());
	}
	
	@Override
	public void updateCategoryAuthority(CategoryAuthority categoryAuthority){
		commonMapper.insert("CategoryAuthority.updateCategoryAuthority", categoryAuthority);
		this.loadCategoryAuthorityCache();
		if(categoryAuthority.getHashId()!=null)
			authorityCacheService.reloadCategoryAuthorityCache(categoryAuthority.getHashId());
	}
	
	@Override
	@Transactional
	public void initAuthority(List<CategoryAuthority> parentCategoryAuthorityList,List<CategoryAuthority> categoryAuthorityList,List<LinkAuthority> linkAuthorityList){
		Map<Integer,Integer> categoryAuthorityIdMap = new HashMap<Integer,Integer>();
		for(CategoryAuthority categoryAuthority:parentCategoryAuthorityList){
			Integer oldId = categoryAuthority.getId();
			commonMapper.insert(CategoryAuthority.class, categoryAuthority);
			categoryAuthorityIdMap.put(oldId, categoryAuthority.getId());
		}
		
		for(CategoryAuthority categoryAuthority:categoryAuthorityList){
			Integer oldId = categoryAuthority.getId();
			categoryAuthority.setPid(categoryAuthorityIdMap.get(oldId));
			commonMapper.insert(CategoryAuthority.class, categoryAuthority);
			categoryAuthorityIdMap.put(oldId, categoryAuthority.getId());
		}
	}
	
//	@Transactional
//	public void deleteAuthority(Integer id,String operator){
//		//删除相关功能权限数据
//		linkAuthorityService.deleteByCategoryAuthorityId(id, operator);
//		//删除菜单权限数据
//		commonMapper.deleteAndBak(CategoryAuthority.class, id, operator);
//		//删除角色功能权限关系roleAuthority
//		roleCategoryAuthorityService.delete(id, operator);
//		//TODO 将权限服务与缓存服务解耦,此处代码删除,放到controller中
//		//刷新系统总权限缓存
////		authorityCacheService.refresh();
////		//刷新所有用户的角色权限缓存信息
////		userSessionService.refreshUserSessionAuthorityInfo();
//	}
//	
//	public void updateCategoryAuthority(CategoryAuthority authority){
//		commonMapper.insert("CategoryAuthority.updateCategoryAuthority", authority);
//	}
//	
//	public List<AuthorityModel> selectFirstAuthorityModelList(){
//		return commonMapper.selectList("CategoryAuthority.selectFirstAuthorityModelList");
//	}
	
	/**
	 * 重新加载菜单权限数据到缓存中
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	private void loadCategoryAuthorityCache(){
		List<CategoryAuthority> authorityList = commonMapper.selectList("CategoryAuthority.selectAll");
		Map<String,CategoryAuthority> resultMap = new HashMap<String,CategoryAuthority>();
		if(!ListUtil.isEmpty(authorityList)){
			for(int i=0;i<authorityList.size();i++){
				CategoryAuthority authority = (CategoryAuthority) authorityList.get(i);
				resultMap.put(authority.getId().toString(), authority);
			}
		}
		redisTemplate.opsForValue().set(RedisCacheDefineConstants.XFSW_ALL_CATEGORY_AUTHORITY, JsonUtil.entity2Json(resultMap), RedisCacheDefineConstants.XFSW_ALL_CATEGORY_AUTHORITY_CACHE_EXPIRED_TIME, TimeUnit.MILLISECONDS);
		logger.debug("Category authority has loaded!");
	}
	
	/**
	 * 加载所有菜单权限进入缓存
	 * @param forced			是否强制加载
	 * @param returnData		是否需要返回缓存数据，false则返回null
	 * @return				map,key为权限ID
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	private Map<String,CategoryAuthority> loadCategoryAuthorityCache(boolean forced,boolean returnData) throws BusinessException {
		boolean isReload = false;
		if(forced) {
			isReload = true;
		}
		else {
			RedisCallback<Boolean> callback = (RedisConnection connection) -> {
				return connection.exists(RedisCacheDefineConstants.XFSW_ALL_CATEGORY_AUTHORITY.getBytes());
			};
			boolean isExsit = redisTemplate.execute(callback);
			if(!isExsit){
				isReload = true;
			}
		}
		
		Map<String,CategoryAuthority> resultMap = null;
		if(isReload) {
			//获取所有菜单权限缓
			List<CategoryAuthority> authorityList = commonMapper.selectList("CategoryAuthority.selectAll");
			resultMap = new HashMap<String,CategoryAuthority>();
			if(!ListUtil.isEmpty(authorityList)){
				for(int i=0;i<authorityList.size();i++){
					CategoryAuthority authority = (CategoryAuthority) authorityList.get(i);
					resultMap.put(authority.getId().toString(), authority);
				}
			}
			redisTemplate.opsForValue().set(RedisCacheDefineConstants.XFSW_ALL_CATEGORY_AUTHORITY, JsonUtil.entity2Json(resultMap), RedisCacheDefineConstants.XFSW_ALL_CATEGORY_AUTHORITY_CACHE_EXPIRED_TIME, TimeUnit.MILLISECONDS);
			logger.debug("Category authority has loaded!");
		}
		else {
			if(returnData) {
				String info = redisTemplate.opsForValue().get(RedisCacheDefineConstants.XFSW_ALL_CATEGORY_AUTHORITY);
				resultMap = new HashMap<String,CategoryAuthority>();
				try {
					Map<String,Object> dataMap = JsonUtil.json2Map(info);
					for(Entry<String,Object> entry:dataMap.entrySet()) {
						CategoryAuthority categoryAuthority = new CategoryAuthority();
						BeanUtils.copyProperties(categoryAuthority, entry.getValue());
						resultMap.put(entry.getKey().toString(), categoryAuthority);
					}
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new BusinessException(ErrorConstant.ERROR_SYSTEM_KNOWN,"copy the cache data of category authority into entity error!",e);
				}
			}
		}
		return resultMap;
	}

	
}
