package net.xueshupa.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.KnowledgeInfo;

@Service("knowledgeInfoService")
public class KnowledgeInfoService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
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
}
