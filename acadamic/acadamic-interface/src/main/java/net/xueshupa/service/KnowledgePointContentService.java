/**
 * 
 */
package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.KnowledgePointContent;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface KnowledgePointContentService {

	List<KnowledgePointContent> selectListByKnowledgePointCode(String knowledgePointCode);
}
