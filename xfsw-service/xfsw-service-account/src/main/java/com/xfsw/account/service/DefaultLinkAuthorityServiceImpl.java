/**
 * 
 */
package com.xfsw.account.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.account.entity.DefaultLinkAuthority;
import com.xfsw.common.mapper.ICommonMapper;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Service("defaultLinkAuthorityService")
public class DefaultLinkAuthorityServiceImpl implements DefaultLinkAuthorityService {

	@Resource(name="accountCommonMapper")
	private ICommonMapper commonMapper;
	
	@Override
	public List<DefaultLinkAuthority> selectListByDefaultAuthorityId(Integer defaultAuthorityId) {
		DefaultLinkAuthority defaultLinkAuthority = new DefaultLinkAuthority();
		defaultLinkAuthority.setDefaultAuthorityId(defaultAuthorityId);
		return commonMapper.selectList(DefaultLinkAuthority.class, defaultLinkAuthority);
	}
	
	@Override
	public void insertDefaultLinkAuthority(DefaultLinkAuthority defaultLinkAuthority){
		commonMapper.insert(DefaultLinkAuthority.class, defaultLinkAuthority);
	}
	
	@Override
	public void deleteDefaultLinkAuthority(Integer id,String operator){
		commonMapper.deleteAndBak(DefaultLinkAuthority.class, id, operator);
	}
	
	@Override
	public List<DefaultLinkAuthority> selectAll(){
		return commonMapper.selectAll(DefaultLinkAuthority.class);
	}

}
