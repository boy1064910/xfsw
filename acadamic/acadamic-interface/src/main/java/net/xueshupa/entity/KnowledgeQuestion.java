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
public class KnowledgeQuestion implements Serializable {

	private static final long serialVersionUID = -1107072027994848053L;

	private Integer id;
	private Integer knowledgePointContentId;
	private String content;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	private List<KnowledgeQuestionAnswer> answerList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public List<KnowledgeQuestionAnswer> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<KnowledgeQuestionAnswer> answerList) {
		this.answerList = answerList;
	}
	public Integer getKnowledgePointContentId() {
		return knowledgePointContentId;
	}
	public void setKnowledgePointContentId(Integer knowledgePointContentId) {
		this.knowledgePointContentId = knowledgePointContentId;
	}
}
