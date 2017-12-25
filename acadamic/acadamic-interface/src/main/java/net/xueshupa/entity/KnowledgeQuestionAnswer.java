/**
 * 
 */
package net.xueshupa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class KnowledgeQuestionAnswer implements Serializable {

	private static final long serialVersionUID = -7021852154603365470L;

	private String knowledgeQuestionId;
	private String code;
	private String rightAnswer;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public String getKnowledgeQuestionId() {
		return knowledgeQuestionId;
	}
	public void setKnowledgeQuestionId(String knowledgeQuestionId) {
		this.knowledgeQuestionId = knowledgeQuestionId;
	}
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
}
