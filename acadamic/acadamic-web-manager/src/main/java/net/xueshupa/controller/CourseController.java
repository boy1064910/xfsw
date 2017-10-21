package net.xueshupa.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping("/saveCourse")
	@ResponseBody
	public ResponseModel saveCourse(Course course){
		UserSessionModel user = ThreadUserInfoManager.getUserInfo();
		course.setLastUpdater(user.getAccount());
		course.setUserId(user.getId());
		return new ResponseModel();
	}
}
