package net.xueshupa.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;
import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.Course;
import net.xueshupa.model.MiniCourseListModel;

@Service("courseService")
public class CourseService {

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
//		String code = increaseCacheService.generateCourseCode();
		String code = "";
		course.setCode(code);
		if(course.getId()!=null){
			commonMapper.insert("Course.insertCourse", course);
			return (Course) commonMapper.get(Course.class, course.getId());
		}
		else{
			commonMapper.insert("Course.updateCourse", course);
			return course;
		}
	}
	
	@Transactional
	public void deleteById(String code,String operator){
		exerciseService.deleteByCourseCode(code, operator);
		diffLevelService.deleteByCourseCode(code, operator);
		knowledgeService.deleteByCourseCode(code, operator);
		chapterService.deleteByCourseCode(code, operator);
		Course course = new Course(code);
		commonMapper.deleteAndBak(Course.class, course, operator);
	}
	
	public Course getByCode(String code){
		return commonMapper.get(Course.class, "code", code);
	}
	
	/**
	 * 前端接口
	 * @return	
	 */
	public List<MiniCourseListModel> selectMiniCourseList(){
		return commonMapper.selectList("Course.selectMiniCourseList");
	}
	
	public DataTableResponseModel selectPageInfo(DataTablePageInfo pageInfo){
		String countSql = "SELECT COUNT(id) FROM Course";
		String dataSql = "SELECT * FROM Course";
		return commonMapper.selectPageBySql(countSql, dataSql, pageInfo, null);
	}
}
