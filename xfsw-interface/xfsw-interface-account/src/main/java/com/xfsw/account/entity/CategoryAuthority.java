/**
 * 
 */
package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.xfsw.common.util.DJBHashUtil;
import com.xfsw.common.util.StringUtil;

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
	private Integer defaultAuthorityId;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public CategoryAuthority(){}
	
	public CategoryAuthority(DefaultAuthority defaultAuthority,Integer tenantId,String tenantCode) {
		super();
		this.id = defaultAuthority.getId();
		this.pid = defaultAuthority.getPid();
		this.name = defaultAuthority.getName();
		this.url = defaultAuthority.getUrl();
		this.remark = defaultAuthority.getRemark();
		this.ico = defaultAuthority.getIco();
		this.tenantId = tenantId;
		this.defaultAuthorityId = defaultAuthority.getId();
		
		if(!StringUtils.isEmpty(defaultAuthority.getUrl())){
			String url = defaultAuthority.getUrl();
			String subfixUrl = "aRandomCode="+tenantCode+"-"+StringUtil.getRandomString(8);
			url = url.contains("?") ? (url + "&" + subfixUrl) : (url + "?" + subfixUrl);
			this.url = url;
			this.hashId = DJBHashUtil.DJBHashId(this.url);
		}
	}
	
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
	public Integer getDefaultAuthorityId() {
		return defaultAuthorityId;
	}
	public void setDefaultAuthorityId(Integer defaultAuthorityId) {
		this.defaultAuthorityId = defaultAuthorityId;
	}
}
