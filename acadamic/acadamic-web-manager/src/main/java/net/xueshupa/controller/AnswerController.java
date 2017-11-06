/**
 * 
 */
package net.xueshupa.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.business.service.OssService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;

import net.xueshupa.entity.Answer;
import net.xueshupa.service.AnswerService;

/**
 * 预设答案选项接口
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Controller
@RequestMapping("/manager/answer")
public class AnswerController {

	private String answerOssFolder = "answer/";
	
	@Resource(name="answerService")
	AnswerService answerService;
	
	@Resource(name="ossService")
	OssService ossService;
	
	@RequestMapping("/index")
	public void index(){
		
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResponseModel list(){
		return new ResponseModel(answerService.selectPreAnswerList());
	}
	
	/**
	 * 保存答案信息
	 * @param answerFileNames	答案文件名称数组
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@RequestMapping(value="/saveAnswers",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel saveAnswers(String[] answerFileNames){
		if(ArrayUtils.isEmpty(answerFileNames)){
			return new ResponseModel();
		}
		String lastUpdater = ThreadUserInfoManager.getAccount();
		Date lastUpdateTime = new Date();
		String[] answerFileUrls = ossService.saveObject(answerFileNames, answerOssFolder);
		Answer[] answers = new Answer[answerFileNames.length];
		for(int i=0;i<answerFileNames.length;i++){
			Answer answer = new Answer();
			answer.setAnswer(answerFileUrls[i]);
			answer.setLastUpdater(lastUpdater);
			answer.setLastUpdateTime(lastUpdateTime);
			answers[i] = answer;
		}
		Integer[] answerIds = answerService.saveAnswers(answers);
		return new ResponseModel(answerIds);
	}
	
	@RequestMapping(value="/deleteAnswer",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel deleteAnswer(Integer answerId){
		answerService.deleteAnswer(answerId,ThreadUserInfoManager.getAccount());
		return new ResponseModel();
	}
}
