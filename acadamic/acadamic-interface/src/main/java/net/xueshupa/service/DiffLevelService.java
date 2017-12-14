package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.DiffLevel;
import net.xueshupa.model.MiniDiffLevelListModel;

public interface DiffLevelService {

	public void deleteByCourseCode(String code,String operator);
	
	public DiffLevel saveDiffLevel(String knowledgeCode,DiffLevel diffLevel);
	
	public List<DiffLevel> selectListByKnowledgeCode(String knowledgeCode);
	
	public List<MiniDiffLevelListModel> selectMiniListByKnowledgeCode(String knowledgeCode);
}
