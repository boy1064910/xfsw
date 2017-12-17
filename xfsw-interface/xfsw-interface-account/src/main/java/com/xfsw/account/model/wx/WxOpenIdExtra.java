/**
 * 
 */
package com.xfsw.account.model.wx;

import java.io.Serializable;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class WxOpenIdExtra implements Serializable {

	private static final long serialVersionUID = -5587183538762171873L;

	String miniOpenId;//小程序openid
	String serviceOpenId;//服务号openid
	public String getMiniOpenId() {
		return miniOpenId;
	}
	public void setMiniOpenId(String miniOpenId) {
		this.miniOpenId = miniOpenId;
	}
	public String getServiceOpenId() {
		return serviceOpenId;
	}
	public void setServiceOpenId(String serviceOpenId) {
		this.serviceOpenId = serviceOpenId;
	}
}
