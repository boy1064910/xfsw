package com.xfsw.account.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.xfsw.account.entity.User;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class UserModel implements Serializable {

	private static final long serialVersionUID = -1799586730012460617L;

	private Integer id;
	private String account;
	private String authCode;
	private String nickName;
	private String head;
	private String email;
	private Date registeTime;
	private Integer status;
	private List<UserTenantModel> userTenantRoleList;
	
	public UserModel() {}
	
	public UserModel(User user) {
		this.id = user.getId();
		this.account = user.getAccount();
		this.authCode = user.getPwd();
		this.nickName = user.getNickName();
		this.head = user.getHead();
		this.email = user.getEmail();
		this.registeTime = user.getRegisteTime();
		this.status = user.getStatus();
	}
	
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
	public List<UserTenantModel> getUserTenantRoleList() {
		return userTenantRoleList;
	}
	public void setUserTenantRoleList(List<UserTenantModel> userTenantRoleList) {
		this.userTenantRoleList = userTenantRoleList;
	}

	/**
	 * 
	 * @return the authCode
	 * @author xiaopeng.liu
	 * @version 
	 */
	public String getAuthCode() {
		return authCode;
	}

	/**
	 * 
	 * @param authCode the authCode to set
	 * @author xiaopeng.liu
	 * @version 
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public Date getRegisteTime() {
		return registeTime;
	}

	public void setRegisteTime(Date registeTime) {
		this.registeTime = registeTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
