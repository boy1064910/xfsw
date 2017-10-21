package net.xueshupa.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;

import net.xueshupa.entity.DiffLevel;
import net.xueshupa.service.DiffLevelService;

@Controller
@RequestMapping("/manager/acadamic/course/chapter/knowledge/diff/level")
public class DiffLevelController {

	@Resource(name="diffLevelService")
	DiffLevelService diffLevelService;
	
	@RequestMapping("/index")
	public void index(Model model,Integer knowledgeId,String knowledgeCode){
		model.addAttribute("knowledgeId", knowledgeId);
		model.addAttribute("knowledgeCode", knowledgeCode);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResponseModel list(String knowledgeCode){
		return new ResponseModel(diffLevelService.selectListByKnowledgeCode(knowledgeCode));
	}
	
	@RequestMapping("/saveDiffLevel")
	@ResponseBody
	public ResponseModel saveDiffLevel(DiffLevel diffLevel,String knowledgeCode){
		diffLevel.setLastUpdater(ThreadUserInfoManager.getAccount());
		return new ResponseModel(diffLevelService.saveDiffLevel(knowledgeCode, diffLevel));
	}
}
