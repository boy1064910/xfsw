/**
 * 
 */
package com.xfsw.model.user;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class UserModel {

	private String sessionId;
	private String account;
	private String head;
	private String nickName;
	private String email;
	
	public UserModel(String sessionId, String account, String head, String nickName) {
		super();
		this.sessionId = sessionId;
		this.account = account;
		this.head = head;
		this.nickName = nickName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
