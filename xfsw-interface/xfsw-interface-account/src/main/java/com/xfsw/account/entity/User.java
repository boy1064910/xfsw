package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuxifan
 *
 */
/**
 * 用户服务中的用户实体类
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class User implements Serializable {

	private static final long serialVersionUID = -4623969779113238672L;
	
	private Integer id;
	private String account;
	private String pwd;
	private String nickName;
	private Integer status;
	private String head;
	private Date registeTime;
	private String email;
	private Integer roleId;
	private String unionId;
	private String serviceOpenId;
	private String miniOpenId;
	
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public Date getRegisteTime() {
		return registeTime;
	}
	public void setRegisteTime(Date registeTime) {
		this.registeTime = registeTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public String getServiceOpenId() {
		return serviceOpenId;
	}
	public void setServiceOpenId(String serviceOpenId) {
		this.serviceOpenId = serviceOpenId;
	}
	public String getMiniOpenId() {
		return miniOpenId;
	}
	public void setMiniOpenId(String miniOpenId) {
		this.miniOpenId = miniOpenId;
	}

}
