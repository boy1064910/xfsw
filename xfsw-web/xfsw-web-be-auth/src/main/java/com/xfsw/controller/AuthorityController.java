package com.xfsw.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xfsw.account.service.AuthorityCacheService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.session.model.UserSessionModel;
import com.xfsw.session.service.UserSessionService;

/**
 * 管理后台权限数据接口服务
 * @author 刘希帆
 */
@RestController
@RequestMapping("/manager/authority")
public class AuthorityController {
	
	@Resource(name="userSessionService")
	UserSessionService userSessionService;
	
	@Resource(name="authorityCacheService")
	AuthorityCacheService authorityCacheService;
	
	/**
	 * 获取用户菜单权限
	 * @author liuxiaopeng
	 * @param request
	 * @return
	 */
	@GetMapping(value="/getUserAuthorityList")
	public ResponseModel getUserAuthorityList(){
		ResponseModel resultModel = new ResponseModel();
		UserSessionModel userSessionModel = ThreadUserInfoManager.getUserInfo();
		Integer[] categoryAuthorityIds = userSessionModel.getCategoryAuthorityIds();
		resultModel.setData(authorityCacheService.getCategoryAuthorityList(categoryAuthorityIds));
		return resultModel;
	}
}
