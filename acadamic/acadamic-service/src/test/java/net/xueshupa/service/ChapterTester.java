package net.xueshupa.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xfsw.common.mapper.ICommonMapper;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-service-tester.xml","classpath*:spring/spring-service-*.xml"})
@Rollback(true)
public class ChapterTester {

	@Resource(name = "acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Test
	public void sendBizCode() {
		//更新课程购买数量
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("courseId", 14);
		String courseByCountSql = "UPDATE Course SET buyCount = (SELECT COUNT(courseId) FROM (SELECT userId,courseId FROM progresscourse GROUP BY userId,courseId) AS a WHERE a.courseId =#{courseId} GROUP BY a.courseId) WHERE id = #{courseId}";
		commonMapper.updateBySql(courseByCountSql, params);
	}
}
