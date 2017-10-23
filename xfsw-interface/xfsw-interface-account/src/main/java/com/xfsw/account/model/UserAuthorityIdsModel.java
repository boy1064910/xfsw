package com.xfsw.account.model;

import java.io.Serializable;

public class UserAuthorityIdsModel implements Serializable {

	private static final long serialVersionUID = 7136929222950686620L;
	
	private Integer userId;
	private Integer tenantId;
	private Integer[] authorityIds;
	private Integer[] categoryAuthorityIds;
	
	public UserAuthorityIdsModel(Integer userId,Integer tenantId,Integer[] authorityIds,Integer[] categoryAuthorityIds){
		this.userId = userId;
		this.tenantId = tenantId;
		this.authorityIds = authorityIds;
		this.categoryAuthorityIds = categoryAuthorityIds;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

}
