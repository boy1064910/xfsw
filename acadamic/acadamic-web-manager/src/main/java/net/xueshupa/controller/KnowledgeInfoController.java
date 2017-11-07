/**
 * 
 */
package net.xueshupa.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;

import net.xueshupa.entity.KnowledgeInfo;
import net.xueshupa.service.KnowledgeInfoService;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Controller
@RequestMapping("/manager/course/chapter/knowledge/info")
public class KnowledgeInfoController {

	@Resource(name="knowledgeInfoService")
	KnowledgeInfoService knowledgeInfoService;
	
	@RequestMapping(value="/initAddKnowledgeInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResponseModel initAddKnowledgeInfo(KnowledgeInfo knowledgeInfo){
		knowledgeInfo.setLastUpdater(ThreadUserInfoManager.getAccount());
		knowledgeInfo.setLastUpdateTime(new Date());
		Integer knowledgeId = knowledgeInfoService.saveKnowledgeInfo(knowledgeInfo);
		return new ResponseModel(knowledgeId);
	}
}
