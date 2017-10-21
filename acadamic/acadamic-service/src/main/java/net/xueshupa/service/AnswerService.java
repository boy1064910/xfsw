package net.xueshupa.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.ArrayUtil;
import com.xfsw.common.util.ListUtil;

import net.xueshupa.entity.Answer;

@Service("answerService")
public class AnswerService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Transactional
	public List<Answer> saveAnswerList(List<Answer> answerList,Integer exerciseId,Integer[] deleteIds,String operator){
		if(!ListUtil.isEmpty(answerList)){
			for(Answer answer:answerList){
				if(answer.getId().intValue()!=0){
					commonMapper.update("Answer.updateAnswer",answer);
				}
				else{
					answer.setExerciseId(exerciseId);
					commonMapper.update("Answer.insertAnswer",answer);
				}
			}
		}
		if(!ArrayUtil.isEmpty(deleteIds)){
			commonMapper.deleteAndBak(Answer.class, "id", deleteIds, operator);
		}
		return answerList;
	}
	
	public List<Answer> selectListByExerciseIds(Integer[] exerciseIds){
		return commonMapper.selectList("Answer.selectListByExerciseIds",exerciseIds);
	}
	
	public void deleteByExerciseId(Integer exerciseId,String operator){
		commonMapper.deleteAndBak(Answer.class, "exerciseId", exerciseId, operator);
	}
	
	public List<Answer> selectListByExerciseId(Integer exerciseId){
		return commonMapper.selectList("Answer.selectListByExerciseId",exerciseId);
	}
}
