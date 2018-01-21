/**
 * 
 */
package com.xfsw.common.mq.model;

import java.io.Serializable;
import java.util.List;

/**
 * 订单收到成功付款信息，分发的消息结构
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class OrderReceiverModel implements Serializable {

	private static final long serialVersionUID = -65896537632226836L;
	private Integer userId;
	private String bizExtra;
	private List<String> detailBizExtra;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getBizExtra() {
		return bizExtra;
	}
	public void setBizExtra(String bizExtra) {
		this.bizExtra = bizExtra;
	}
	public List<String> getDetailBizExtra() {
		return detailBizExtra;
	}
	public void setDetailBizExtra(List<String> detailBizExtra) {
		this.detailBizExtra = detailBizExtra;
	}
}
