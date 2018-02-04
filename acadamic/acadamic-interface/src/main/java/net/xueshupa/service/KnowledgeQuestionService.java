/**
 * 
 */
package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.KnowledgeQuestion;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface KnowledgeQuestionService {

	List<KnowledgeQuestion> selectListByKnowledgePointContentIdList(List<Integer> knowledgePointContentIdList);
}
