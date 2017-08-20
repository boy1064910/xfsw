/**
 * 
 */
package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

import com.xfsw.account.enums.MessageCodeBusinessTypeEnum;
import com.xfsw.account.enums.MessageCodeMediumTypeEnum;
	
/**
 * 验证码实体类
 * @author lxp
 */
public class MessageCode implements Serializable {

	private static final long serialVersionUID = -7029355135097052722L;
	private Integer id;
	private Integer mediumType;
	private String mediumTypeRemark;
	private String code;
	private String ip;
	private String account;
	private Date sendTime;
	private Integer isValid;
	private Integer businessType;
	private String businessTypeRemark;//短信类型描述说明
	
	//辅助字段
	private String template;//短信模板
	
	public MessageCode(MessageCodeMediumTypeEnum mediumType, String code, String ip, String account, int businessType) {
		super();
		this.mediumType = mediumType.getMediumType();
		this.mediumTypeRemark = mediumType.getMediumTypeRemark();
		this.code = code;
		this.ip = ip;
		this.account = account;
		MessageCodeBusinessTypeEnum messageCodeBusinessType = MessageCodeBusinessTypeEnum.getTypeEnum(businessType);
		if(messageCodeBusinessType==null){
			throw new RuntimeException("无匹配的验证码业务类型："+businessType);
		}
		this.businessType = messageCodeBusinessType.getBusinessType();
		this.businessTypeRemark = messageCodeBusinessType.getBusinessTypeRemark();
		this.template = messageCodeBusinessType.getTemplate();
	}
	
	public MessageCode(MessageCodeMediumTypeEnum mediumType, String code, String account, int businessType) {
		this.mediumType = mediumType.getMediumType();
		this.code = code;
		this.account = account;
		this.businessType = businessType;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMediumType() {
		return mediumType;
	}
	public void setMediumType(Integer mediumType) {
		this.mediumType = mediumType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getMediumTypeRemark() {
		return mediumTypeRemark;
	}

	public void setMediumTypeRemark(String mediumTypeRemark) {
		this.mediumTypeRemark = mediumTypeRemark;
	}

	public String getBusinessTypeRemark() {
		return businessTypeRemark;
	}

	public void setBusinessTypeRemark(String businessTypeRemark) {
		this.businessTypeRemark = businessTypeRemark;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
}
