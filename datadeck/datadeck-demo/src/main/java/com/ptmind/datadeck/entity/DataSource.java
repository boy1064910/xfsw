package com.ptmind.datadeck.entity;

public class DataSource {

	private String code;// 主键,数据源代号
	private String tenantId;// 租户ID
	private Long ownerId;// 用户ID
	private String name;// 数据源名称
	private String description;// 数据源描述
	private String authType;// 授权步骤代码\类型,目前只有oauth2和rest请求两种方式

	private String config;//数据源授权信息配置
	private String stepConfig;//步骤解析配置
	private String fieldConfig;//字段解析配置
	
	private Long creatorId;
	private Long createTime;// 创建时间
	private Long upadtorId;
	private Long updateTime;// 最后操作时间
	
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
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public String getStepConfig() {
		return stepConfig;
	}
	public void setStepConfig(String stepConfig) {
		this.stepConfig = stepConfig;
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
	public String getFieldConfig() {
		return fieldConfig;
	}
	public void setFieldConfig(String fieldConfig) {
		this.fieldConfig = fieldConfig;
	}
}
