package com.xfsw.account.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.model.AuthorityModel;
import com.xfsw.common.consts.CommonConstant;
import com.xfsw.common.util.ArrayUtil;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.ListUtil;

@Service("authorityCacheService")
public class AuthorityCacheServiceImpl implements AuthorityCacheService {

//	private static Logger logger = LoggerFactory.getLogger(SystemAuthorityServiceImpl.class);
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource
	CategoryAuthorityService categoryAuthorityService;
	
	@Resource
	AuthorityService authorityService;
	
	@PostConstruct
	private void init(){
		//获取所有菜单权限缓
		List<CategoryAuthority> authorityList = categoryAuthorityService.selectAll();
		Map<String,String> map = new LinkedHashMap<String,String>();
		if(!ListUtil.isEmpty(authorityList)){
			for(int i=0;i<authorityList.size();i++){
				CategoryAuthority authority = (CategoryAuthority) authorityList.get(i);
				map.put(authority.getId().toString(), JsonUtil.entity2Json(authority));
			}
		}
		redisTemplate.opsForValue().set(CommonConstant.XFSW_ALL_CATEGORY_AUTHORITY, JsonUtil.entity2Json(map), CommonConstant.XFSW_ALL_CATEGORY_AUTHORITY_CACHE_EXPIRED_TIME, TimeUnit.MILLISECONDS);
		
		//获取所有的权限（菜单不包括一级菜单和功能权限）
		List<AuthorityModel> authorityModelList = authorityService.selectAll();
		Map<String,String> authorityTreeMap = new LinkedHashMap<String,String>();
		if(!ListUtil.isEmpty(authorityModelList)){
			for(int i=0;i<authorityModelList.size();i++){
				AuthorityModel authorityModel = (AuthorityModel) authorityModelList.get(i);
				authorityTreeMap.put(authorityModel.getId().toString(), JsonUtil.entity2Json(authorityModel));
			}
		}
		redisTemplate.opsForValue().set(CommonConstant.XFSW_ALL_AUTHORITY, JsonUtil.entity2Json(authorityTreeMap), CommonConstant.XFSW_ALL_CATEGORY_AUTHORITY_CACHE_EXPIRED_TIME, TimeUnit.MILLISECONDS);
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoryAuthority> getCategoryAuthorityList(Integer[] categoryAuthorityIds){
		if(ArrayUtil.isEmpty(categoryAuthorityIds)) return null;
		
		this.checkAndReload();
		
		String info = redisTemplate.opsForValue().get(CommonConstant.XFSW_ALL_CATEGORY_AUTHORITY);
		Map<String,String> map = (Map<String, String>) JsonUtil.json2Map(info);
		List<CategoryAuthority> categoryAuthorityList = new ArrayList<CategoryAuthority>();
		for(Integer id : categoryAuthorityIds){
			if(map.containsKey(id.toString())){
				categoryAuthorityList.add((CategoryAuthority) JsonUtil.json2Entity(map.get(id.toString()), CategoryAuthority.class));
			}
		}
		return categoryAuthorityList;
	}
	
	@SuppressWarnings("unchecked")
	public AuthorityModel getAuthorityModelById(Integer id){
		if(id==null) return null;
		this.checkAndReload();
		String info = redisTemplate.opsForValue().get(CommonConstant.XFSW_ALL_AUTHORITY);
		Map<String,String> map = (Map<String, String>) JsonUtil.json2Map(info);
		if(map.containsKey(id.toString())){
			AuthorityModel am = (AuthorityModel) JsonUtil.json2Entity(map.get(id.toString()),AuthorityModel.class);
			return (AuthorityModel) am;
		}
		else{
			return null;
		}
	}
	
	public void refresh(){
		this.init();
	}
	
	/**
	 * 检查缓存是否存在，若不存在重新加载
	 */
	private void checkAndReload(){
		RedisCallback<Boolean> callback = (RedisConnection connection) -> {
			return connection.exists(CommonConstant.XFSW_ALL_CATEGORY_AUTHORITY.getBytes());
		};
		boolean isExsit = redisTemplate.execute(callback);
		if(!isExsit){
			this.init();
		}
	}
}
