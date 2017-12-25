/**
 * 
 */
package net.xueshupa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class KnowledgeQuestion implements Serializable {

	private static final long serialVersionUID = -1107072027994848053L;

	private Integer id;
	private Integer knowledgeContentId;
	private String content;
	private String lastUpdater;
	private Date lastUpdateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getKnowledgeContentId() {
		return knowledgeContentId;
	}
	public void setKnowledgeContentId(Integer knowledgeContentId) {
		this.knowledgeContentId = knowledgeContentId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
