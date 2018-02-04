/**
 * 
 */
package net.xueshupa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.common.classes.ResponseModel;

import net.xueshupa.service.KnowledgePointService;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Controller
@RequestMapping("/manager/course/chapter/knowledge/point")
public class KnowledgePointController {

	@Autowired
	KnowledgePointService knowledgePointService;
	
	@RequestMapping("/index")
	public void index(Model model,Integer knowledgeId,String knowledgeCode){
		model.addAttribute("knowledgeId", knowledgeId);
		model.addAttribute("knowledgeCode", knowledgeCode);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResponseModel list(String knowledgeCode){
		return new ResponseModel(knowledgePointService.selectListByKnowledgeCode(knowledgeCode));
	}
	
}
