/**
 * 
 */
package net.xueshupa.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.common.classes.ResponseModel;

import net.xueshupa.service.AnswerService;

/**
 * 预设答案选项接口
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Controller
@RequestMapping("/manager/pre/answer")
public class PreAnswerController {

	@Resource(name="answerService")
	AnswerService answerService;
	
	@RequestMapping("/index")
	public void index(){
		
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResponseModel list(){
		return new ResponseModel(answerService.selectPreAnswerList());
	}
}
