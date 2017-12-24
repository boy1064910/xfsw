/**
 * 
 */
package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.ProgressChapter;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface ProgressChapterService {

	List<ProgressChapter> selectListByInfo(Integer userId,Integer courseId);
	
	ProgressChapter getByInfo(Integer userId,Integer chapterId);
}
