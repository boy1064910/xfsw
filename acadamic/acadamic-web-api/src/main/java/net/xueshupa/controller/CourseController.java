package net.xueshupa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.filter.response.ResponseFilterRetention;

import net.xueshupa.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Resource(name="courseService")
	CourseService courseService;
	
	@ResponseFilterRetention(ignores = { "userId","state","lastUpdater","lastUpdateTime" })
	@GetMapping(value = "/list")
	public ResponseModel list(){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("courseList", courseService.selectAll());
		return new ResponseModel(resultMap);
	}
	
}
