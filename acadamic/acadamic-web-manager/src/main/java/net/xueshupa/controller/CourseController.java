package net.xueshupa.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.session.model.UserSessionModel;

import net.xueshupa.entity.Course;
import net.xueshupa.service.CourseService;

@Controller
@RequestMapping("/manager/course")
public class CourseController {

	@Resource(name="courseService")
	CourseService courseService;
	
	@RequestMapping("/index")
	public void index(Model model){
		
	}
	
	@RequestMapping(value = "/saveCourse",method = RequestMethod.POST)
	@ResponseBody
	public ResponseModel saveCourse(Course course){
		UserSessionModel user = ThreadUserInfoManager.getUserInfo();
		course.setLastUpdater(user.getAccount());
		course.setLastUpdateTime(new Date());
		course.setUserId(user.getId());
		course.setOriginPrice(course.getPrice());//目前无优惠，课程原价与销售价格一致
		course = courseService.saveCourse(course);
		return new ResponseModel(course);
	}
	
	@RequestMapping("/pageInfo")
	@ResponseBody
	public DataTableResponseModel pageInfo(DataTablePageInfo pageInfo){
		return courseService.selectPageInfo(pageInfo);
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public ResponseModel getById(Integer id){
		return new ResponseModel(courseService.getById(id));
	}
	
	@RequestMapping("/deleteCourse")
	@ResponseBody
	public ResponseModel deleteCourse(Integer id){
		courseService.deleteById(id,ThreadUserInfoManager.getAccount());
		return new ResponseModel();
	}
	
}
