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
import net.xueshupa.entity.KnowledgePointContentAnswer;
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
		
		//查询 content id list
		List<Integer> knowledgePointContentIdList = contentList.stream().map(x->x.getId()).collect(Collectors.toList());
		
		//bind the relation of content answer and content 
		List<KnowledgePointContentAnswer> contentAnswerList =  commonMapper.selectList("KnowledgePointContentAnswer.selectListByKnowledgePointContentIds", knowledgePointContentIdList);
		Map<Integer,List<KnowledgePointContentAnswer>> contentAnswerMap = new HashMap<Integer,List<KnowledgePointContentAnswer>>();
		for(KnowledgePointContentAnswer c:contentAnswerList) {
			if(contentAnswerMap.containsKey(c.getKnowledgePointContentId())) {
				contentAnswerMap.get(c.getKnowledgePointContentId()).add(c);
			}
			else {
				List<KnowledgePointContentAnswer> klist = new ArrayList<KnowledgePointContentAnswer>();
				klist.add(c);
				contentAnswerMap.put(c.getKnowledgePointContentId(), klist);
			}
		}
		contentList.stream().forEach(x->{
			if(contentAnswerMap.containsKey(x.getId())) {
				x.setContentAnswerList(contentAnswerMap.get(x.getId()));
			}
		});
		
		//设置question和content的主从数据
		List<KnowledgeQuestion> questionList = commonMapper.selectList("KnowledgeQuestion.selectListByKnowledgePointContentIds", knowledgePointContentIdList);
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
		//讲content list从map中取出，赋值到point中
		list.stream().forEach(x->{
			if(contentListMap.containsKey(x.getId())) {
				x.setContentList(contentListMap.get(x.getId()));
			}
		});
		return list;
	}

}
