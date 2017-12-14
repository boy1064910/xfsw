package net.xueshupa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.filter.response.ResponseFilterRetention;

import net.xueshupa.entity.Course;
import net.xueshupa.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Resource(name="courseService")
	CourseService courseService;
	
	@ResponseFilterRetention(ignores = { "userId","state","lastUpdater","lastUpdateTime" })
	@GetMapping(value = "/list")
	public ResponseModel list(){
		List<Course> courseList = courseService.selectAll();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("courseList", courseService.selectAll());
		return new ResponseModel(resultMap);
	}
	
	@GetMapping(value = "/chapter/list")
	public ResponseModel chapterList(Integer courseId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("courseList", courseService.selectAll());
		return new ResponseModel(resultMap);
	}
}

class CourseModel{
	private Integer id;
	private String code;
	private String name;
	private Double originPrice;
	private Double price;
	private Integer buyCount;
	boolean userBuyed;//用户是否已经购买
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(Double originPrice) {
		this.originPrice = originPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
}

class ChapterModel{
	Integer id;
	String code;
	String name;
	String info;
	Double originPrice;
	Double price;
	Integer buyCount;
	boolean userBuyed;//用户是否已经购买
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Double getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(Double originPrice) {
		this.originPrice = originPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public boolean isUserBuyed() {
		return userBuyed;
	}
	public void setUserBuyed(boolean userBuyed) {
		this.userBuyed = userBuyed;
	}
}