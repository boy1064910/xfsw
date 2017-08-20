/**
 * 
 */
package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuxifan
 *	用户登录记录
 */
public class UserLoginRecord implements Serializable {

	private static final long serialVersionUID = -2980018789687128897L;
	private Integer id;
	private Integer userId;
	private String ip;
	private Date loginTime;
	
	public UserLoginRecord(Integer userId,String ip){
		this.userId = userId;
		this.ip = ip;
		this.loginTime = new Date();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
