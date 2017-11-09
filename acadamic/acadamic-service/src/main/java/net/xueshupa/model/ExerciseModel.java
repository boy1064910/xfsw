/**
 * 
 */
package net.xueshupa.model;

import java.util.List;

import net.xueshupa.entity.Exercise;
import net.xueshupa.entity.ExerciseDetail;

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
	
	private List<ExerciseDetail> exerciseDetailList;

	public List<ExerciseDetail> getExerciseDetailList() {
		return exerciseDetailList;
	}

	public void setExerciseDetailList(List<ExerciseDetail> exerciseDetailList) {
		this.exerciseDetailList = exerciseDetailList;
	}
}
