/**
 * 
 */
package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.ProgressCourse;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface ProgressCourseService {

	List<ProgressCourse> selectListByUserId(Integer userId);
}
