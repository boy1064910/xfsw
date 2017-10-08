package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户、空间、角色关系数据结构
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class UserTenantRole implements Serializable{

	private static final long serialVersionUID = 2662799098796125129L;
	private Integer id;
	private Integer userId;
	private Integer tenantId;
	private Integer roleId;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public UserTenantRole() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getLastUpdater() {
		return lastUpdater;
	}

	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
