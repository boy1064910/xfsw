package com.xfsw.account.model;

import java.io.Serializable;
import java.util.List;

/**
 * 用户空间信息模型数据结构
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class UserTenantModel implements Serializable{

	private static final long serialVersionUID = -6753722428341709713L;
	private Integer userTenantId;
	private Integer userId;
	private Integer tenantId;
	private String tenantName;
	private String tenantCode;
	private List<Integer> roleIdList;
	private Integer[] categoryAuthorityIds;// 菜单权限ID数组
	private Integer[] authorityIds;// 所有权限ID数组（公共权限、菜单权限和请求权限）
	
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

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
