/**
 * 
 */
package com.xfsw.order.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class OrderInfo implements Serializable{

	private static final long serialVersionUID = 2629247077451472835L;

	private Long id;
	private Integer tenantId;
	private Integer userId;
	private String orderNumber;
	private String payment;
	private Double sumCount;
	private Double payCount;
	private Integer status;
	private Date createTime;
	private Date payTime;
	private String tradeNumber;
	private String paymentExtra;//支付信息扩展参数
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Double getSumCount() {
		return sumCount;
	}
	public void setSumCount(Double sumCount) {
		this.sumCount = sumCount;
	}
	public Double getPayCount() {
		return payCount;
	}
	public void setPayCount(Double payCount) {
		this.payCount = payCount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getTradeNumber() {
		return tradeNumber;
	}
	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}
	public String getPaymentExtra() {
		return paymentExtra;
	}
	public void setPaymentExtra(String paymentExtra) {
		this.paymentExtra = paymentExtra;
	}
	public String getLastUpdater() {
		return lastUpdater;
	}
	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
}
