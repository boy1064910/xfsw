/**
 * 
 */
package net.xueshupa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.KnowledgePoint;
import net.xueshupa.entity.KnowledgePointContent;
import net.xueshupa.entity.KnowledgeQuestion;
import net.xueshupa.entity.KnowledgeQuestionAnswer;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@Service
public class KnowledgePointServiceImpl implements KnowledgePointService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Autowired
	KnowledgeQuestionAnswerService knowledgeQuestionAnswerService;
	
	@Override
	public KnowledgePoint getKnowledgePointInfo(Integer id){
		KnowledgePoint knowledgePoint = commonMapper.get(KnowledgePoint.class, id);
		
		//查询content数据
		String sql = "SELECT * FROM KnowledgePointContent WHERE knowledgePointId = #{knowledgePointId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("knowledgePointId", id);
		List<KnowledgePointContent> contentList = commonMapper.selectListBySql(sql, params, KnowledgePointContent.class);
		if(!CollectionUtils.isEmpty(contentList)){
			knowledgePoint.setContentList(contentList);
			//查询 content id list
			List<Integer> knowledgePointContentIdList = contentList.stream().map(x->x.getId()).collect(Collectors.toList());
			
			//设置question和content的主从数据
			List<KnowledgeQuestion> questionList = commonMapper.selectList("KnowledgeQuestion.selectListByKnowledgePointContentIdList", knowledgePointContentIdList);
			List<Integer> questionIdList = questionList.stream().map(x->x.getId()).collect(Collectors.toList());
			//获取所有问题的答案列表并分组
			Map<Integer,List<KnowledgeQuestionAnswer>> knowledgeQuestionAnswerMap = knowledgeQuestionAnswerService.selectListByKnowledgeQuestionIds(questionIdList);
			Map<Integer,List<KnowledgeQuestion>> questionListMap = new HashMap<Integer,List<KnowledgeQuestion>>();
			for(KnowledgeQuestion c:questionList) {
				//为问题设置答案
				c.setAnswerList(knowledgeQuestionAnswerMap.get(c.getId()));
				if(questionListMap.containsKey(c.getKnowledgePointContentId())) {
					questionListMap.get(c.getKnowledgePointContentId()).add(c);
				}
				else {
					List<KnowledgeQuestion> klist = new ArrayList<KnowledgeQuestion>();
					klist.add(c);
					questionListMap.put(c.getKnowledgePointContentId(), klist);
				}
			}
			contentList.stream().forEach(x->{
				if(questionListMap.containsKey(x.getId())) {
					x.setQuestionList(questionListMap.get(x.getId()));
				}
			});
			
			//设置content和point的主从关系
			Map<Integer,List<KnowledgePointContent>> contentListMap = new HashMap<Integer,List<KnowledgePointContent>>();
			for(KnowledgePointContent c:contentList) {
				if(contentListMap.containsKey(c.getKnowledgePointId())) {
					contentListMap.get(c.getKnowledgePointId()).add(c);
				}
				else {
					List<KnowledgePointContent> clist = new ArrayList<KnowledgePointContent>();
					clist.add(c);
					contentListMap.put(c.getKnowledgePointId(), clist);
				}
			}
		}
		return knowledgePoint;
	}

	@Override
	public List<KnowledgePoint> selectListByKnowledgeCode(String knowledgeCode) {
		return commonMapper.selectList("KnowledgePoint.selectListByKnowledgeCode", knowledgeCode);
	}
}
