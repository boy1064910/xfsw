package net.xueshupa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.filter.response.ResponseFilterRetention;
import com.xfsw.common.thread.ThreadUserInfoManager;

import net.xueshupa.entity.Chapter;
import net.xueshupa.entity.Course;
import net.xueshupa.entity.ProgressChapter;
import net.xueshupa.entity.ProgressCourse;
import net.xueshupa.service.ChapterService;
import net.xueshupa.service.CourseService;
import net.xueshupa.service.ProgressChapterService;
import net.xueshupa.service.ProgressCourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Resource(name="courseService")
	CourseService courseService;
	
	@Resource(name="chapterService")
	ChapterService chapterService;
	
	@Resource
	ProgressCourseService progressCourseService;
	@Resource
	ProgressChapterService progressChapterService;
	
	@ResponseFilterRetention(ignores = { "userId","code","state","lastUpdater","lastUpdateTime" })
	@GetMapping(value = "/list")
	public ResponseModel list(){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Course> courseList = courseService.selectAll();
		List<ProgressCourse> progressCourseList = progressCourseService.selectListByUserId(ThreadUserInfoManager.getUserId());
		if(!CollectionUtils.isEmpty(progressCourseList)){
			Stream<ProgressCourse> progressCourseStream = progressCourseList.stream();
			Map<Integer,Integer> courseChapterMap = progressCourseStream.collect(Collectors.toMap(ProgressCourse::getCourseId, ProgressCourse::getChapterId));
			Set<Integer> courseIdSet = progressCourseStream.map(x->x.getCourseId()).collect(Collectors.toSet());
			List<Integer> chapterIdList = progressCourseStream.map(x->x.getChapterId()).collect(Collectors.toList());
			List<Chapter> chapterList = chapterService.selectListByIdList(chapterIdList);
			//课程章节信息
			Map<Integer,Chapter> chapterMap = chapterList.stream().collect(Collectors.toMap(Chapter::getId,Function.identity()));
			
			//未购买的课程信息
			List<Course> unBuyCourseList = courseList.stream().filter(x->!courseIdSet.contains(x.getId())).collect(Collectors.toList());
			//已购买的课程信息
			List<BuyedCourseModel> buyedCourseList = courseList.stream().filter(x->courseIdSet.contains(x.getId())).map(x->{
				BuyedCourseModel buyedCourseModel = new BuyedCourseModel();
				buyedCourseModel.setId(x.getId());
				buyedCourseModel.setName(x.getName());
				buyedCourseModel.setBuyCount(x.getBuyCount());
				Integer chapterId = courseChapterMap.get(x.getId());
				buyedCourseModel.setChapterId(chapterId);
				if(chapterId!=null&&chapterMap.containsKey(chapterId)){
					Chapter chapter = chapterMap.get(chapterId);
					buyedCourseModel.setChapterName(chapter.getName());
					buyedCourseModel.setChapterOrderIndex(chapter.getOrderIndex());
				}
				return buyedCourseModel;
			}).collect(Collectors.toList());
			resultMap.put("unBuyCourseList", unBuyCourseList);
			resultMap.put("buyedCourseList", buyedCourseList);
		}
		else{
			resultMap.put("unBuyCourseList", courseList);
		}
		
		return new ResponseModel(resultMap);
	}
	
	@ResponseFilterRetention(ignores = { "userId","code","lastUpdater","lastUpdateTime" })
	@GetMapping(value = "/chapter/list")
	public ResponseModel chapterList(Integer courseId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Course course = courseService.getById(courseId);
		resultMap.put("course", course);
		
		List<Chapter> chapterList = chapterService.selectListByCourseId(courseId);
		if(CollectionUtils.isEmpty(chapterList)) {
			return new ResponseModel(resultMap);
		}
		
		List<ChapterModel> chapterModelList = null;
		List<ProgressChapter> progressChapterList = progressChapterService.selectListByInfo(ThreadUserInfoManager.getUserId(), courseId);
		if(!CollectionUtils.isEmpty(progressChapterList)) {
			Set<Integer> buyedChapterIdSet = progressChapterList.stream().map(x->x.getChapterId()).collect(Collectors.toSet());
			chapterModelList = chapterList.stream().map(x->{
				ChapterModel chapterModel = new ChapterModel(x);
				if(buyedChapterIdSet.contains(x.getId())) {
					chapterModel.setBuyed(true);
				}
				return chapterModel;
			}).collect(Collectors.toList());
		}
		else {
			 chapterModelList = chapterList.stream().map(x->{return new ChapterModel(x);}).collect(Collectors.toList());
		}
		
		resultMap.put("chapterList", chapterModelList);
		return new ResponseModel(resultMap);
	}
}

class BuyedCourseModel{
	private Integer id;
	private String name;
	private Integer buyCount;
	private Integer chapterId;
	private String chapterName;
	private Integer chapterOrderIndex;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public Integer getChapterOrderIndex() {
		return chapterOrderIndex;
	}
	public void setChapterOrderIndex(Integer chapterOrderIndex) {
		this.chapterOrderIndex = chapterOrderIndex;
	}
}

class ChapterModel{
	Chapter chapter;
	boolean isBuyed;
	
	public ChapterModel(Chapter chapter) {
		super();
		this.chapter = chapter;
		this.isBuyed = false;
	}
	
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public boolean isBuyed() {
		return isBuyed;
	}
	public void setBuyed(boolean isBuyed) {
		this.isBuyed = isBuyed;
	}
}

