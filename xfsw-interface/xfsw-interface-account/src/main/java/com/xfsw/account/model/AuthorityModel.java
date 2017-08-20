package com.xfsw.account.model;

import java.io.Serializable;

public class AuthorityModel implements Serializable {

	private static final long serialVersionUID = -8194928001541583940L;
	
	private Integer id;
	private Integer pId;
	private String name;
	private boolean open;
	private boolean checked;
	private Integer type;//1: 菜单权限，2：功能权限
	private String authoritySql;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getAuthoritySql() {
		return authoritySql;
	}
	public void setAuthoritySql(String authoritySql) {
		this.authoritySql = authoritySql;
	}
}
