package com.ptmind.datadeck.entity.datasource;

import com.ptmind.datadeck.entity.DataSource;
import com.ptmind.datadeck.entity.datasource.auth.AbstractAuthConfig;
import com.ptmind.datadeck.entity.datasource.auth.AuthTypeEnum;

/**
 * 携带授权信息的数据源数据结构(提供携带授权信息的数据源配置接口使用)
 * @author lxp
 * @version 3.0.0
 */
public class AuthConfigDataSource extends AbstractDataSource {

	AbstractAuthConfig authConfig;//数据源配置信息中的授权配置json映射的结构体
	
	public AuthConfigDataSource() {}
	
	public AuthConfigDataSource(DataSource dataSource) {
		super(dataSource);
		this.authConfig = AuthTypeEnum.initAuthConfig(dataSource.getAuthType(), dataSource.getConfig());
	}

	public AbstractAuthConfig getAuthConfig() {
		return authConfig;
	}

	public void setAuthConfig(AbstractAuthConfig authConfig) {
		this.authConfig = authConfig;
	}
}
