package net.xueshupa.service;

import java.util.List;

import net.xueshupa.entity.Answer;

public interface AnswerService {

	public void deleteByExerciseId(Integer exerciseId,String operator);
	
	/**
	 * 查询预设答案列表
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public List<Answer> selectPreAnswerList();
	
	public List<Answer> selectAnswerListByExerciseDetailId(Integer exerciseDetailId);
	
	/**
	 * 保存答案数组数据
	 * @param answerList
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public Integer[] saveAnswers(Answer[] answers);
	
	public void deleteAnswer(Integer id,String operator);
	
	public List<Answer> selectListByIdList(List<Integer> answerIdList);
}
