/**
 * 
 */
package net.xueshupa.model;

import java.util.List;

import net.xueshupa.entity.KnowledgeInfo;
import net.xueshupa.entity.KnowledgeInfoDetail;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class KnowledgeInfoModel extends KnowledgeInfo {

	private List<ExerciseModel> exerciseList;
	private List<KnowledgeInfoDetail> knowledgeInfoDetailList;
	
	public KnowledgeInfoModel(KnowledgeInfo knowledgeInfo) {
		this.setId(knowledgeInfo.getId());
		this.setKnowledgeId(knowledgeInfo.getKnowledgeId());
		this.setLastUpdater(knowledgeInfo.getLastUpdater());
		this.setLastUpdateTime(knowledgeInfo.getLastUpdateTime());
		this.setOrderIndex(knowledgeInfo.getOrderIndex());
		this.setType(knowledgeInfo.getType());
	}
	
	public List<ExerciseModel> getExerciseList() {
		return exerciseList;
	}

	public void setExerciseList(List<ExerciseModel> exerciseList) {
		this.exerciseList = exerciseList;
	}

	public List<KnowledgeInfoDetail> getKnowledgeInfoDetailList() {
		return knowledgeInfoDetailList;
	}

	public void setKnowledgeInfoDetailList(List<KnowledgeInfoDetail> knowledgeInfoDetailList) {
		this.knowledgeInfoDetailList = knowledgeInfoDetailList;
	}
}
