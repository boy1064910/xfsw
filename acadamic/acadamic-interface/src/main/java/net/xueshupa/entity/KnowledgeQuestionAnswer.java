/**
 * 
 */
package net.xueshupa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 知识点练习题答案
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class KnowledgeQuestionAnswer implements Serializable {

	private static final long serialVersionUID = -7021852154603365470L;

	private Integer knowledgeQuestionId;
	private String code;
	private String rightAnswer;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public Integer getKnowledgeQuestionId() {
		return knowledgeQuestionId;
	}
	public void setKnowledgeQuestionId(Integer knowledgeQuestionId) {
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
