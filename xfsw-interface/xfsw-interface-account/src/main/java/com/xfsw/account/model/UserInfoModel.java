/**
 * 
 */
package com.xfsw.account.model;

import java.io.Serializable;

/**
 * @author liuxifan
 *	用户基础信息模型
 */
public class UserInfoModel implements Serializable {

	private static final long serialVersionUID = 5410398074138161459L;
	private Integer id;
	private String account;
	private String nickName;
	private Integer status;
	private String head;
	private String email;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
