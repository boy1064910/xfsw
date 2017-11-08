package net.xueshupa.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.business.service.OssService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;

import net.xueshupa.entity.Exercise;
import net.xueshupa.entity.ExerciseDetail;
import net.xueshupa.service.ExerciseService;

@Controller
@RequestMapping("/manager/course/chapter/exersice")
public class ExerciseController {

//	@Value("${ali.oss.define.domain}")
//	String defineDomain;
//	@Value("${ali.oss.bucket}")
//	String bucketName;
//	@Value("${ali.oss.tmp.dir}")
//	String tmpDir;
//	@Value("${ali.oss.exercise.dir}")
//	String exerciseDir;
	
	private String exerciseOssFolder = "exercise/";
	
	@Resource(name="ossService")
	OssService ossService;
	
	@Resource(name="exerciseService")
	ExerciseService exerciseService;
	
	
//	@RequestMapping("/index")
//	public void index(Model model,Integer diffLevelId,String diffLevelCode){
//		model.addAttribute("diffLevelId", diffLevelId);
//		model.addAttribute("diffLevelCode", diffLevelCode);
//	}
//	
//	@RequestMapping("/list")
//	@ResponseBody
//	public ResponseModel list(String diffLevelCode){
//		return new ResponseModel(exerciseService.selectListByDiffLevelCode(diffLevelCode));
//	}
//	
//	@RequestMapping("/saveExercise")
//	@ResponseBody
//	public ResponseModel saveExercise(Exercise exercise,String diffLevelCode,String[] answers,int[] answerIds,Integer[] deleteAnswerIds){
//		if(ArrayUtil.isEmpty(answers)){
//			return new ResponseModel(ErrorConstant.ERROR_BUSINESS_KNOWN,"答案不能为空");
//		}
////		if(!StringUtil.isEmpty(exercise.getPicUrl())){
//			//设置临时文件的http全路径
////			exercise.setPicUrl(defineDomain+exercise.getPicUrl());
////			String picUrl = exercise.getPicUrl().replaceAll(tmpDir, exerciseDir);
////			ossService.copyObject(bucketName, tmpDir, exercise.getPicUrl(), bucketName, exerciseDir, picUrl);
////			exercise.setPicUrl(picUrl);//视频路径地址
////		}
//		
//		String account = ThreadUserInfoManager.getAccount();
//		exercise.setLastUpdater(account);
//		
//		List<Answer> answerList = new ArrayList<Answer>();
//		for(int i=0;i<answers.length;i++){
//			Answer answer = new Answer();
//			answer.setAnswer(answers[i]);
//			answer.setId(answerIds[i]);
//			answer.setOrderIndex(i+1);
//			answer.setLastUpdater(account);
//			answerList.add(answer);
//		}
//		return new ResponseModel(exerciseService.saveExercise(diffLevelCode, exercise, answerList, deleteAnswerIds));
//	}
//	
//	@RequestMapping("/deleteExercise")
//	@ResponseBody
//	public ResponseModel deleteExercise(Integer id){
//		exerciseService.deleteExercise(id,ThreadUserInfoManager.getAccount());
//		return new ResponseModel();
//	}
	
	@RequestMapping("/initAddExercise")
	@ResponseBody
	public ResponseModel initAddExercise(Integer knowledgeInfoId){
		Exercise exercise = new Exercise();
		exercise.setLastUpdater(ThreadUserInfoManager.getAccount());
		exercise.setLastUpdateTime(new Date());
		exercise.setKnowledgeInfoId(knowledgeInfoId);
		Integer exerciseId = exerciseService.insertExercise(exercise);
		return new ResponseModel(exerciseId);
	}
	
	@RequestMapping("/uploadExercise")
	@ResponseBody
	public ResponseModel uploadExercise(Integer exerciseId,String filePath){
		String exerciseUrl = ossService.saveObject(filePath, exerciseOssFolder);
		
		Exercise exercise = new Exercise();
		exercise.setId(exerciseId);
		exercise.setExerciseUrl(exerciseUrl);
		exercise.setLastUpdater(ThreadUserInfoManager.getAccount());
		exercise.setLastUpdateTime(new Date());
		
		exerciseService.uploadExerciseUrl(exercise);
		return new ResponseModel(exerciseUrl);
	}
	
	@RequestMapping("/insertExerciseDetail")
	@ResponseBody
	public ResponseModel insertExerciseDetail(Integer exerciseId){
		ExerciseDetail exerciseDetail = new ExerciseDetail();
		exerciseDetail.setExerciseId(exerciseId);
		exerciseDetail.setLastUpdater(ThreadUserInfoManager.getAccount());
		exerciseDetail.setLastUpdateTime(new Date());
		Integer exerciseDetailId = exerciseService.insertExerciseDetail(exerciseDetail);
		return new ResponseModel(exerciseDetailId);
	}
	
	@RequestMapping("/updateExerciseDetail")
	@ResponseBody
	public ResponseModel updateExerciseDetail(Integer exerciseDetailId,String type,String answer){
		ExerciseDetail exerciseDetail = new ExerciseDetail();
		exerciseDetail.setType(type);
		exerciseDetail.setAnswer(answer);
		exerciseDetail.setId(exerciseDetailId);
		exerciseService.updateExerciseDetail(exerciseDetail);
		return new ResponseModel();
	}
	
	@RequestMapping("/delteExerciseDetail")
	@ResponseBody
	public ResponseModel delteExerciseDetail(Integer exerciseDetailId){
		exerciseService.deleteExerciseDetail(exerciseDetailId,ThreadUserInfoManager.getAccount());
		return new ResponseModel();
	}
	
}
