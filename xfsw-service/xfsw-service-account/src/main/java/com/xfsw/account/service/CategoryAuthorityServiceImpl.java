package com.xfsw.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.CategoryAuthority;
import com.xfsw.account.model.AuthorityModel;
import com.xfsw.account.service.CategoryAuthorityService;
import com.xfsw.account.service.LinkAuthorityService;
import com.xfsw.account.service.RoleCategoryAuthorityService;
import com.xfsw.common.mapper.ICommonMapper;

@Service("categoryAuthorityService")
public class CategoryAuthorityServiceImpl implements CategoryAuthorityService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="roleCategoryAuthorityService")
	RoleCategoryAuthorityService roleCategoryAuthorityService;
	
	@Resource(name="linkAuthorityService")
	LinkAuthorityService linkAuthorityService;
	
//	@Resource(name="authorityCacheService")
//	AuthorityCacheService authorityCacheService;
//	
//	@Resource(name="userSessionService")
//	UserSessionService userSessionService;

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryAuthority> selectAll() {
		return (List<CategoryAuthority>) commonMapper.selectList("CategoryAuthority.selectAll");
	}
	
	public void insertAuthority(CategoryAuthority authority){
		commonMapper.insert("CategoryAuthority.insertAuthority", authority);
	}
	
	public CategoryAuthority get(Integer id){
		return (CategoryAuthority) commonMapper.get(CategoryAuthority.class, id);
	}
	
	@Transactional
	public void deleteAuthority(Integer id,String operator){
		//删除相关功能权限数据
		linkAuthorityService.deleteByCategoryAuthorityId(id, operator);
		//删除菜单权限数据
		commonMapper.deleteAndBak(CategoryAuthority.class, id, operator);
		//删除角色功能权限关系roleAuthority
		roleCategoryAuthorityService.delete(id, operator);
		//TODO 将权限服务与缓存服务解耦,此处代码删除,放到controller中
		//刷新系统总权限缓存
//		authorityCacheService.refresh();
//		//刷新所有用户的角色权限缓存信息
//		userSessionService.refreshUserSessionAuthorityInfo();
	}
	
	public void updateCategoryAuthority(CategoryAuthority authority){
		commonMapper.insert("CategoryAuthority.updateCategoryAuthority", authority);
	}
	
	@SuppressWarnings("unchecked")
	public List<AuthorityModel> selectFirstAuthorityModelList(){
		return (List<AuthorityModel>) commonMapper.selectList("CategoryAuthority.selectFirstAuthorityModelList");
	}
}
