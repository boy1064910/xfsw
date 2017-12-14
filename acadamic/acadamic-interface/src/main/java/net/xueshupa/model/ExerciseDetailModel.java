/**
 * 
 */
package net.xueshupa.model;

import net.xueshupa.entity.ExerciseDetail;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class ExerciseDetailModel extends ExerciseDetail {

	private String answerUrl;
	
	public ExerciseDetailModel(ExerciseDetail exerciseDetail) {
		this.setId(exerciseDetail.getId());
		this.setAnswer(exerciseDetail.getAnswer());
		this.setExerciseId(exerciseDetail.getExerciseId());
		this.setLastUpdater(exerciseDetail.getLastUpdater());
		this.setLastUpdateTime(exerciseDetail.getLastUpdateTime());
		this.setOrderIndex(exerciseDetail.getOrderIndex());
		this.setType(exerciseDetail.getExerciseDetailType());
	}
	
	public String getAnswerUrl() {
		return answerUrl;
	}

	public void setAnswerUrl(String answerUrl) {
		this.answerUrl = answerUrl;
	}
	
}
