package com.xfsw.account.model;

import java.io.Serializable;

public class UserSessionModel implements Serializable {

	private static final long serialVersionUID = -6695597901616113945L;
	private Integer id;
	private String account;
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
}
