package com.ptmind.datadeck.model.connection;

/**
 * 数据源连接账户信息
 * @author xiaopeng.liu
 * @version 3.0.0
 */
public class ConnectionAccountInfo {

	private String id;//数据源连接ID
	private String name;//数据源连接账号信息
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
