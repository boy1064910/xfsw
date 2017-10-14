/**
 * 
 */
package com.xfsw.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void deleteById(Integer id,String operator){
		commonMapper.deleteAndBak(DefaultLinkAuthority.class, id, operator);
	}
	
	@Override
	public void deleteByDefaultAuthorityId(Integer defaultAuthorityId,String operator){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("defaultAuthorityId", defaultAuthorityId);
		commonMapper.deleteAndBak(DefaultLinkAuthority.class, params, operator);
	}
	
	@Override
	public List<DefaultLinkAuthority> selectAll(){
		return commonMapper.selectAll(DefaultLinkAuthority.class);
	}

}
