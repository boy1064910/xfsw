package net.xueshupa.entity;
import java.util.Date;

/**
* Answer 
* @author lxp
* Mon Jul 31 13:18:23 CST 2017
*/ 
public class Answer{

	private Integer id;
	private Integer exerciseDetailId;
	private String answer;
	private Integer orderIndex;
	private String lastUpdater;
	private Date lastUpdateTime;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public Integer getExerciseDetailId() {
		return exerciseDetailId;
	}
	public void setExerciseDetailId(Integer exerciseDetailId) {
		this.exerciseDetailId = exerciseDetailId;
	}
	public void setAnswer(String answer){
		this.answer=answer;
	}
	public String getAnswer(){
		return answer;
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
}

