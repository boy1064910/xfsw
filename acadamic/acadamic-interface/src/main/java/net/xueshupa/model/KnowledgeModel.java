/**
 * 
 */
package net.xueshupa.model;

import java.util.List;

import net.xueshupa.entity.Knowledge;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class KnowledgeModel extends Knowledge {

	private List<KnowledgeInfoModel> knowledgeInfoList;
	
	public KnowledgeModel(Knowledge knowledge) {
		this.setId(knowledge.getId());
		this.setCode(knowledge.getCode());
		this.setLastUpdater(knowledge.getLastUpdater());
		this.setLastUpdateTime(knowledge.getLastUpdateTime());
		this.setName(knowledge.getName());
		this.setOrderIndex(knowledge.getOrderIndex());
	}

	public List<KnowledgeInfoModel> getKnowledgeInfoList() {
		return knowledgeInfoList;
	}

	public void setKnowledgeInfoList(List<KnowledgeInfoModel> knowledgeInfoList) {
		this.knowledgeInfoList = knowledgeInfoList;
	}
}
