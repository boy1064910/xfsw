/**
 * 
 */
package com.xfsw.order.model.wx;

import java.io.Serializable;

import com.xfsw.order.model.BaseGenerateOrderInfo;

/**
 * 生成微信订单参数
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class WxGenerateOrderInfo extends BaseGenerateOrderInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	String openId;//用户在微信端

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
