/**
 * 
 */
package com.xfsw.account.service;

import com.xfsw.account.entity.LinkAuthority;

/**
 * @author {xiaopeng.liu@decked.com.cn} 用户数据服务接口
 */
public interface LinkAuthorityService {

	void insertLinkAuthority(LinkAuthority linkAuthority);
	
	void updateLinkAuthority(LinkAuthority linkAuthority);
	
	LinkAuthority get(Integer id);
	
	void deleteLinkAuthority(Integer id,String operator);
	
	void deleteByCategoryAuthorityId(Integer categoryAuthorityHashId,String operator);

}
