package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

public class CommonAuthority implements Serializable {

	private static final long serialVersionUID = 1104432927851295621L;
	private Integer id;
	private String name;
	private String url;
	private String lastUpdater;
	private Date lastUpdateTime;

	// 辅助字段
	private Integer oldId;

	public CommonAuthority() {
	}

	public CommonAuthority(Integer id, String url) {
		this.id = id;
		this.url = url;
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

}
