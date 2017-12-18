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
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = -5413692416976085967L;
	
	private Integer id;
	private Integer orderInfoId;
	private Integer dataId;
	private String dataName;
	private Integer count;
	private Double price;
	private Double originPrice;
	private String detailExtra;//商品数据扩展信息
	private String lastUpdater;
	private Date lastUpdateTime;
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderInfoId() {
		return orderInfoId;
	}
	public void setOrderInfoId(Integer orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

}
