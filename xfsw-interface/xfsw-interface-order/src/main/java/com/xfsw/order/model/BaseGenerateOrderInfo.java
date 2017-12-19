/**
 * 
 */
package com.xfsw.order.model;

import java.io.Serializable;
import java.util.List;

import com.xfsw.order.enums.Payment;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public abstract class BaseGenerateOrderInfo implements Serializable{

	private static final long serialVersionUID = -2790435330108007095L;
	
	Integer userId;
	Integer tenantId;
	Payment payment;
	Double sumCount;
	String operator;
	String bizCode;
	List<GenerateOrderDetail> detailList;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTenantId() {
		return tenantId;
	}
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Double getSumCount() {
		return sumCount;
	}
	public void setSumCount(Double sumCount) {
		this.sumCount = sumCount;
	}
	public List<GenerateOrderDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<GenerateOrderDetail> detailList) {
		this.detailList = detailList;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	
}
