/**
 * 
 */
package net.xueshupa.component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.mq.consts.QueueDestination;
import com.xfsw.common.mq.model.OrderReceiverModel;

import net.xueshupa.entity.Chapter;
import net.xueshupa.entity.ProgressChapter;
import net.xueshupa.entity.ProgressCourse;

/**
 * 章节购买成功消息通知
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Component
public class ChapterOrderReceiver extends MessageListenerAdapter {

	@Resource(name = "acadamicCommonMapper")
	ICommonMapper commonMapper;

	/**
	 * 章节购买成功消息通知
	 */
	@Transactional(value = "acadamicTxManager")
	@JmsListener(destination = QueueDestination.ACADAMIC_CHAPTER_ORDER, concurrency = "1", containerFactory = "jmsListenerContainerFactory")
	public void onMessage(Message message, Session session) throws JMSException {
		OrderReceiverModel model = (OrderReceiverModel) getMessageConverter().fromMessage(message);
		Integer userId = model.getUserId();
		Integer chapterId = Integer.valueOf(model.getBizExtra().toString());
		Chapter chapter = commonMapper.get(Chapter.class, chapterId);
		
		Integer courseId = chapter.getCourseId();
		ProgressCourse progrssCourse = new ProgressCourse();
		progrssCourse.setUserId(userId);
		progrssCourse.setCourseId(courseId);
		progrssCourse.setChapterId(chapterId);
		commonMapper.insert(ProgressCourse.class, progrssCourse);
		
		String sql = "SELECT COUNT(id) FROM ProgressChapter WHERE chapterId = #{chapterId} AND userId = #{userId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("chapterId", chapterId);
		params.put("userId", userId);
		boolean isExsit = commonMapper.checkBySql(sql,params);
		if(!isExsit){
			ProgressChapter progressChapter = new ProgressChapter();
			progressChapter.setUserId(userId);
			progressChapter.setChapterId(chapterId);
			progressChapter.setCourseId(courseId);
			progressChapter.setBuyTime(new Date());
			commonMapper.insert(ProgressChapter.class, progressChapter);
		}
		
		//更新课程购买数量
		String courseByCountSql = "UPDATE Course SET buyCount = (SELECT COUNT(courseId) FROM (SELECT userId,courseId FROM progresscourse GROUP BY userId,courseId) AS a WHERE a.courseId =#{courseId} GROUP BY a.courseId) WHERE id = #{courseId}";
		commonMapper.updateBySql(courseByCountSql, params);
		message.acknowledge();
	}
}
