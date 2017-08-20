package com.xfsw.account.service;

public interface SystemErrorLogService {

	/**
	 * 后台系统错误记录
	 * @param info
	 * @param operator
	 * @author liuxifan
	 */
	void insertBackErrorLog(String info,String operator);
}
