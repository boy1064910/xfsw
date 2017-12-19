/**
 * 
 */
package com.xfsw.order.model;

import java.io.Serializable;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public class GenerateOrderDetail implements Serializable {

	private static final long serialVersionUID = -6159613211167031309L;

	Integer count;
	Double price;
	Double originPrice;
	String detailExtra;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(Double originPrice) {
		this.originPrice = originPrice;
	}
	public String getDetailExtra() {
		return detailExtra;
	}
	public void setDetailExtra(String detailExtra) {
		this.detailExtra = detailExtra;
	}
}
