package com.xfsw.account.service;

import java.util.List;

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
