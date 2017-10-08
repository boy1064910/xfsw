package com.xfsw.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xfsw.account.service.CategoryAuthorityCacheService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.session.model.UserSessionModel;

/**
 * 管理后台权限数据接口服务
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@RestController
@RequestMapping("/manager/authority")
public class AuthorityController {
	
	@Resource(name="categoryAuthorityCacheService")
	CategoryAuthorityCacheService categoryAuthorityCacheService;
	
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
		resultModel.setData(categoryAuthorityCacheService.selectListByIds(categoryAuthorityIds));
		return resultModel;
	}
}
