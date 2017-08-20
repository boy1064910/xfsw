/**
 * 手机验证码业务类型枚举类
 * @author liuxiaopeng
 *
 */
package com.xfsw.account.enums;
public enum MessageCodeBusinessTypeEnum{
	BIND_MINI_ACCOUNT(1,"微信小程序绑定手机号","短信验证码：【${0}】，您正在使用学术葩微信小程序绑定手机号。如非本人操作，请妥善保管，不要泄露给其他人。");
	
	private int businessType;
	private String businessTypeRemark;//业务类型描述
	private String template;

	MessageCodeBusinessTypeEnum(int businessType,String businessTypeRemark,String template) {
		this.businessType = businessType;
		this.businessTypeRemark = businessTypeRemark;
		this.template = template;
	}

	/**
	 * 获取验证码业务类型代码
	 * @return
	 */
	public int getBusinessType() {
		return businessType;
	}
	
	/**
	 * 获取验证码业务类型文字描述
	 * @return
	 */
	public String getBusinessTypeRemark(){
		return businessTypeRemark;
	}
	
	public String getTemplate() {
		return template;
	}
	
	/**
	 * 根据业务类型代码获取验证码业务枚举信息
	 * @param businessType
	 * @return
	 */
	public static MessageCodeBusinessTypeEnum getTypeEnum(int businessType){
		MessageCodeBusinessTypeEnum re = null;
		for(MessageCodeBusinessTypeEnum e:MessageCodeBusinessTypeEnum.values()){
			if(e.getBusinessType()==businessType){
				re=e;
				break;
			}
		}
		return re;
	}
}