package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.Chapter;

public interface ChapterService {

	public void deleteByCourseCode(String code,String operator);
	
	public Chapter saveChapter(Chapter chapter,String courseCode);
	
	public List<Chapter> selectListByCourseId(Integer courseId);
	
	public List<Chapter> selectListByCourseCode(String courseCode);
	
	public Chapter getByCode(String code);
	
	public Chapter getById(Integer id) ;
	
	public void deleteChapter(Integer id,String operator);
	
	public List<Chapter> selectListByIdList(List<Integer> chapterIdList);
}
