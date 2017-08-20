package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

public class LinkAuthority implements Serializable {

	private static final long serialVersionUID = -736902529228470678L;
	private Integer id;
	private String name;
	private Integer authorityId;
	private String url;
	private String lastUpdater;
	private Date lastUpdateTime;

	private Integer oldId;

	public LinkAuthority() {
	}

	public LinkAuthority(Integer id, Integer authorityId, String url) {
		this.id = id;
		this.authorityId = authorityId;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
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

}
