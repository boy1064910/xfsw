/**
 * 
 */
package com.xfsw.common.mq.model;

import java.io.Serializable;

/**
 * 订单收到成功付款信息，分发的消息结构
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class OrderReceiverModel implements Serializable {

	private static final long serialVersionUID = -65896537632226836L;
	private Integer userId;
	private Object bizExtra;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Object getBizExtra() {
		return bizExtra;
	}
	public void setBizExtra(Object bizExtra) {
		this.bizExtra = bizExtra;
	}
}
