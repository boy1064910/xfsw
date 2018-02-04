/**
 * 
 */
package net.xueshupa.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.KnowledgePointContent;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Service
public class KnowledgePointContentServiceImpl implements KnowledgePointContentService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public List<KnowledgePointContent> selectListByKnowledgePointCode(String knowledgePointCode) {
		return commonMapper.selectList("KnowledgePointContent.selectListByKnowledgePointCode", knowledgePointCode);
	}

}
