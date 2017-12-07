package com.xfsw.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.session.consts.SessionConstant;
import com.xfsw.session.service.UserSessionService;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Resource
	UserSessionService userSessionService;

	/**
	 * 获取用户公开信息
	 * @param sessionId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@GetMapping("/info")
	public ResponseModel info(@RequestHeader(SessionConstant.XFSW_SESSION_ID) String sessionId) {
		return new ResponseModel(userSessionService.getUserPublicInfo(sessionId));
	}

}