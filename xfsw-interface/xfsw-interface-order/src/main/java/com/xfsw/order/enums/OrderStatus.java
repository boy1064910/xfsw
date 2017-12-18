/**
 * 
 */
package com.xfsw.order.enums;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public enum OrderStatus {

	WAITING_PAY(0), PAYED(1), USER_CANCEL(-1), SYSTEM_CANCEL(-2);

	private Integer status;

	private OrderStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}
