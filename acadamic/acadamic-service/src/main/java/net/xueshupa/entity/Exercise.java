package net.xueshupa.entity;
import java.util.Date;

/**
* Exercise 
* @author lxp
* Mon Jul 24 22:18:23 CST 2017
*/ 
public class Exercise{

	private Integer id;
	private Integer knowledgeInfoId;
	private String exerciseUrl;
	private Integer orderIndex;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setOrderIndex(Integer orderIndex){
		this.orderIndex=orderIndex;
	}
	public Integer getOrderIndex(){
		return orderIndex;
	}
	public void setLastUpdater(String lastUpdater){
		this.lastUpdater=lastUpdater;
	}
	public String getLastUpdater(){
		return lastUpdater;
	}
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime=lastUpdateTime;
	}
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
	public Integer getKnowledgeInfoId() {
		return knowledgeInfoId;
	}
	public void setKnowledgeInfoId(Integer knowledgeInfoId) {
		this.knowledgeInfoId = knowledgeInfoId;
	}
	public String getExerciseUrl() {
		return exerciseUrl;
	}
	public void setExerciseUrl(String exerciseUrl) {
		this.exerciseUrl = exerciseUrl;
	}
}

