/**
 * 
 */
package net.xueshupa.entity;

import java.util.Date;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class KnowledgeInfoDetail {

	private Integer id;
	private Integer knowledgeInfoId;
	private String knowledgeInfoDetailType;
	private String info;
	private Integer orderIndex;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
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
	public Integer getKnowledgeInfoId() {
		return knowledgeInfoId;
	}
	public void setKnowledgeInfoId(Integer knowledgeInfoId) {
		this.knowledgeInfoId = knowledgeInfoId;
	}
	public String getKnowledgeInfoDetailType() {
		return knowledgeInfoDetailType;
	}
	public void setKnowledgeInfoDetailType(String knowledgeInfoDetailType) {
		this.knowledgeInfoDetailType = knowledgeInfoDetailType;
	}
}
