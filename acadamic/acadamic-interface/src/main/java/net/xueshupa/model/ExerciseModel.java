/**
 * 
 */
package net.xueshupa.model;

import java.util.List;

import net.xueshupa.entity.Exercise;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class ExerciseModel extends Exercise {

	public ExerciseModel(Exercise exercise) {
		this.setId(exercise.getId());
		this.setKnowledgeInfoId(exercise.getKnowledgeInfoId());
		this.setExerciseUrl(exercise.getExerciseUrl());
		this.setLastUpdater(exercise.getLastUpdater());
		this.setLastUpdateTime(exercise.getLastUpdateTime());
		this.setOrderIndex(exercise.getOrderIndex());
	}
	
	private List<ExerciseDetailModel> exerciseDetailModelList;

	public List<ExerciseDetailModel> getExerciseDetailModelList() {
		return exerciseDetailModelList;
	}

	public void setExerciseDetailModelList(List<ExerciseDetailModel> exerciseDetailModelList) {
		this.exerciseDetailModelList = exerciseDetailModelList;
	}

}
