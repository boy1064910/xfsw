/**
 * 
 */
package net.xueshupa.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.business.service.OssService;
import com.xfsw.common.classes.ResponseModel;

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
	private String 
	
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
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@RequestMapping(value="/saveAnswer",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel saveAnswer(String[] answerPicUrls){
		return new ResponseModel(answerService.selectPreAnswerList());
	}
}
