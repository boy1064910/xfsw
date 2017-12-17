package com.xfsw.account.model.wx;

/**
 * 微信小程序用户信息水印
 * @author LiuXi
 *
 */
public class WxWaterMark {

	private String appid;
	private String timestamp;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
