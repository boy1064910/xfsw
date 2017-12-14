package net.xueshupa.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.NumberUtil;

import net.xueshupa.entity.Course;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="chapterService")
	ChapterService chapterService;
	
	@Resource(name="knowledgeService")
	KnowledgeService knowledgeService;
	
	@Resource(name="diffLevelService")
	DiffLevelService diffLevelService;

	@Resource(name="exerciseService")
	ExerciseService exerciseService;
	
	public Course saveCourse(Course course){
		//TODO 后期需要考虑编号的同步锁，前期先使用spring的线程安全解决，后期分布式采用redis分布式锁解决
		if(course.getId()!=null){
			String sql = "UPDATE Course SET name = #{name},price = #{price},lastUpdater=#{lastUpdater},lastUpdateTime = NOW() WHERE id = #{id}";
			commonMapper.updateBySql(sql, course);
			return commonMapper.get(Course.class, course.getId());
		}
		else{
			commonMapper.insert(Course.class, course);
			Integer id = course.getId();
			String code = NumberUtil.toZeroCode(id, 6);
			course.setCode(code);
			String sql = "UPDATE Course SET code = #{code} WHERE id = #{id}";
			commonMapper.updateBySql(sql, course);
			return course;
		}
	}
	
	public void deleteById(Integer id,String operator){
		commonMapper.deleteAndBak(Course.class, id, operator);
	}
	
	public Course getByCode(String code){
		return commonMapper.get(Course.class, "code", code);
	}
	
	public DataTableResponseModel selectPageInfo(DataTablePageInfo pageInfo){
		String countSql = "SELECT COUNT(id) FROM Course";
		String dataSql = "SELECT * FROM Course";
		return commonMapper.selectPageBySql(countSql, dataSql, pageInfo, null);
	}
	
	public Course getById(Integer id) {
		return commonMapper.get(Course.class, id);
	}
	
	public List<Course> selectAll(){
		return commonMapper.selectAll(Course.class);
	}
}
