/**
 * 
 */
package net.xueshupa.entity;

import java.util.Date;

import net.xueshupa.enums.ExerciseDetailType;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class ExerciseDetail {

	private Integer id;
	private Integer exerciseId;
	private ExerciseDetailType type;
	private String answer;
	private Integer orderIndex;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
	}
	public String getType(){
		return this.type == null? null : this.type.toString();
	}
	public void setType(ExerciseDetailType type) {
		this.type = type;
	}
	public void setType(String type){
		this.type = ExerciseDetailType.valueOf(type);
		if(this.type==null){
			this.type = ExerciseDetailType.UNKNOWN;
		}
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
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
	public Integer getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}
}
