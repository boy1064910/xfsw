package net.xueshupa.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;

import net.xueshupa.entity.Chapter;
import net.xueshupa.service.ChapterService;

@Controller
@RequestMapping("/manager/course/chapter")
public class ChapterController {

	@Resource(name="chapterService")
	ChapterService chapterService;
	
	@RequestMapping("/index")
	public void index(Model model,String code,Integer courseId){
		model.addAttribute("courseCode", code);
		model.addAttribute("courseId", courseId);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResponseModel list(String courseCode){
		return new ResponseModel(chapterService.selectListByCourseCode(courseCode));
	}
	
	@RequestMapping("/saveChapter")
	@ResponseBody
	public ResponseModel saveChapter(Chapter chapter,String courseCode){
		chapter.setLastUpdater(ThreadUserInfoManager.getAccount());
		return new ResponseModel(chapterService.saveChapter(chapter, courseCode));
	}
}
