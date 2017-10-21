package net.xueshupa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mini/course")
public class MiniCourseController {

//	@Resource(name="courseService")
//	CourseService courseService;
//	
//	@Resource(name="chapterService")
//	ChapterService chapterService;
//	
//	@Resource(name="knowledgeService")
//	KnowledgeService knowledgeService;
//	
//	@Resource(name="diffLevelService")
//	DiffLevelService diffLevelService;
//	
//	@Resource(name="exerciseService")
//	ExerciseService exerciseService;
//	
//	@RequestMapping("/list")
//	@ResponseBody
//	public HttpResultModel list(){
//		return new HttpResultModel(courseService.selectMiniCourseList());
//	}
//	
//	@RequestMapping("/chapterList")
//	@ResponseBody
//	public HttpResultModel chapterList(String courseCode){
//		Map<String,Object> result = new HashMap<String,Object>();
//		result.put("course", courseService.getByCode(courseCode));
//		result.put("chapterList", chapterService.selectMiniChapterList(courseCode));
//		return new HttpResultModel(result);
//	}
//	
//	@RequestMapping("/chapter/knowledgeList")
//	@ResponseBody
//	public HttpResultModel knowledgeList(String chapterCode){
//		Map<String,Object> result = new HashMap<String,Object>();
//		result.put("chapter", courseService.getByCode(chapterCode));
//		result.put("knowledgeList", knowledgeService.selectMiniKnowledgeList(chapterCode));
//		return new HttpResultModel(result);
//	}
//	
//	@RequestMapping("/chapter/knowledgeInfo")
//	@ResponseBody
//	public HttpResultModel knowledgeInfo(Integer knowledgeId){
//		Map<String,Object> result = new HashMap<String,Object>();
//		MiniKnowledgeInfoModel knowledge = knowledgeService.getMiniInfoById(knowledgeId);
//		result.put("knowledge", knowledge);
//		result.put("diffLevelList", diffLevelService.selectMiniListByKnowledgeCode(knowledge.getCode()));
//		result.put("exerciseList", exerciseService.selectMiniListByDiffLevelCode(knowledge.getCode()));
//		return new HttpResultModel(result);
//	}
}
