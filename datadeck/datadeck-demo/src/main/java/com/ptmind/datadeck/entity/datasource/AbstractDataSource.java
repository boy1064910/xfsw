package com.ptmind.datadeck.entity.datasource;

import com.ptmind.datadeck.entity.DataSource;

/**
 * 数据源定义模型公共抽象结构 Created by Garry on 2017/8/22.
 */
public abstract class AbstractDataSource {

	protected String code;// 主键,数据源代号
	protected String tenantId;// 租户ID
	protected Long ownerId;// 用户ID
	protected String name;// 数据源名称
	protected String description;// 数据源描述
	protected String authType;// 授权步骤代码\类型,目前只有oauth2和rest请求两种方式

	private Long creatorId;
	private Long createTime;// 创建时间
	private Long upadtorId;
	private Long updateTime;// 最后操作时间
	
	public AbstractDataSource() {}

	public AbstractDataSource(DataSource dataSource) {
		this.code = dataSource.getCode();
		this.tenantId = dataSource.getTenantId();
		this.ownerId = dataSource.getOwnerId();
		this.name = dataSource.getName();
		this.description = dataSource.getDescription();
		this.authType = dataSource.getAuthType();
		this.creatorId = dataSource.getCreatorId();
		this.createTime = dataSource.getCreateTime();
		this.upadtorId = dataSource.getUpadtorId();
		this.updateTime = dataSource.getUpdateTime();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
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

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpadtorId() {
		return upadtorId;
	}

	public void setUpadtorId(Long upadtorId) {
		this.upadtorId = upadtorId;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

}
