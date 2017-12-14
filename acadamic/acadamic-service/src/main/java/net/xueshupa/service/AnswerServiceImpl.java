package net.xueshupa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.Answer;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public void deleteByExerciseId(Integer exerciseId,String operator){
		commonMapper.deleteAndBak(Answer.class, "exerciseId", exerciseId, operator);
	}
	
	/**
	 * 查询预设答案列表
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@Override
	public List<Answer> selectPreAnswerList(){
		String sql = "SELECT * FROM Answer WHERE exerciseDetailId IS NULL";
		return commonMapper.selectListBySql(sql, Answer.class);
	}
	
	@Override
	public List<Answer> selectAnswerListByExerciseDetailId(Integer exerciseDetailId){
		String sql = "SELECT * FROM Answer WHERE exerciseDetailId = #{exerciseDetailId} OR exerciseDetailId IS NULL";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("exerciseDetailId", exerciseDetailId);
		return commonMapper.selectListBySql(sql, params, Answer.class);
	}
	
	/**
	 * 保存答案数组数据
	 * @param answerList
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@Override
	@Transactional(transactionManager="acadamicTxManager")
	public Integer[] saveAnswers(Answer[] answers){
		if(ArrayUtils.isEmpty(answers)){
			return null;
		}
		Integer[] ids = new Integer[answers.length];
		for(int i=0;i<answers.length;i++){
			commonMapper.insert("Answer.saveAnswer", answers[i]);
			Integer maxOrderIndex = commonMapper.get("Answer.selectMaxOrderIndex",answers[i]);
			if(maxOrderIndex==null){
				maxOrderIndex = 0;
			}
			maxOrderIndex++;
			answers[i].setOrderIndex(maxOrderIndex);
			commonMapper.update("Answer.updateOrderIndexById",answers[i]);
			ids[i] = answers[i].getId();
		}
		return ids;
	}
	
	@Override
	public void deleteAnswer(Integer id,String operator){
		commonMapper.deleteAndBak(Answer.class, id, operator);
	}
	
	@Override
	public List<Answer> selectListByIdList(List<Integer> answerIdList){
		if(CollectionUtils.isEmpty(answerIdList)) {
			return null;
		}
		return commonMapper.selectList("Answer.selectListByIdList", answerIdList);
	}
}
