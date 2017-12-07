package com.xfsw.session.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.xfsw.account.model.UserModel;
import com.xfsw.account.model.UserTenantModel;

/**
 * 
 * @author xiaopeng.liu
 * @version
 */
public class UserSessionModel implements Serializable {

	private static final long serialVersionUID = -1799586730012460617L;

	private Integer id;
	private String account;
	private String authCode;//授权登录码，后期后台服务修改为有效期的登录服务
	private String nickName;
	private String head;
	private String email;
	private Date registeTime;
	private Integer status;
	private List<UserTenantSessionModel> userTenantList;
	
	//当前空间下的相关数据信息
	private Integer tenantId;
	private String tenantName;
	private String tenantCode;
	private List<Integer> roleIdList;
	private Integer[] categoryAuthorityIds;// 菜单权限ID数组
	private Integer[] authorityIds;// 所有权限ID数组（公共权限、菜单权限和请求权限）

	public UserSessionModel() {
	}

	public UserSessionModel(UserModel userModel) {
		this.id = userModel.getId();
		this.account = userModel.getAccount();
		this.nickName = userModel.getNickName();
		this.head = userModel.getHead();
		this.email = userModel.getEmail();
		this.registeTime = userModel.getRegisteTime();
		this.status = userModel.getStatus();
		
		if(!CollectionUtils.isEmpty(userModel.getUserTenantRoleList())){
			this.userTenantList = new ArrayList<UserTenantSessionModel>(userModel.getUserTenantRoleList().size());
			userModel.getUserTenantRoleList().forEach((u)->{
				this.userTenantList.add(new UserTenantSessionModel(u));
			});
			
			this.tenantId = this.userTenantList.get(0).getTenantId();
			this.tenantName = this.userTenantList.get(0).getTenantName();
			this.tenantCode = this.userTenantList.get(0).getTenantCode();
			this.roleIdList = this.userTenantList.get(0).getRoleIdList();
			this.categoryAuthorityIds = this.userTenantList.get(0).getCategoryAuthorityIds();
			this.authorityIds = this.userTenantList.get(0).getAuthorityIds();
		}
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

	public Integer[] getCategoryAuthorityIds() {
		return categoryAuthorityIds;
	}

	public void setCategoryAuthorityIds(Integer[] categoryAuthorityIds) {
		this.categoryAuthorityIds = categoryAuthorityIds;
	}

	public Integer[] getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(Integer[] authorityIds) {
		this.authorityIds = authorityIds;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
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

class UserTenantSessionModel {

	private Integer userTenantId;
	private Integer tenantId;
	private String tenantName;
	private String tenantCode;
	private List<Integer> roleIdList;
	private Integer[] categoryAuthorityIds;// 菜单权限ID数组
	private Integer[] authorityIds;// 所有权限ID数组（公共权限、菜单权限和请求权限）

	public UserTenantSessionModel(UserTenantModel userTenantModel) {
		super();
		this.userTenantId = userTenantModel.getUserTenantId();
		this.tenantId = userTenantModel.getTenantId();
		this.tenantName = userTenantModel.getTenantName();
		this.tenantCode = userTenantModel.getTenantCode();
		this.roleIdList = userTenantModel.getRoleIdList();
		this.categoryAuthorityIds = userTenantModel.getCategoryAuthorityIds();
		this.authorityIds = userTenantModel.getAuthorityIds();
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Integer getUserTenantId() {
		return userTenantId;
	}

	public void setUserTenantId(Integer userTenantId) {
		this.userTenantId = userTenantId;
	}

	public Integer[] getCategoryAuthorityIds() {
		return categoryAuthorityIds;
	}

	public void setCategoryAuthorityIds(Integer[] categoryAuthorityIds) {
		this.categoryAuthorityIds = categoryAuthorityIds;
	}

	public Integer[] getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(Integer[] authorityIds) {
		this.authorityIds = authorityIds;
	}
}