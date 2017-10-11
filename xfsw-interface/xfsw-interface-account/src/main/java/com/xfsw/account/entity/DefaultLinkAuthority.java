package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 默认功能权限类结构
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class DefaultLinkAuthority implements Serializable {

	private static final long serialVersionUID = -4361648509697693507L;
	private Integer id;
	private String name;
	private Integer defaultAuthorityId;
	private String url;
	private String lastUpdater;
	private Date lastUpdateTime;

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

	public Integer getDefaultAuthorityId() {
		return defaultAuthorityId;
	}

	public void setDefaultAuthorityId(Integer defaultAuthorityId) {
		this.defaultAuthorityId = defaultAuthorityId;
	}

}
