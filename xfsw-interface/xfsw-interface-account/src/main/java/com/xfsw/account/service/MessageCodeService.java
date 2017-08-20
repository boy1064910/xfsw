/**
 * 
 */
package com.xfsw.account.service;

/**
 * 验证码服务接口
 * @author lxp
 */
public interface MessageCodeService {

	/**
	 * 发送手机验证码
	 * @param account	手机号
	 * @param businessType	业务类型(详情见{@link com.xfsw.universe.enums.MessageCodeBusinessTypeEnum})
	 * @param ip	用户请求的IP地址
	 */
	public void sendPhoneCode(String account,Integer businessType,String ip);
	
	/**
	 * 验证手机验证码
	 * @author liuxiaopeng
	 * @param	account	手机号
	 * @param	businessType	业务类型(详情见{@link com.xfsw.universe.enums.MessageCodeBusinessTypeEnum})
	 * @param	code	验证码
	 */
	public boolean verifyPhoneCode(String account,Integer businessType,String code);
	
	/**
	 * 调用接口给指定手机发送短信
	 * @param phoneNumber	手机号
	 * @param content	短信内容
	 */
	public void sendSms(String phoneNumber,String content);
}
