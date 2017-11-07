package net.xueshupa.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;

import net.xueshupa.entity.Knowledge;
import net.xueshupa.service.KnowledgeService;

@Controller
@RequestMapping("/manager/course/chapter/knowledge")
public class KnowledgeController {

//	@Value("${ali.oss.define.domain}")
//	String defineDomain;
//	@Value("${ali.oss.bucket}")
//	String bucketName;
//	@Value("${ali.oss.tmp.dir}")
//	String tmpDir;
//	@Value("${ali.oss.knowledge.dir}")
//	String knowledgeDir;
	
//	@Resource(name="ossService")
//	OssService ossService;
	
	@Resource(name="knowledgeService")
	KnowledgeService knowledgeService;
	
	@RequestMapping("/index")
	public void index(Model model,Integer chapterId,String chapterCode){
		model.addAttribute("chapterId", chapterId);
		model.addAttribute("chapterCode", chapterCode);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResponseModel list(String chapterCode){
		return new ResponseModel(knowledgeService.selectListByChapterCode(chapterCode));
	}
	
	@RequestMapping(value="/saveKnowledge",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel saveKnowledge(Knowledge knowledge,String chapterCode){
		knowledge.setLastUpdater(ThreadUserInfoManager.getAccount());
		return new ResponseModel(knowledgeService.saveKnowledge(chapterCode, knowledge));
	}
	
	@RequestMapping("/getKnowledgeById")
	@ResponseBody
	public ResponseModel getKnowledgeById(Integer knowledgeId){
		return new ResponseModel(knowledgeService.getById(knowledgeId));
	}
	
	@RequestMapping(value="/deleteKnowledgeById",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel deleteKnowledgeById(Integer knowledgeId){
		knowledgeService.deleteById(knowledgeId, ThreadUserInfoManager.getAccount());
		return new ResponseModel();
	}

	/**
	 * 进入知识点设置界面
	 * @param model
	 * @param knowledgeId
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@RequestMapping("/initSettle")
	public void initSettle(Model model,Integer knowledgeId){
		model.addAttribute("knowledgeId", knowledgeId);
	}
}
