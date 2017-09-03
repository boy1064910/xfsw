package com.ptmind.datadeck.entity.datasource.field;

public class FieldParseConfig {

	String url;
	String itemsKey;
	FieldParseSetting parseSetting;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getItemsKey() {
		return itemsKey;
	}
	public void setItemsKey(String itemsKey) {
		this.itemsKey = itemsKey;
	}
	public FieldParseSetting getParseSetting() {
		return parseSetting;
	}
	public void setParseSetting(FieldParseSetting parseSetting) {
		this.parseSetting = parseSetting;
	}
}
