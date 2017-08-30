package com.ptmind.datadeck.entity.datasource.config;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 选择配置步骤的结构体
 * @author lxp
 * @version 3.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigStep {

	String title;//数据选择框的标题
	String placeholder;//数据选择框默认文本
	String dataType;//数据选择框的内容格式:tree or list
	String url;//步骤数据请求接口url
	String method;//步骤数据请求的方式
	
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
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
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
}

