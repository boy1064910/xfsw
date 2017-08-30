package com.ptmind.datadeck.entity.datasource.auth;

/**
 * 数据源授权配置信息结构
 * @author lxp
 * @version 3.0.0
 */
public abstract class AbstractAuthConfig {
	protected String url;//表单提交url
	protected String method;//表单数据提交方式
	protected AuthFormItem[] items;//授权表单输入项
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public AuthFormItem[] getItems() {
		return items;
	}
	public void setItems(AuthFormItem[] items) {
		this.items = items;
	}
}

/**
 * 授权表单输入内容数据格式
 * @author lxp
 * @version 3.0.0
 */
class AuthFormItem{
	String title;//文本框标题
	String placeholder;//文本框默认显示文字
	String inputType;//string\number\password\password
	String name;//表单请求保存connection的参数名称
	Boolean isAuthParam;//是否作为请求授权参数
	String validation;//文本框校验项
	String authParamName;//请求授权链接的参数名称
	String defaultValue;//默认值
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValidation() {
		return validation;
	}
	public void setValidation(String validation) {
		this.validation = validation;
	}
	public Boolean getIsAuthParam() {
		return isAuthParam;
	}
	public void setIsAuthParam(Boolean isAuthParam) {
		this.isAuthParam = isAuthParam;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getAuthParamName() {
		return authParamName;
	}
	public void setAuthParamName(String authParamName) {
		this.authParamName = authParamName;
	}
}



