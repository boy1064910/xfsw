package net.xueshupa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.consts.ErrorConstant;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.common.util.ArrayUtil;
import com.xfsw.common.util.StringUtil;

import net.xueshupa.entity.Answer;
import net.xueshupa.entity.Exercise;
import net.xueshupa.service.ExerciseService;

@Controller
@RequestMapping("/manager/acadamic/course/chapter/knowledge/diff/level/exercise")
public class ExerciseController {

//	@Value("${ali.oss.define.domain}")
//	String defineDomain;
//	@Value("${ali.oss.bucket}")
//	String bucketName;
//	@Value("${ali.oss.tmp.dir}")
//	String tmpDir;
//	@Value("${ali.oss.exercise.dir}")
//	String exerciseDir;
	
//	@Resource(name="ossService")
//	OssService ossService;
	
	@Resource(name="exerciseService")
	ExerciseService exerciseService;
	
	@RequestMapping("/index")
	public void index(Model model,Integer diffLevelId,String diffLevelCode){
		model.addAttribute("diffLevelId", diffLevelId);
		model.addAttribute("diffLevelCode", diffLevelCode);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResponseModel list(String diffLevelCode){
		return new ResponseModel(exerciseService.selectListByDiffLevelCode(diffLevelCode));
	}
	
	@RequestMapping("/saveExercise")
	@ResponseBody
	public ResponseModel saveExercise(Exercise exercise,String diffLevelCode,String[] answers,int[] answerIds,Integer[] deleteAnswerIds){
		if(ArrayUtil.isEmpty(answers)){
			return new ResponseModel(ErrorConstant.ERROR_BUSINESS_KNOWN,"答案不能为空");
		}
		if(!StringUtil.isEmpty(exercise.getPicUrl())){
			//设置临时文件的http全路径
//			exercise.setPicUrl(defineDomain+exercise.getPicUrl());
//			String picUrl = exercise.getPicUrl().replaceAll(tmpDir, exerciseDir);
//			ossService.copyObject(bucketName, tmpDir, exercise.getPicUrl(), bucketName, exerciseDir, picUrl);
//			exercise.setPicUrl(picUrl);//视频路径地址
		}
		
		String account = ThreadUserInfoManager.getAccount();
		exercise.setLastUpdater(account);
		
		List<Answer> answerList = new ArrayList<Answer>();
		for(int i=0;i<answers.length;i++){
			Answer answer = new Answer();
			answer.setAnswer(answers[i]);
			answer.setId(answerIds[i]);
			answer.setOrderIndex(i+1);
			answer.setLastUpdater(account);
			answerList.add(answer);
		}
		return new ResponseModel(exerciseService.saveExercise(diffLevelCode, exercise, answerList, deleteAnswerIds));
	}
	
	@RequestMapping("/deleteExercise")
	@ResponseBody
	public ResponseModel deleteExercise(Integer id){
		exerciseService.deleteExercise(id,ThreadUserInfoManager.getAccount());
		return new ResponseModel();
	}
	
	@RequestMapping("/getInfoById")
	@ResponseBody
	public ResponseModel getInfoById(Integer id){
		return new ResponseModel(exerciseService.getInfoById(id));
	}
}
