package com.xfsw.order.model.wx.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信统一下单参数结构
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@XmlRootElement(name="xml")
public class WxUnionOrderRequester {
	
	@XmlElement
	private String appid;
	@XmlElement
	private String mch_id;
	@XmlElement
	private String device_info;
	@XmlElement
	private String nonce_str;
	@XmlElement
	private String body;
	@XmlElement
	private String sign;
	@XmlElement
	private String out_trade_no;
	@XmlElement
	private int total_fee;
	@XmlElement
	private String spbill_create_ip;
	@XmlElement
	private String notify_url;
	@XmlElement
	private String trade_type;
	@XmlElement
	private String product_id;
	@XmlElement
	private String openid;
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
