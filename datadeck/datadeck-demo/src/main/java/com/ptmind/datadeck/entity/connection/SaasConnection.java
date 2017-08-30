package com.ptmind.datadeck.entity.connection;

/**
 * 用户连接数据源授权后保存的连接信息结构体
 * @author lxp
 * @version 3.0.0
 */
public class SaasConnection extends AbstractConnection {

	String clientId;//使用第三方平台开发者账号的client_id或者账号ID,由用户手动填写
    String clientKey;////数据源对应的客户端秘钥
	
	private String accountInfo;//建立连接的账号信息
    private String connectionConfig;//建立连接后的配置信息
	
	public String getClientKey() {
		return clientKey;
	}
	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}
	public String getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}
	public String getConnectionConfig() {
		return connectionConfig;
	}
	public void setConnectionConfig(String connectionConfig) {
		this.connectionConfig = connectionConfig;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
