/**
 * 
 */
package net.xueshupa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class KnowledgePoint implements Serializable {

	private static final long serialVersionUID = 8456883261726395083L;

	private Integer id;
	private Integer knowledgeId;
	private String code;
	private String title;
	private Integer orderIndex;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	private List<KnowledgePointContent> contentList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public List<KnowledgePointContent> getContentList() {
		return contentList;
	}
	public void setContentList(List<KnowledgePointContent> contentList) {
		this.contentList = contentList;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
