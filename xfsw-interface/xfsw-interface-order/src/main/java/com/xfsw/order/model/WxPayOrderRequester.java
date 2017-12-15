package com.xfsw.order.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class WxPayOrderRequester {
	
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
	
	
	public WxPayOrderRequester(String appid, String mch_id, String device_info, String nonce_str, String body, String sign, String out_trade_no, int total_fee, String spbill_create_ip, String notify_url, String trade_type, String product_id) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.device_info = device_info;
		this.nonce_str = nonce_str;
		this.body = body;
		this.sign = sign;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.spbill_create_ip = spbill_create_ip;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
		this.product_id = product_id;
	}
	
	public WxPayOrderRequester(String appid, String mch_id, String device_info, String nonce_str, String body, String sign, String out_trade_no, int total_fee, String spbill_create_ip, String notify_url, String trade_type, String product_id,String openid) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.device_info = device_info;
		this.nonce_str = nonce_str;
		this.body = body;
		this.sign = sign;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.spbill_create_ip = spbill_create_ip;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
		this.product_id = product_id;
		this.openid = openid;
	}
	
	public WxPayOrderRequester() {
		super();
	}
}
