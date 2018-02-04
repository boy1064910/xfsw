/**
 * 
 */
package net.xueshupa.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.KnowledgeQuestion;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Service
public class KnowledgeQuestionServiceImpl implements KnowledgeQuestionService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	@Override
	public List<KnowledgeQuestion> selectListByKnowledgePointContentIdList(List<Integer> knowledgePointContentIdList) {
		return commonMapper.selectList("KnowledgeQuestion.selectListByKnowledgePointContentIdList", knowledgePointContentIdList);
	}

}
