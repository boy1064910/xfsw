package net.xueshupa.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/initAddKnowledge")
	public void initAddKnowledge(Model model,String chapterCode){
		model.addAttribute("chapterCode", chapterCode);
	}
	
	@RequestMapping("/saveKnowledge")
	@ResponseBody
	public ResponseModel saveKnowledge(Knowledge knowledge,String chapterCode){
		//设置临时文件的http全路径
//		knowledge.setVideoUrl(defineDomain+knowledge.getVideoUrl());
//		String videoUrl = knowledge.getVideoUrl().replaceAll(tmpDir, knowledgeDir);
//		ossService.copyObject(bucketName, tmpDir, knowledge.getVideoUrl(), bucketName, knowledgeDir, videoUrl);
//		knowledge.setVideoUrl(videoUrl);//视频路径地址
		
		knowledge.setLastUpdater(ThreadUserInfoManager.getAccount());
		
		return new ResponseModel(knowledgeService.saveKnowledge(chapterCode, knowledge));
	}
}
