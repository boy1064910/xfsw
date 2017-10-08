package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户与空间关系数据结构
 * @author xiaopeng.liu
 * @version 3.0.0
 */
public class UserTenant implements Serializable{

	private static final long serialVersionUID = 386865461710078713L;
	private Integer id;
	private Integer userId;
	private Integer tenantId;
	private Date lastLoginTime;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public UserTenant() {}
	
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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
}
