package net.xueshupa.service;

import java.util.List;

import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;

import net.xueshupa.entity.Course;

public interface CourseService {

	public Course saveCourse(Course course);
	
	public void deleteById(Integer id,String operator);
	
	public Course getByCode(String code);
	
	public DataTableResponseModel selectPageInfo(DataTablePageInfo pageInfo);
	
	public Course getById(Integer id) ;
	
	public List<Course> selectAll();
}
