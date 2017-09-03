package com.ptmind.datadeck.model.datasource;

import com.ptmind.datadeck.entity.datasource.auth.AbstractAuthConfig;

/**
 * 数据源基础信息数据结构
 * @author xiaopeng.liu
 * @version 3.0.0
 */
public class DataSourceAuthConfig {

	String code;
	String name;
	String description;
	
	AbstractAuthConfig authConfig;//数据源配置信息中的授权配置json映射的结构体

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AbstractAuthConfig getAuthConfig() {
		return authConfig;
	}

	public void setAuthConfig(AbstractAuthConfig authConfig) {
		this.authConfig = authConfig;
	}
}
