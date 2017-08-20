package com.xfsw.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.account.entity.LinkAuthority;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ListUtil;

@Service("linkAuthorityService")
public class LinkAuthorityServiceImpl implements LinkAuthorityService {

	@Resource(name="accountCommonMapper")
	private ICommonMapper commonMapper;
	
	@Resource(name="roleLinkAuthorityService")
	RoleLinkAuthorityService roleLinkAuthorityService;

	@Resource(name="roleAuthoritySqlService")
	RoleAuthoritySqlService roleAuthoritySqlService;
	
//	@Resource(name="authorityCacheService")
//	AuthorityCacheService authorityCacheService;
//	
//	@Resource(name="roleAuthoritySqlCacheService")
//	RoleAuthoritySqlCacheService roleAuthoritySqlCacheService;
//	
//	@Resource(name="userSessionService")
//	UserSessionService userSessionService;
	
	public void insertLinkAuthority(LinkAuthority linkAuthority){
		commonMapper.insert("LinkAuthority.insertLinkAuthority", linkAuthority);
	}
	
	@Transactional
	public void updateLinkAuthority(LinkAuthority linkAuthority){
		//更新权限信息
		commonMapper.update("LinkAuthority.updateLinkAuthority", linkAuthority);
		//更新该权限对应的角色权限表信息
		commonMapper.update("RoleLinkAuthority.updateRoleLinkAuthority",linkAuthority);
		//更新该角色权限对应的SQL配置表信息
		commonMapper.update("RoleAuthoritySql.updateRoleAuthoritySqlByAuthorityId",linkAuthority);
	}
	
	public LinkAuthority get(Integer id){
		return (LinkAuthority) commonMapper.get(LinkAuthority.class, id);
	}
	
	@Transactional
	public void deleteLinkAuthority(Integer id,String operator){
		//删除功能权限
		commonMapper.deleteAndBak(LinkAuthority.class, id, operator);
		//删除角色功能权限关系RoleLinkAuthority
		roleLinkAuthorityService.deleteRoleLinkAuthority(id, operator);
		//删除角色权限SQL配置信息RoleAuthoritySql
		roleAuthoritySqlService.deleteAndBak(id, operator);
		//TODO 将权限服务与缓存服务解耦,此处代码删除,放到controller中
		//删除该权限对应的角色权限SQL配置信息
//		roleAuthoritySqlCacheService.authorityHashIdDeleteEvent(id);
//		//刷新系统总权限缓存
//		authorityCacheService.refresh();
//		//刷新所有用户的角色权限缓存信息
//		userSessionService.refreshUserSessionAuthorityInfo();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void deleteByCategoryAuthorityId(Integer categoryAuthorityHashId,String operator){
		List<Integer> linkAuthorityIdList = (List<Integer>) commonMapper.selectList("LinkAuthority.selectIdListByCategoryAuthorityId",categoryAuthorityHashId);
		if(!ListUtil.isEmpty(linkAuthorityIdList)){
			for(Integer linkAuthorityId:linkAuthorityIdList){
				//删除功能权限
				commonMapper.deleteAndBak(LinkAuthority.class, linkAuthorityId, operator);
				//删除角色功能权限关系RoleLinkAuthority
				roleLinkAuthorityService.deleteRoleLinkAuthority(linkAuthorityId, operator);
				//删除角色权限SQL配置信息RoleAuthoritySql
				roleAuthoritySqlService.deleteAndBak(linkAuthorityId, operator);
				//TODO 将权限服务与缓存服务解耦,此处代码删除,放到controller中
				//删除该权限对应的角色权限SQL配置信息
//				roleAuthoritySqlCacheService.authorityHashIdDeleteEvent(linkAuthorityId);
			}
		}
	}
}
