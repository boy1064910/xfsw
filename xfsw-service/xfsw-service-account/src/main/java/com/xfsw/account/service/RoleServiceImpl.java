package com.xfsw.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.account.entity.Role;
import com.xfsw.common.mapper.ICommonMapper;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public List<Role> selectListByTenantId(Integer tenantId) {
		Role role = new Role();
		role.setTenantId(tenantId);
		return commonMapper.selectList(Role.class, role);
	}

	
//	
//	@Resource(name="userService")
//	UserService userService;
//	
//	@Resource(name="roleCategoryAuthorityService")
//	RoleCategoryAuthorityService roleCategoryAuthorityService;
//	
//	@Resource(name="roleLinkAuthorityService")
//	RoleLinkAuthorityService roleLinkAuthorityService;
//	
//	@Resource(name="roleAuthoritySqlService")
//	RoleAuthoritySqlService roleAuthoritySqlService;
//	
//	@Resource(name="roleAuthoritySqlParamService")
//	RoleAuthoritySqlParamService roleAuthoritySqlParamService;
//	
//	@Override
//	public Role get(Integer id) {
//		return (Role) commonMapper.get(Role.class, id);
//	}
//	
//	@Transactional
//	public void updateRole(Role role,Integer[] ids,Integer[] types){
//		List<Integer> oldCategoryAuthorityIds = roleCategoryAuthorityService.selectAuthorityIdsByRoleId(role.getId());
//		List<Integer> oldLinkAuthorityIds = roleLinkAuthorityService.selectAuthorityIdsByRoleId(role.getId());
//		
//		Map<Integer,Integer> oldAuthorityIdsMap = new HashMap<Integer,Integer>();
//		if(!ListUtil.isEmpty(oldCategoryAuthorityIds)){
//			for(Integer id:oldCategoryAuthorityIds){
//				oldAuthorityIdsMap.put(id, id);
//			}
//		}
//		
//		Map<Integer,Integer> oldLinkAuthorityIdsMap = new HashMap<Integer,Integer>();
//		if(!ListUtil.isEmpty(oldLinkAuthorityIds)){
//			for(Integer id:oldLinkAuthorityIds){
//				oldLinkAuthorityIdsMap.put(id, id);
//			}
//		}
//		
//		//新增的菜单权限ID数组
//		List<Integer> addAuthorityIds = new ArrayList<Integer>();
//		//新增的功能权限ID数组
//		List<Integer> addLinkAuthorityIds = new ArrayList<Integer>();
//		
//		//筛选出新增的权限ID和解除的权限ID
//		if(!ArrayUtil.isEmpty(ids)){
//			for(int i=0;i<ids.length;i++){
//				if(types[i].intValue()==1){//菜单权限
//					if(oldAuthorityIdsMap.containsKey(ids[i])){
//						oldAuthorityIdsMap.remove(ids[i]);
//					}
//					else{
//						addAuthorityIds.add(ids[i]);
//					}
//				}
//				else{//功能权限
//					if(oldLinkAuthorityIdsMap.containsKey(ids[i])){
//						oldLinkAuthorityIdsMap.remove(ids[i]);
//					}
//					else{
//						addLinkAuthorityIds.add(ids[i]);
//					}
//				}
//			}
//		}
//		
//		//保存新增的角色权限信息
//		if(!ListUtil.isEmpty(addAuthorityIds)){
//			roleCategoryAuthorityService.insertRoleCategoryAuthorityList(role.getId(), addAuthorityIds,role.getLastUpdater());
//		}
//		
//		if(!ListUtil.isEmpty(addLinkAuthorityIds)){
//			roleLinkAuthorityService.insertRoleLinkAuthorityList(role.getId(), addLinkAuthorityIds,role.getLastUpdater());
//		}
//		
//		if(!MapUtil.isEmpty(oldAuthorityIdsMap)){
//			//删除的菜单权限ID集合
//			Integer[] delAuthorityIds = new Integer[oldAuthorityIdsMap.size()];
//			oldAuthorityIdsMap.keySet().toArray(delAuthorityIds);
//			if(!ArrayUtil.isEmpty(delAuthorityIds)){
//				//删除角色权限SQL配置参数信息
//				roleAuthoritySqlParamService.deleteAndBakByRoleIdAndAuthorityIds(role.getId(), delAuthorityIds);
//				//删除角色权限SQL配置信息
//				roleAuthoritySqlService.deleteAndBakByRoleIdAndAuthorityIds(role.getId(), delAuthorityIds);
//				//删除角色权限信息
//				roleCategoryAuthorityService.deleteAndBakByRoleIdAndAuthorityIds(role.getId(), delAuthorityIds);
//			}
//		}
//		
//		if(!MapUtil.isEmpty(oldLinkAuthorityIdsMap)){
//			//删除的功能权限ID集合
//			Integer[] delLinkAuthorityIds = new Integer[oldLinkAuthorityIdsMap.size()];
//			oldLinkAuthorityIdsMap.keySet().toArray(delLinkAuthorityIds);
//			if(!ArrayUtil.isEmpty(delLinkAuthorityIds)){
//				//删除角色权限SQL配置参数信息
//				roleAuthoritySqlParamService.deleteAndBakByRoleIdAndAuthorityIds(role.getId(), delLinkAuthorityIds);
//				//删除角色权限SQL配置信息
//				roleAuthoritySqlService.deleteAndBakByRoleIdAndAuthorityIds(role.getId(), delLinkAuthorityIds);
//				//删除角色功能权限信息
//				roleLinkAuthorityService.deleteAndBakByRoleIdAndAuthorityIds(role.getId(), delLinkAuthorityIds);
//			}
//		}
//		commonMapper.update("Role.updateRole", role);
//	}
//	
//	@Transactional
//	public void addRole(Role role,Integer[] ids,Integer[] types){
//		commonMapper.insert("Role.addRole", role);
//		//保存角色权限关系
//		if(!ArrayUtil.isEmpty(ids)){
//			List<Integer> authorityIdList = new ArrayList<Integer>();
//			List<Integer> linkAuthorityIdList = new ArrayList<Integer>();
//			for(int i=0;i<ids.length;i++){
//				if(types[i].intValue()==1){
//					authorityIdList.add(ids[i]);
//				}
//				else{
//					linkAuthorityIdList.add(ids[i]);
//				}
//			}
//			
//			if(!ListUtil.isEmpty(authorityIdList)){
//				roleCategoryAuthorityService.insertRoleCategoryAuthorityList(role.getId(), authorityIdList,role.getLastUpdater());
//			}
//			
//			if(!ListUtil.isEmpty(linkAuthorityIdList)){
//				roleLinkAuthorityService.insertRoleLinkAuthorityList(role.getId(), linkAuthorityIdList,role.getLastUpdater());
//			}
//		}
//	}

//	@Transactional
//	public void deleteRole(Integer roleId,String operator){
//		// 判断角色是否已被用户绑定关系
//		if(userService.checkByRoleId(roleId)){//检查该角色是否已经配置用户，若已配置，抛出异常
//			throw new RuntimeException("该角色已被使用，无法删除 ");
//		}
//		//删除角色
//		commonMapper.deleteAndBak(Role.class, roleId, operator);
//		//删除角色权限数据
//		roleCategoryAuthorityService.deleteByRoleId(roleId, operator);
//		roleLinkAuthorityService.deleteByRoleId(roleId, operator);
//		//删除角色权限对应的SQL数据(RoleAuthoritySql)
//		roleAuthoritySqlService.deleteByRoleId(roleId, operator);
//		//删除角色对应的SQL参数数据(RoleAuthoritySqlParam)
//		roleAuthoritySqlParamService.deleteByRoleId(roleId, operator);
//	}
	
//	public List<Role> selectAll(){
//		return commonMapper.selectAll(Role.class);
//	}
}
