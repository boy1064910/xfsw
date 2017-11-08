package net.xueshupa.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;

import net.xueshupa.entity.Exercise;
import net.xueshupa.entity.ExerciseDetail;

@Service("exerciseService")
public class ExerciseService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Resource(name="answerService")
	AnswerService answerService;
	
	public Integer insertExercise(Exercise exercise){
		String sql = "SELECT MAX(orderIndex) FROM Exercise WHERE knowledgeInfoId = #{knowledgeInfoId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("knowledgeInfoId", exercise.getKnowledgeInfoId());
		Integer orderIndex = commonMapper.getBySql(sql, params, Integer.class);
		if(orderIndex==null){
			orderIndex = 1;
		}
		else{
			orderIndex++;
		}
		exercise.setOrderIndex(orderIndex);
		commonMapper.insert(Exercise.class, exercise);
		return exercise.getId();
	}
	
	public void uploadExerciseUrl(Exercise exercise){
		String sql = "UPDATE Exercise SET exerciseUrl = #{exerciseUrl},lastUpdater = #{lastUpdater},lastUpdateTime=#{lastUpdateTime} WHERE id = #{id}";
		commonMapper.updateBySql(sql, exercise);
	}
	
	public Integer insertExerciseDetail(ExerciseDetail exerciseDetail){
		String sql = "SELECT MAX(orderIndex) FROM ExerciseDetail WHERE exerciseId = #{exerciseId}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("exerciseId", exerciseDetail.getExerciseId());
		Integer orderIndex = commonMapper.getBySql(sql, params, Integer.class);
		if(orderIndex==null){
			orderIndex = 1;
		}
		else{
			orderIndex++;
		}
		exerciseDetail.setOrderIndex(orderIndex);
		commonMapper.insert(ExerciseDetail.class, exerciseDetail);
		return exerciseDetail.getId();
	}
	
	public void updateExerciseDetail(ExerciseDetail exerciseDetail){
		String sql = "UPDATE ExerciseDetail SET type = #{type},answer = #{answer} WHERE id = #{id}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", exerciseDetail.getType());
		params.put("answer", exerciseDetail.getAnswer());
		params.put("id", exerciseDetail.getId());
		commonMapper.updateBySql(sql,params);
	}
	
	public void deleteExerciseDetail(Integer exerciseDetailId,String operator){
		commonMapper.deleteAndBak(ExerciseDetail.class, exerciseDetailId, operator);
	}
	
