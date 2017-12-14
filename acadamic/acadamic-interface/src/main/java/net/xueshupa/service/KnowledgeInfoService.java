package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.KnowledgeInfo;
import net.xueshupa.entity.KnowledgeInfoDetail;
import net.xueshupa.model.KnowledgeInfoModel;

public interface KnowledgeInfoService {

	public Integer saveKnowledgeInfo(KnowledgeInfo knowledgeInfo);
	
	public void deleteKnowledgeInfo(Integer knowledgeInfoId,String operator) ;
	
	public List<KnowledgeInfoModel> selectModelListByKnowledge(Integer knowledgeId);
	
	public Integer saveKnowledgeInfoDetail(KnowledgeInfoDetail knowledgeInfoDetail);
	
	public void updateKnowledgeInfoDetail(KnowledgeInfoDetail knowledgeInfoDetail);
	
	public void deleteKnowledgeInfoDetail(Integer knowledgeInfoDetailId,String operator);
	
	public List<KnowledgeInfoDetail> selectKnowledgeInfoDetailListByKnowledgeInfoIds(Integer[] knowledgeInfoIds);
}
