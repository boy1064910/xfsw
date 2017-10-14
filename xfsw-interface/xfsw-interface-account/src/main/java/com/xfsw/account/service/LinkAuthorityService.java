package com.xfsw.account.service;

import java.util.List;
import java.util.Map;

import com.xfsw.account.entity.LinkAuthority;

/**
 * 
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface LinkAuthorityService {

	/**
	 * 根据菜单权限查询功能权限数据
	 * @param categoryAuthorityId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<LinkAuthority> selectListByCategoryAuthorityId(Integer categoryAuthorityId);
	
	/**
	 * 通过ID查询单个功能权限数据
	 * @param id
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	LinkAuthority getById(Integer id);
	
	/**
	 * 更新功能权限信息
	 * @param linkAuthority
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void updateLinkAuthority(LinkAuthority linkAuthority);
	
	/**
	 * 新增功能权限信息
	 * @param linkAuthority
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void insertLinkAuthority(LinkAuthority linkAuthority);
	
	/**
	 * 初始化功能权限
	 * @param linkAuthorityList
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	List<Integer> initLinkAuthority(List<LinkAuthority> linkAuthorityList,Map<Integer,Integer> categoryAuthorityIdMap);
	
//	void insertLinkAuthority(LinkAuthority linkAuthority);
//	
//	void updateLinkAuthority(LinkAuthority linkAuthority);
//	
//	LinkAuthority get(Integer id);
//	
//	void deleteLinkAuthority(Integer id,String operator);
//	
//	void deleteByCategoryAuthorityId(Integer categoryAuthorityHashId,String operator);

}
