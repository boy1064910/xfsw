/**
 * 
 */
package com.xfsw.order.model.wx;

import java.io.Serializable;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class WxGenerateOrderResponser implements Serializable {

	private static final long serialVersionUID = 4236371989596392441L;
	
	private String timeStamp;
	private String nonceStr;
	private String packageInfo;
	private String signType;
	private String paySign;
	
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPackageInfo() {
		return packageInfo;
	}
	public void setPackageInfo(String packageInfo) {
		this.packageInfo = packageInfo;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	
}
