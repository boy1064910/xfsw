/**
 * 
 */
package net.xueshupa.service;

import java.util.List;
import java.util.Map;

import net.xueshupa.entity.KnowledgeQuestionAnswer;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public interface KnowledgeQuestionAnswerService {

	/**
	 * 获取知识点问题的答案列表，按照知识点问题ID分组
	 * @param knowledgeQuestionIdList
	 * @return
	 */
	Map<Integer,List<KnowledgeQuestionAnswer>> selectListByKnowledgeQuestionIds(List<Integer> knowledgeQuestionIdList);
}
