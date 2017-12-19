/**
 * 
 */
package net.xueshupa.entity;

import java.util.Date;

/**
 * 章节学习进度
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class ProgressChapter {
	private Integer id;
	private Integer userId;
	private Integer courseId;
	private Integer chapterId;
	private Integer knowledgeId;
	private Double progressPercent;
	private Date createTime;
	private Date lastUpdateTime;
	private Date buyTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public Integer getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Double getProgressPercent() {
		return progressPercent;
	}
	public void setProgressPercent(Double progressPercent) {
		this.progressPercent = progressPercent;
	}
	public Date getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
}
