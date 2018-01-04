/**
 * 
 */
package net.xueshupa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 知识点内容答案
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class KnowledgePointContentAnswer implements Serializable {

	private static final long serialVersionUID = -7021852154603365470L;

	private Integer id;
	private Integer knowledgePointContentId;
	private String code;
	private String rightAnswer;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getKnowledgePointContentId() {
		return knowledgePointContentId;
	}
	public void setKnowledgePointContentId(Integer knowledgePointContentId) {
		this.knowledgePointContentId = knowledgePointContentId;
	}
}
