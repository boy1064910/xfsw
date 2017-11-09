package net.xueshupa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.KnowledgeInfo;
import net.xueshupa.model.ExerciseModel;
import net.xueshupa.model.KnowledgeInfoModel;

@Service("knowledgeInfoService")
public class KnowledgeInfoService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource
	ExerciseService exerciseService;
	
	public Integer saveKnowledgeInfo(KnowledgeInfo knowledgeInfo){
		String sql = "SELECT MAX(orderIndex) FROM KnowledgeInfo WHERE knowledgeId = #{knowledgeId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("knowledgeId", knowledgeInfo.getKnowledgeId());
		Integer orderIndex = commonMapper.getBySql(sql, params, Integer.class);
		if(orderIndex==null){
			orderIndex = 1;
		}
		else{
			orderIndex++;
		}
		knowledgeInfo.setOrderIndex(orderIndex);
		commonMapper.insert(KnowledgeInfo.class, knowledgeInfo);
		return knowledgeInfo.getId();
	}
	
	public List<KnowledgeInfoModel> selectModelListByKnowledge(Integer knowledgeId){
		String sql = "SELECT * FROM KnowledgeInfo WHERE knowledgeId = #{knowledgeId} ORDER BY orderIndex ASC";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("knowledgeId", knowledgeId);
		List<KnowledgeInfo> knowledgeInfoList = commonMapper.selectListBySql(sql, params, KnowledgeInfo.class);
		if(CollectionUtils.isEmpty(knowledgeInfoList)) {
			return null;
		}
		
		Integer[] knowledgeInfoIds = new Integer[knowledgeInfoList.size()];
		int index = 0;
		for(KnowledgeInfo knowledgeInfo:knowledgeInfoList) {
			knowledgeInfoIds[index] = knowledgeInfo.getId();
			index++;
		}
		//查询练习题数组数据
		List<ExerciseModel> exerciseModelList = exerciseService.selectModelListByKnowledgeInfoIds(knowledgeInfoIds);
		Map<Integer,List<ExerciseModel>> exerciseModelMap = new HashMap<Integer,List<ExerciseModel>>();
		if(!CollectionUtils.isEmpty(exerciseModelList)) {
			for(ExerciseModel exerciseModel:exerciseModelList) {
				if(!exerciseModelMap.containsKey(exerciseModel.getKnowledgeInfoId())) {
					exerciseModelMap.put(exerciseModel.getKnowledgeInfoId(), new ArrayList<ExerciseModel>());
				}
				exerciseModelMap.get(exerciseModel.getKnowledgeInfoId()).add(exerciseModel);
			}
		}
		
		List<KnowledgeInfoModel> knowledgeInfoModelList = new ArrayList<KnowledgeInfoModel>(knowledgeInfoList.size());
		for(KnowledgeInfo knowledgeInfo:knowledgeInfoList) {
			KnowledgeInfoModel knowledgeInfoModel = new KnowledgeInfoModel(knowledgeInfo);
			if(exerciseModelMap.containsKey(knowledgeInfoModel.getId())) {
				knowledgeInfoModel.setExerciseList(exerciseModelMap.get(knowledgeInfoModel.getId()));
			}
			knowledgeInfoModelList.add(knowledgeInfoModel);
		}
		return knowledgeInfoModelList;
	}
}
