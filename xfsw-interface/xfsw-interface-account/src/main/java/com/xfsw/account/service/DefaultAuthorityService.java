package com.xfsw.account.service;

import java.util.List;

import com.xfsw.account.entity.DefaultAuthority;

/**
 * 默认权限服务接口
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface DefaultAuthorityService {

	List<DefaultAuthority> selectAll();
}
