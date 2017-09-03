package com.ptmind.datadeck.entity.datasource.auth;

import com.ptmind.datadeck.entity.datasource.auth.request.AuthRequestParam;

/**
 * oauth2授权方式
 * @author lxp
 * @version 3.0.0
 */
public class AuthOAuth2Config extends AbstractAuthConfig {

	private AuthRequestParam[] grantParams;//授权code需要的参数,TODO 后期考虑初始化response_type\scope\redirect_uri\nonce
//	private GrantResponseParser[] grantParsers;//获取第三方回调后的参数字段配置
	
	private String tokenUrl;//获取token URL
	private AuthRequestParam[] tokenParams;
//	private String parseTokenKey;//token结果中定义token字段的key名称
//	private String expireTimeKey;//token结果中定义的过期时间字段的key名称
	
//	private String stateName;//oauth授权请求时携带的自定义参数字段名称
	
	private String accountResultKey;//token返回结果中的账号信息字段名称
	private String accountParamKey;//获取account信息需要的参数字段名称
	private String accountUrl;//获取account信息url
	private String accountParseKey;//解析account信息中的账号字段
//	private String accountIdKey;//token结果中定义的第三方平台的字段key名称
	
	public AuthRequestParam[] getGrantParams() {
		return grantParams;
	}
	public void setGrantParams(AuthRequestParam[] grantParams) {
		this.grantParams = grantParams;
	}
	public String getTokenUrl() {
		return tokenUrl;
	}
	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}
	public AuthRequestParam[] getTokenParams() {
		return tokenParams;
	}
	public void setTokenParams(AuthRequestParam[] tokenParams) {
		this.tokenParams = tokenParams;
	}
	public String getAccountUrl() {
		return accountUrl;
	}
	public void setAccountUrl(String accountUrl) {
		this.accountUrl = accountUrl;
	}
	public String getAccountParseKey() {
		return accountParseKey;
	}
	public void setAccountParseKey(String accountParseKey) {
		this.accountParseKey = accountParseKey;
	}
	public String getAccountParamKey() {
		return accountParamKey;
	}
	public void setAccountParamKey(String accountParamKey) {
		this.accountParamKey = accountParamKey;
	}
	public String getAccountResultKey() {
		return accountResultKey;
	}
	public void setAccountResultKey(String accountResultKey) {
		this.accountResultKey = accountResultKey;
	}
}

/**
 * 授权code回调之后返回的参数,需要解析记录的字段配置结构
 * @author lxp
 * @version
 */
class GrantResponseParser{
	private String keyName;//返回后的参数中,需要获取的参数key名称
	private int level;//参数所处层级
	private String saveKeyName;//存储到下一步请求map中的参数key名称
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getSaveKeyName() {
		return saveKeyName;
	}
	public void setSaveKeyName(String saveKeyName) {
		this.saveKeyName = saveKeyName;
	}
}

