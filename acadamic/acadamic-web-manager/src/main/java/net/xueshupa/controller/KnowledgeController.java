package net.xueshupa.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.business.service.OssService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;

import net.xueshupa.entity.Knowledge;
import net.xueshupa.entity.KnowledgeInfo;
import net.xueshupa.entity.KnowledgeInfoDetail;
import net.xueshupa.service.KnowledgeInfoService;
import net.xueshupa.service.KnowledgeService;

@Controller
@RequestMapping("/manager/course/chapter/knowledge")
public class KnowledgeController {

	@Resource(name="knowledgeService")
	KnowledgeService knowledgeService;
	
	@Resource(name="knowledgeInfoService")
	KnowledgeInfoService knowledgeInfoService;
	
	@Resource(name="ossService")
	OssService ossService;
	
	private String knowledgeDetailVideoOssFolder = "knowledge-detail-video/";
	
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
		knowledge.setLastUpdateTime(new Date());
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
	
	@RequestMapping(value="/initAddKnowledgeInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel initAddKnowledgeInfo(KnowledgeInfo knowledgeInfo){
		knowledgeInfo.setLastUpdater(ThreadUserInfoManager.getAccount());
		knowledgeInfo.setLastUpdateTime(new Date());
		Integer knowledgeId = knowledgeInfoService.saveKnowledgeInfo(knowledgeInfo);
		return new ResponseModel(knowledgeId);
	}
	
	@RequestMapping(value="/deleteKnowledgeInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel deleteKnowledgeInfo(Integer knowledgeInfoId){
		knowledgeInfoService.deleteKnowledgeInfo(knowledgeInfoId,ThreadUserInfoManager.getAccount());
		return new ResponseModel();
	}
	
	@RequestMapping(value="/insertKnowledgeInfoDetail",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel insertKnowledgeInfoDetail(KnowledgeInfoDetail knowledgeInfoDetail){
		String knowledgeDetailUrl = ossService.saveObject(knowledgeInfoDetail.getInfo(), knowledgeDetailVideoOssFolder);
		knowledgeInfoDetail.setInfo(knowledgeDetailUrl);
		knowledgeInfoDetail.setLastUpdater(ThreadUserInfoManager.getAccount());
		knowledgeInfoDetail.setLastUpdateTime(new Date());
		Integer knowledgeDetailId = knowledgeInfoService.saveKnowledgeInfoDetail(knowledgeInfoDetail);
		knowledgeInfoDetail.setId(knowledgeDetailId);
		return new ResponseModel(knowledgeInfoDetail);
	}
	
	@RequestMapping(value="/updateKnowledgeInfoDetail",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel updateKnowledgeInfoDetail(KnowledgeInfoDetail knowledgeInfoDetail){
		knowledgeInfoDetail.setLastUpdater(ThreadUserInfoManager.getAccount());
		knowledgeInfoDetail.setLastUpdateTime(new Date());
		knowledgeInfoService.updateKnowledgeInfoDetail(knowledgeInfoDetail);
		return new ResponseModel();
	}
	
	@RequestMapping(value="/deleteKnowledgeInfoDetail",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel deleteKnowledgeInfoDetail(Integer knowledgeInfoDetailId){
		knowledgeInfoService.deleteKnowledgeInfoDetail(knowledgeInfoDetailId, ThreadUserInfoManager.getAccount());
		return new ResponseModel();
	}
}