//	public void deleteByCourseCode(String code,String operator){
//		String sql = "DELETE FROM Exercise WHERE code LIKE CONCAT('#{code}','%')";
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("code", code);
//		commonMapper.deleteAndBak(sql, Exercise.class, params, operator);
//	}
//	
//	@Transactional
//	public Exercise saveExercise(String diffLevelCode,Exercise exercise,List<Answer> answerList,Integer[] deleteAnswerIds){
//		if(exercise.getId()!=null){
//			commonMapper.update("Exercise.updateExercise", exercise);
//			exercise = (Exercise) commonMapper.get(Exercise.class, exercise.getId());
//		}
//		else{
//			Integer orderIndex = (Integer) commonMapper.get("Exercise.selectMaxOrderIndexByCode",diffLevelCode);
//			if(orderIndex==null){
//				orderIndex = 1;
//			}
//			else{
//				orderIndex++;
//			}
//			String code = diffLevelCode + NumberUtil.toZeroCode(orderIndex, 2);
////			exercise.setCode(code);
//			exercise.setOrderIndex(orderIndex);
//			commonMapper.insert("Exercise.insertExercise", exercise);
//		}
////		List<Answer> tmpAnswerList = answerService.saveAnswerList(answerList, exercise.getId(),deleteAnswerIds,exercise.getLastUpdater());
////		exercise.setAnswerList(tmpAnswerList);
//		return exercise;
//	}
//	
//	public List<Exercise> selectListByDiffLevelCode(String diffLevelCode){
//		List<Exercise> list = commonMapper.selectList("Exercise.selectListByDiffLevelCode", diffLevelCode);
//		if(!ListUtil.isEmpty(list)){
//			Integer[] exerciseIds = new Integer[list.size()];
//			Map<Integer,Exercise> exerciseMap = new HashMap<Integer,Exercise>(); 
//			int index = 0;
//			for(Exercise exercise:list){
//				exerciseIds[index] = exercise.getId();
////				exercise.setAnswerList(new ArrayList<Answer>());
//				exerciseMap.put(exercise.getId(), exercise);
//				index++;
//			}
//			
//			List<Answer> answerList = answerService.selectListByExerciseIds(exerciseIds);
//			if(!ListUtil.isEmpty(answerList)){
//				for(Answer answer:answerList){
////					exerciseMap.get(answer.getExerciseId()).getAnswerList().add(answer);
//				}
//			}
//		}
//		return list;
//	}
//	
//	public List<Exercise> selectListByKnowledgeCode(String knowledgeCode){
//		List<Exercise> list = commonMapper.selectList("Exercise.selectListByKnowledgeCode", knowledgeCode);
//		if(!ListUtil.isEmpty(list)){
//			Integer[] exerciseIds = new Integer[list.size()];
//			Map<Integer,Exercise> exerciseMap = new HashMap<Integer,Exercise>(); 
//			int index = 0;
//			for(Exercise exercise:list){
//				exerciseIds[index] = exercise.getId();
////				exercise.setAnswerList(new ArrayList<Answer>());
//				exerciseMap.put(exercise.getId(), exercise);
//				index++;
//			}
//			
//			List<Answer> answerList = answerService.selectListByExerciseIds(exerciseIds);
//			if(!ListUtil.isEmpty(answerList)){
//				for(Answer answer:answerList){
////					exerciseMap.get(answer.getExerciseId()).getAnswerList().add(answer);
//				}
//			}
//		}
//		return list;
//	}
//	
//	@Transactional
//	public void deleteExercise(Integer id,String operator){
//		answerService.deleteByExerciseId(id, operator);
//		commonMapper.deleteAndBak(Exercise.class, id, operator);
//	}
//	
//	public Exercise getInfoById(Integer id){
//		Exercise exercise = (Exercise) commonMapper.get(Exercise.class, id);
////		exercise.setAnswerList(answerService.selectListByExerciseId(id));
//		return exercise;
//	}
//	
//	/**
//	 * 前端接口
//	 * @param diffLevelCode
//	 * @return
//	 */
//	public List<MiniExerciseListModel> selectMiniListByDiffLevelCode(String knowledgeCode){
//		List<Exercise> list = commonMapper.selectList("Exercise.selectListByKnowledgeCode", knowledgeCode);
//		if(!ListUtil.isEmpty(list)){
//			List<MiniExerciseListModel> miniList = new ArrayList<MiniExerciseListModel>(list.size());
//			Integer[] exerciseIds = new Integer[list.size()];
//			Map<Integer,Exercise> exerciseMap = new LinkedHashMap<Integer,Exercise>(); 
//			int index = 0;
//			for(Exercise exercise:list){
//				exerciseIds[index] = exercise.getId();
////				exercise.setAnswerList(new ArrayList<Answer>());
//				exerciseMap.put(exercise.getId(), exercise);
//				index++;
//			}
//			
//			List<Answer> answerList = answerService.selectListByExerciseIds(exerciseIds);
//			if(!ListUtil.isEmpty(answerList)){
//				for(Answer answer:answerList){
////					exerciseMap.get(answer.getExerciseId()).getAnswerList().add(answer);
//				}
//			}
//			
//			for(Entry<Integer,Exercise> entry:exerciseMap.entrySet()){
//				miniList.add(new MiniExerciseListModel(entry.getValue()));
//			}
//			return miniList;
//		}
//		return null;
//	}
	
}
