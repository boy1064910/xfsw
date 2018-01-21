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

import net.xueshupa.entity.ProgressCourse;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Service
public class ProgressCourseServiceImpl implements ProgressCourseService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public List<ProgressCourse> selectListByUserId(Integer userId) {
		String sql = "SELECT * FROM ProgressCourse WHERE userId = #{userId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId",userId);
		return commonMapper.selectListBySql(sql, params, ProgressCourse.class);
	}

	@Override
	public List<ProgressCourse> selectLearningList(Integer userId) {
		String sql = "SELECT * FROM ProgressCourse WHERE userId = #{userId} GROUP BY courseId ORDER BY createTime ASC";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId",userId);
		return commonMapper.selectListBySql(sql, params, ProgressCourse.class);
	}
	
	@Override
	public ProgressCourse getByInfo(Integer userId,Integer chapterId){
		ProgressCourse entity = new ProgressCourse();
		entity.setUserId(userId);
		entity.setChapterId(chapterId);
		return commonMapper.get(ProgressCourse.class, entity);
	}
	
}
