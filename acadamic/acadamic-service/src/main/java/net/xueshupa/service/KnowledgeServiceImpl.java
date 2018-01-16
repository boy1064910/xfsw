package net.xueshupa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.NumberUtil;

import net.xueshupa.entity.Knowledge;
import net.xueshupa.entity.KnowledgePoint;
import net.xueshupa.model.KnowledgeModel;
import net.xueshupa.model.MiniKnowledgeInfoModel;
import net.xueshupa.model.MiniKnowledgeListModel;

@Service("knowledgeService")
public class KnowledgeServiceImpl implements KnowledgeService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="diffLevelService")
	DiffLevelService diffLevelService;
	
	@Resource(name="knowledgeInfoService")
	KnowledgeInfoService knowledgeInfoService;
	
	public void deleteByCourseCode(String code,String operator){
		String sql = "DELETE FROM Knowledge WHERE code LIKE CONCAT('#{code}','%')";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("code", code);
		commonMapper.deleteAndBak(sql, Knowledge.class, params, operator);
	}
	
	public List<Knowledge> selectListByChapterCode(String chapterCode){
		List<Knowledge> knowledgeList = commonMapper.selectList("Knowledge.selectListByChapterCode", chapterCode);
		List<Integer> knowledgeIdList = knowledgeList.stream().map(x->x.getId()).collect(Collectors.toList());
		if(!CollectionUtils.isEmpty(knowledgeIdList)){
			List<KnowledgePoint> knowledgePointList = commonMapper.selectList("KnowledgePoint.selectListByKnowledgeIdList",knowledgeIdList);
			Map<Integer,List<KnowledgePoint>> knowledgePointMap = new HashMap<Integer,List<KnowledgePoint>>();
			for(KnowledgePoint c:knowledgePointList) {
				//为问题设置答案
				if(!knowledgePointMap.containsKey(c.getKnowledgeId())) {
					knowledgePointMap.put(c.getKnowledgeId(), new ArrayList<KnowledgePoint>());
				}
				knowledgePointMap.get(c.getKnowledgeId()).add(c);
			}
			
			knowledgeList.stream().forEach(x->{
				if(knowledgePointMap.containsKey(x.getId())) {
					x.setKnowledgePointList(knowledgePointMap.get(x.getId()));
				}
			});
		}
		return knowledgeList;
	}
	
	public Knowledge saveKnowledge(String chapterCode,Knowledge knowledge){
		if(knowledge.getId()!=null){
			commonMapper.insert("Knowledge.updateKnowledge", knowledge);
			return (Knowledge) commonMapper.get(Knowledge.class, knowledge.getId());
		}
		else{
			Integer orderIndex = (Integer) commonMapper.get("Knowledge.selectMaxOrderIndexByCode",chapterCode);
			if(orderIndex==null){
				orderIndex = 1;
			}
			else{
				orderIndex++;
			}
			String code = chapterCode + NumberUtil.toZeroCode(orderIndex, 3);
			knowledge.setCode(code);
			knowledge.setOrderIndex(orderIndex);
			commonMapper.insert("Knowledge.insertKnowledge", knowledge);
			return knowledge;
		}
	}
	
	public KnowledgeModel getById(Integer id){
		Knowledge knowledge = commonMapper.get(Knowledge.class, id);
		KnowledgeModel knowledgeModel = new KnowledgeModel(knowledge);
		knowledgeModel.setKnowledgeInfoList(knowledgeInfoService.selectModelListByKnowledge(knowledge.getId()));
		return knowledgeModel;
	}
	
	/**
	 * 删除知识点信息
	 * @param id
	 * @param operator
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@Transactional(transactionManager="acadamicTxManager")
	public void deleteById(Integer id,String operator){
		commonMapper.deleteAndBak(Knowledge.class, id, operator);
		//TODO 删除相关联的数据
	}
	
	/**
	 * 前端接口
	 * @return	
	 */
	public List<MiniKnowledgeListModel> selectMiniKnowledgeList(String chapterCode){
		return commonMapper.selectList("Knowledge.selectMiniKnowledgeList",chapterCode);
	}
	
	/**
	 * 前端接口
	 * @return	
	 */
	public MiniKnowledgeInfoModel getMiniInfoById(Integer id){
		return (MiniKnowledgeInfoModel) commonMapper.get("Knowledge.getMiniInfoById", id);
	}
}
