package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.Exercise;
import net.xueshupa.entity.ExerciseDetail;
import net.xueshupa.model.ExerciseModel;

public interface ExerciseService {

	public Integer insertExercise(Exercise exercise);
	
	public void uploadExerciseUrl(Exercise exercise);
	
	public void deleteExercise(Integer exerciseId,String operator);
	
	public Integer insertExerciseDetail(ExerciseDetail exerciseDetail);
	
	public void updateExerciseDetail(ExerciseDetail exerciseDetail);
	
	public void deleteExerciseDetail(Integer exerciseDetailId,String operator);
	
	/**
	 * 根据知识点内容ID数组查询习题数据
	 * @param knowledgeInfoIds
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public List<ExerciseModel> selectModelListByKnowledgeInfoIds(Integer[] knowledgeInfoIds);
	
	public List<ExerciseDetail> selectExerciseDetailListByExerciseIds(Integer[] exerciseIds);
}
