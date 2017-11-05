package net.xueshupa.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.Answer;

@Service("answerService")
public class AnswerService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	public List<Answer> selectListByExerciseIds(Integer[] exerciseIds){
		return commonMapper.selectList("Answer.selectListByExerciseIds",exerciseIds);
	}
	
	public void deleteByExerciseId(Integer exerciseId,String operator){
		commonMapper.deleteAndBak(Answer.class, "exerciseId", exerciseId, operator);
	}
	
	public List<Answer> selectListByExerciseId(Integer exerciseId){
		return commonMapper.selectList("Answer.selectListByExerciseId",exerciseId);
	}
	
	/**
	 * 查询预设答案列表
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public List<Answer> selectPreAnswerList(){
		return commonMapper.selectList("Answer.selectPreAnswerList");
	}
	
	/**
	 * 保存答案数组数据
	 * @param answerList
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@Transactional(transactionManager="acadamicTxManager")
	public Integer[] saveAnswers(Answer[] answers){
		if(ArrayUtils.isEmpty(answers)){
			return null;
		}
		Integer[] ids = new Integer[answers.length];
		for(int i=0;i<answers.length;i++){
			commonMapper.insert("Answer.saveAnswer", answers[i]);
			ids[i] = answers[i].getId();
		}
		return ids;
	}
}
