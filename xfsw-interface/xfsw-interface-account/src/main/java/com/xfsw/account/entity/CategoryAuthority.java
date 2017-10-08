/**
 * 
 */
package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuxiaopeng
 *
 */
public class CategoryAuthority implements Serializable {

	private static final long serialVersionUID = 6280544017377387868L;
	private Integer id;
	private Integer hashId;
	private Integer pid;
	private String name;
	private String url;
	private String remark;
	private Integer orderIndex;
	private String ico;
	private Integer tenantId;
	private String lastUpdater;
	private Date lastUpdateTime;
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getHashId() {
		return hashId;
	}
	public void setHashId(Integer hashId) {
		this.hashId = hashId;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
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
}
