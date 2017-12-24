/**
 * 
 */
package net.xueshupa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.ProgressChapter;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Service
public class ProgressChapterServiceImpl implements ProgressChapterService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public List<ProgressChapter> selectListByInfo(Integer userId,Integer courseId) {
		String sql = "SELECT * FROM ProgressChapter WHERE userId = #{userId} AND courseId = #{courseId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId",userId);
		params.put("courseId",courseId);
		return commonMapper.selectListBySql(sql, params, ProgressChapter.class);
	}

	@Override
	public ProgressChapter getByInfo(Integer userId,Integer chapterId){
		ProgressChapter entity = new ProgressChapter();
		entity.setUserId(userId);
		entity.setChapterId(chapterId);
		return commonMapper.get(ProgressChapter.class, entity);
	}
}
