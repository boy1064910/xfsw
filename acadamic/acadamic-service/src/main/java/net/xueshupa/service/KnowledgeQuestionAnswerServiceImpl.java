package net.xueshupa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.KnowledgeQuestionAnswer;

@Service
public class KnowledgeQuestionAnswerServiceImpl implements KnowledgeQuestionAnswerService {

	@Resource(name = "acadamicCommonMapper")
	ICommonMapper commonMapper;

	@Override
	public Map<Integer, List<KnowledgeQuestionAnswer>> selectListByKnowledgeQuestionIds(List<Integer> knowledgeQuestionIdList) {
		List<KnowledgeQuestionAnswer> answerList = commonMapper.selectList("KnowledgeQuestionAnswer.selectListByKnowledgeQuestionIds",knowledgeQuestionIdList);
		Map<Integer,List<KnowledgeQuestionAnswer>> answerMap = new HashMap<Integer,List<KnowledgeQuestionAnswer>>();
		answerList.stream().forEach(x->{
			if(!answerMap.containsKey(x.getKnowledgeQuestionId())) {
				answerMap.put(x.getKnowledgeQuestionId(), new ArrayList<KnowledgeQuestionAnswer>());
			}
			answerMap.get(x.getKnowledgeQuestionId()).add(x);
		});
		return answerMap;
	}

}
