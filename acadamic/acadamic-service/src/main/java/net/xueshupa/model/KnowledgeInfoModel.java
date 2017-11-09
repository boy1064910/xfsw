/**
 * 
 */
package net.xueshupa.model;

import java.util.List;

import net.xueshupa.entity.KnowledgeInfo;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class KnowledgeInfoModel extends KnowledgeInfo {

	public KnowledgeInfoModel(KnowledgeInfo knowledgeInfo) {
		this.setId(knowledgeInfo.getId());
		this.setKnowledgeId(knowledgeInfo.getKnowledgeId());
		this.setLastUpdater(knowledgeInfo.getLastUpdater());
		this.setLastUpdateTime(knowledgeInfo.getLastUpdateTime());
		this.setOrderIndex(knowledgeInfo.getOrderIndex());
		this.setType(knowledgeInfo.getType());
	}
	
	private List<ExerciseModel> exerciseList;

	public List<ExerciseModel> getExerciseList() {
		return exerciseList;
	}

	public void setExerciseList(List<ExerciseModel> exerciseList) {
		this.exerciseList = exerciseList;
	}
}
