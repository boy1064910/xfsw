package com.xfsw.order.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class WxPayNotifyResponser {

	private String return_code;
	private String return_msg;
	public WxPayNotifyResponser() {
		super();
	}
	public WxPayNotifyResponser(String return_code, String return_msg) {
		super();
		this.return_code = return_code;
		this.return_msg = return_msg;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
}
