package net.xueshupa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.NumberUtil;

import net.xueshupa.entity.DiffLevel;
import net.xueshupa.model.MiniDiffLevelListModel;

@Service("diffLevelService")
public class DiffLevelServiceImpl implements DiffLevelService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	public void deleteByCourseCode(String code,String operator){
		String sql = "DELETE FROM DiffLevel WHERE code LIKE CONCAT('#{code}','%')";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("code", code);
		commonMapper.deleteAndBak(sql, DiffLevel.class, params, operator);
	}
	
	public DiffLevel saveDiffLevel(String knowledgeCode,DiffLevel diffLevel){
		Integer orderIndex = (Integer) commonMapper.get("DiffLevel.selectMaxOrderIndexByCode",knowledgeCode);
		if(orderIndex==null){
			orderIndex = 1;
		}
		else{
			orderIndex++;
		}
		String code = knowledgeCode + NumberUtil.toZeroCode(orderIndex, 2);
		String sequence = "难度"+NumberUtil.ToCH(orderIndex);
		diffLevel.setCode(code);
		diffLevel.setSequence(sequence);
		diffLevel.setOrderIndex(orderIndex);
		commonMapper.insert("DiffLevel.insertDiffLevel", diffLevel);
		return diffLevel;
	}
	
	public List<DiffLevel> selectListByKnowledgeCode(String knowledgeCode){
		return commonMapper.selectList("DiffLevel.selectListByKnowledgeCode", knowledgeCode);
	}
	
	public List<MiniDiffLevelListModel> selectMiniListByKnowledgeCode(String knowledgeCode){
		return commonMapper.selectList("DiffLevel.selectMiniListByKnowledgeCode", knowledgeCode);
	}
}
