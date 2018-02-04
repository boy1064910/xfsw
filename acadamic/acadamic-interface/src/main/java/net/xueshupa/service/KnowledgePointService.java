/**
 * 
 */
package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.KnowledgePoint;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public interface KnowledgePointService {

	KnowledgePoint getKnowledgePointInfo(Integer id);
	
	List<KnowledgePoint> selectListByKnowledgeCode(String knowledgeCode);
}
