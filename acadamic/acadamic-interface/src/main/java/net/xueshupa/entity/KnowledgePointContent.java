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
public class KnowledgePointContent implements Serializable {

	private static final long serialVersionUID = 8456883261726395083L;

	private Integer id;
	private Integer knowledgePointId;
	private String code;
	private String type;
	private String content;
	private Integer orderIndex;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	private List<KnowledgeQuestion> questionList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getKnowledgePointId() {
		return knowledgePointId;
	}
	public void setKnowledgePointId(Integer knowledgePointId) {
		this.knowledgePointId = knowledgePointId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public List<KnowledgeQuestion> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<KnowledgeQuestion> questionList) {
		this.questionList = questionList;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
}
