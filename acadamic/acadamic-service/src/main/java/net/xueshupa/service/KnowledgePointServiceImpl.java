/**
 * 
 */
package net.xueshupa.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.KnowledgePoint;
import net.xueshupa.entity.KnowledgePointContent;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@Service
public class KnowledgePointServiceImpl implements KnowledgePointService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public List<KnowledgePoint> selectListByKnowledgeId(Integer knowledgeId) {
		String sql = "SELECT * FROM KnowledgePoint WHERE knowledgeId = #{knowledgeId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("knowledgeId",knowledgeId);
		List<KnowledgePoint> list = commonMapper.selectListBySql(sql, params, KnowledgePoint.class);
		
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<Integer> knowledgePointIdList = list.stream().map(x->x.getId()).collect(Collectors.toList());
		List<KnowledgePointContent> contentList = commonMapper.selectList("KnowledgePointContent.selectListByKnowledgePointIds", knowledgePointIdList);
		Map<Integer,List<KnowledgePointContent>> contentListMap = new HashMap<Integer,List<KnowledgePointContent>>();
		for(KnowledgePointContent c:contentList) {
			if(contentListMap.containsKey(c.getKnowledgePointId())) {
				contentListMap.get(c.getKnowledgePointId()).add(c);
			}
			else {
				contentListMap.put(c.getKnowledgePointId(), Arrays.asList(c));
			}
		}
		
		list.stream().forEach(x->{
			if(contentListMap.containsKey(x.getId())) {
				x.setContentList(contentListMap.get(x.getId()));
			}
		});
		return list;
	}

}
