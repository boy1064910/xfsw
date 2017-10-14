package com.xfsw.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.LinkAuthority;
import com.xfsw.common.mapper.ICommonMapper;

@Service("linkAuthorityService")
public class LinkAuthorityServiceImpl implements LinkAuthorityService {

	@Resource(name="accountCommonMapper")
	private ICommonMapper commonMapper;
	
	@Resource(name="roleLinkAuthorityService")
	RoleLinkAuthorityService roleLinkAuthorityService;

	@Resource(name="roleAuthoritySqlService")
	RoleAuthoritySqlService roleAuthoritySqlService;
	
	@Resource(name="authorityCacheService")
	AuthorityCacheService authorityCacheService;

	@Override
	public List<LinkAuthority> selectListByCategoryAuthorityId(Integer categoryAuthorityId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("categoryAuthorityId", categoryAuthorityId);
		return commonMapper.selectList(LinkAuthority.class, params);
	}
	
	@Override
	public LinkAuthority getById(Integer id){
		return commonMapper.get(LinkAuthority.class, id);
	}
	
	@Override
	@Transactional
	public void updateLinkAuthority(LinkAuthority linkAuthority){
		//更新权限信息
		commonMapper.update("LinkAuthority.updateLinkAuthority", linkAuthority);
		//更新该权限对应的角色权限表信息
		commonMapper.update("RoleLinkAuthority.updateRoleLinkAuthority",linkAuthority);
		//更新该角色权限对应的SQL配置表信息
		commonMapper.update("RoleAuthoritySql.updateRoleAuthoritySqlByAuthorityId",linkAuthority);
		//刷新缓存
		authorityCacheService.reloadLinkAuthorityIntoAuthorityCache();
	}
	
	@Override
	public void insertLinkAuthority(LinkAuthority linkAuthority){
		commonMapper.insert(LinkAuthority.class, linkAuthority);
		//刷新缓存
		authorityCacheService.reloadLinkAuthorityIntoAuthorityCache();
	}
	
	@Override
	@Transactional
	public void initLinkAuthority(List<LinkAuthority> linkAuthorityList,Map<Integer,Integer> categoryAuthorityIdMap){
		for(LinkAuthority linkAuthority:linkAuthorityList){
			if(commonMapper.check("LinkAuthority.isExsitLinkAuthority",linkAuthority)){
				continue;
			}
			Integer oldId = linkAuthority.getCategoryAuthorityId();
			linkAuthority.setCategoryAuthorityId(categoryAuthorityIdMap.get(oldId));
			commonMapper.insert(LinkAuthority.class, linkAuthority);
		}
	}
	
	
//	@Resource(name="authorityCacheService")
//	AuthorityCacheService authorityCacheService;
//	
//	@Resource(name="roleAuthoritySqlCacheService")
//	RoleAuthoritySqlCacheService roleAuthoritySqlCacheService;
//	
//	@Resource(name="userSessionService")
//	UserSessionService userSessionService;
	
//	public void insertLinkAuthority(LinkAuthority linkAuthority){
//		commonMapper.insert("LinkAuthority.insertLinkAuthority", linkAuthority);
//	}
//	
//	@Transactional
//	public void updateLinkAuthority(LinkAuthority linkAuthority){
//		//更新权限信息
//		commonMapper.update("LinkAuthority.updateLinkAuthority", linkAuthority);
//		//更新该权限对应的角色权限表信息
//		commonMapper.update("RoleLinkAuthority.updateRoleLinkAuthority",linkAuthority);
//		//更新该角色权限对应的SQL配置表信息
//		commonMapper.update("RoleAuthoritySql.updateRoleAuthoritySqlByAuthorityId",linkAuthority);
//	}
//	
//	public LinkAuthority get(Integer id){
//		return (LinkAuthority) commonMapper.get(LinkAuthority.class, id);
//	}
//	
//	@Transactional
//	public void deleteLinkAuthority(Integer id,String operator){
//		//删除功能权限
//		commonMapper.deleteAndBak(LinkAuthority.class, id, operator);
//		//删除角色功能权限关系RoleLinkAuthority
//		roleLinkAuthorityService.deleteRoleLinkAuthority(id, operator);
//		//删除角色权限SQL配置信息RoleAuthoritySql
//		roleAuthoritySqlService.deleteAndBak(id, operator);
//		//TODO 将权限服务与缓存服务解耦,此处代码删除,放到controller中
//		//删除该权限对应的角色权限SQL配置信息
////		roleAuthoritySqlCacheService.authorityHashIdDeleteEvent(id);
////		//刷新系统总权限缓存
////		authorityCacheService.refresh();
////		//刷新所有用户的角色权限缓存信息
////		userSessionService.refreshUserSessionAuthorityInfo();
//	}
//	
//	@Transactional
//	public void deleteByCategoryAuthorityId(Integer categoryAuthorityHashId,String operator){
//		List<Integer> linkAuthorityIdList = commonMapper.selectList("LinkAuthority.selectIdListByCategoryAuthorityId",categoryAuthorityHashId);
//		if(!ListUtil.isEmpty(linkAuthorityIdList)){
//			for(Integer linkAuthorityId:linkAuthorityIdList){
//				//删除功能权限
//				commonMapper.deleteAndBak(LinkAuthority.class, linkAuthorityId, operator);
//				//删除角色功能权限关系RoleLinkAuthority
//				roleLinkAuthorityService.deleteRoleLinkAuthority(linkAuthorityId, operator);
//				//删除角色权限SQL配置信息RoleAuthoritySql
//				roleAuthoritySqlService.deleteAndBak(linkAuthorityId, operator);
//				//TODO 将权限服务与缓存服务解耦,此处代码删除,放到controller中
//				//删除该权限对应的角色权限SQL配置信息
////				roleAuthoritySqlCacheService.authorityHashIdDeleteEvent(linkAuthorityId);
//			}
//		}
//	}
}
