package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.Knowledge;
import net.xueshupa.model.KnowledgeModel;
import net.xueshupa.model.MiniKnowledgeInfoModel;
import net.xueshupa.model.MiniKnowledgeListModel;

public interface KnowledgeService {

	public void deleteByCourseCode(String code,String operator);
	
	public List<Knowledge> selectListByChapterCode(String chapterCode);
	
	public Knowledge saveKnowledge(String chapterCode,Knowledge knowledge);
	
	public KnowledgeModel getById(Integer id);
	
	/**
	 * 删除知识点信息
	 * @param id
	 * @param operator
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public void deleteById(Integer id,String operator);
	
	/**
	 * 前端接口
	 * @return	
	 */
	public List<MiniKnowledgeListModel> selectMiniKnowledgeList(String chapterCode);
	
	/**
	 * 前端接口
	 * @return	
	 */
	public MiniKnowledgeInfoModel getMiniInfoById(Integer id);
}
