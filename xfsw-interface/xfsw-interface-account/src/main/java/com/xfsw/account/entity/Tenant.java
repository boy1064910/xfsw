package com.xfsw.account.entity;

import java.io.Serializable;

/**
 * 租户空间类结构
 * @author xiaopeng.liu
 * @version 3.0.0
 */
public class Tenant implements Serializable{

	private static final long serialVersionUID = 7545720674037849113L;
	
	private Integer id;
	private String name;
	private String code;
	
	public Tenant() {}
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
