package com.xfsw.account.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.entity.LinkAuthority;
import com.xfsw.account.model.AuthorityModel;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ArrayUtil;

@Service("categoryAuthorityService")
public class CategoryAuthorityServiceImpl implements CategoryAuthorityService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="authRedisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource(name="linkAuthorityService")
	LinkAuthorityService linkAuthorityService;
	
	@Resource(name="authorityCacheService")
	AuthorityCacheService authorityCacheService;
	
	@Resource(name="roleCategoryAuthorityService")
	RoleCategoryAuthorityService roleCategoryAuthorityService;
	
	@Override
	public List<CategoryAuthority> selectListByIds(Integer[] categoryAuthorityIds){
		if(ArrayUtil.isEmpty(categoryAuthorityIds)) 
			return null;
		
		Map<String,CategoryAuthority> categoryAuthorityMap = authorityCacheService.getCategorytAuthorityMap();
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
		authorityCacheService.reloadCategoryAuthorityIntoAuthorityCache();
	}
	
	@Override
	public void updateCategoryAuthority(CategoryAuthority categoryAuthority){
		commonMapper.insert("CategoryAuthority.updateCategoryAuthority", categoryAuthority);
		if(categoryAuthority.getHashId()!=null)
			authorityCacheService.reloadCategoryAuthorityIntoAuthorityCache();
	}
	
	@Override
	@Transactional
	public void initAuthority(List<CategoryAuthority> parentCategoryAuthorityList,List<CategoryAuthority> categoryAuthorityList,List<LinkAuthority> linkAuthorityList,Integer roleId,String operator){
		Map<Integer,Integer> categoryAuthorityIdMap = new HashMap<Integer,Integer>();
		
		List<Integer> categoryAuthorityIdList = new ArrayList<Integer>();
		for(CategoryAuthority categoryAuthority:parentCategoryAuthorityList){
			Integer id = commonMapper.get("CategoryAuthority.getIdByDefaultAuthorityIdAndTenantId",categoryAuthority);
			Integer oldId = categoryAuthority.getId();
			if(id==null){
				categoryAuthority.setId(null);
				commonMapper.insert(CategoryAuthority.class, categoryAuthority);
				id = categoryAuthority.getId();
			}
			categoryAuthorityIdMap.put(oldId, id);
			categoryAuthorityIdList.add(id);
		}
		
		for(CategoryAuthority categoryAuthority:categoryAuthorityList){
			Integer id = commonMapper.get("CategoryAuthority.getIdByDefaultAuthorityIdAndTenantId",categoryAuthority);
			Integer oldId = categoryAuthority.getId();
			if(id==null){
				categoryAuthority.setId(null);
				categoryAuthority.setPid(categoryAuthorityIdMap.get(categoryAuthority.getPid()));
				commonMapper.insert(CategoryAuthority.class, categoryAuthority);
				id = categoryAuthority.getId();
			}
			categoryAuthorityIdMap.put(oldId, id);
			categoryAuthorityIdList.add(id);
		}
		
		List<Integer> linkAuthorityIdList = linkAuthorityService.initLinkAuthority(linkAuthorityList, categoryAuthorityIdMap);
		
		roleCategoryAuthorityService.configAuthority(categoryAuthorityIdList, linkAuthorityIdList, roleId, operator);
	}

	public List<AuthorityModel> selectFirstAuthorityModelList(Integer tenantId){
		return commonMapper.selectList("CategoryAuthority.selectFirstAuthorityModelList",tenantId);
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
	
}
