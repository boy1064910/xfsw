package com.xfsw.session.model;

import java.io.Serializable;

/**
 * 用户登录session信息数据结构 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class UserSessionModel implements Serializable {

	private static final long serialVersionUID = -6695597901616113945L;
	
	private Integer id; //用户ID
	private String account; //用户账号
	private String head;//用户头像链接
	private String nickName;//用户昵称
	private String email;//用户邮箱
	private Integer roleId;//用户角色ID
	private String unionId;//微信开放平台unionId
	private String serviceOpenId;//微信服务号openId,暂时没用上
	private String miniOpenId;//微信小程序openId,暂时没用上
	private Integer[] authorityIds;//所有权限ID数组（公共权限、菜单权限和请求权限）
	
	public UserSessionModel(){}
	
	public UserSessionModel(Integer id, String account, String head, String nickName, String email) {
		super();
		this.id = id;
		this.account = account;
		this.head = head;
		this.nickName = nickName;
		this.email = email;
	}
	private Integer[] categoryAuthorityIds;//菜单权限ID数组
	
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
	public Integer[] getAuthorityIds() {
		return authorityIds;
	}
	public void setAuthorityIds(Integer[] authorityIds) {
		this.authorityIds = authorityIds;
	}
	public Integer[] getCategoryAuthorityIds() {
		return categoryAuthorityIds;
	}
	public void setCategoryAuthorityIds(Integer[] categoryAuthorityIds) {
		this.categoryAuthorityIds = categoryAuthorityIds;
	}
	
	
}
