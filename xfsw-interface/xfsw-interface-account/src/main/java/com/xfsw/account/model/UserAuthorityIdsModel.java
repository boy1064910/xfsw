package com.xfsw.account.model;

import java.io.Serializable;

public class UserAuthorityIdsModel implements Serializable {

	private static final long serialVersionUID = 7136929222950686620L;
	
	private Integer[] authorityIds;
	private Integer[] categoryAuthorityIds;
	
	public UserAuthorityIdsModel(Integer[] authorityIds,Integer[] categoryAuthorityIds){
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

}
