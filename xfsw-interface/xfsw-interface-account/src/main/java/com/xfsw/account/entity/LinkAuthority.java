package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

public class LinkAuthority implements Serializable {

	private static final long serialVersionUID = -736902529228470678L;
	private Integer id;
	private String name;
	private Integer categoryAuthorityId;
	private String url;
	private Integer tenantId;
	private String lastUpdater;
	private Date lastUpdateTime;

	//辅助字段
	private Integer oldId;

	public LinkAuthority() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
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

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getCategoryAuthorityId() {
		return categoryAuthorityId;
	}

	public void setCategoryAuthorityId(Integer categoryAuthorityId) {
		this.categoryAuthorityId = categoryAuthorityId;
	}

}
