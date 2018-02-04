/**
 * 
 */
package net.xueshupa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xfsw.common.classes.ResponseModel;

import net.xueshupa.entity.KnowledgePointContent;
import net.xueshupa.entity.KnowledgeQuestion;
import net.xueshupa.service.KnowledgePointContentService;
import net.xueshupa.service.KnowledgeQuestionService;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Controller
@RequestMapping("/manager/course/chapter/knowledge/point/content")
public class KnowledgePointContentController {

	@Autowired
	KnowledgePointContentService knowledgePointContentService;
	
	@Autowired
	KnowledgeQuestionService knowledgeQuestionService;
	
	@RequestMapping("/index")
	public void index(Model model,Integer knowledgePointId,String knowledgePointCode){
		model.addAttribute("knowledgePointId", knowledgePointId);
		model.addAttribute("knowledgePointCode", knowledgePointCode);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ResponseModel list(String knowledgePointCode){
		List<KnowledgePointContent> contentList = knowledgePointContentService.selectListByKnowledgePointCode(knowledgePointCode);
		Map<Integer,KnowledgePointContent> contentMap = new HashMap<Integer,KnowledgePointContent>();
		contentList.forEach(x->{
			x.setQuestionList(new ArrayList<KnowledgeQuestion>());
			contentMap.put(x.getId(), x);
		});
		if(!CollectionUtils.isEmpty(contentList)){
			List<Integer> knowledgePointContentIdList = contentList.stream().map(x->x.getId()).collect(Collectors.toList());
			List<KnowledgeQuestion> questionList = knowledgeQuestionService.selectListByKnowledgePointContentIdList(knowledgePointContentIdList);
			questionList.forEach(x->{
				contentMap.get(x.getKnowledgePointContentId()).getQuestionList().add(x);
			});
		}
		return new ResponseModel(contentList);
	}
	
}
