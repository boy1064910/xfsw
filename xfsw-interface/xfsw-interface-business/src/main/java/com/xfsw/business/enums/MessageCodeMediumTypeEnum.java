/**
 * 验证码设备类型枚举类
 * @author liuxiaopeng
 *
 */
package com.xfsw.business.enums;
public enum MessageCodeMediumTypeEnum{
	PHONE(1,"手机验证码"),
	EMAIL(2,"邮箱验证码");
	
	private int mediumType;
	private String mediumTypeRemark;

	MessageCodeMediumTypeEnum(int mediumType,String mediumTypeRemark) {
		this.mediumType = mediumType;
		this.mediumTypeRemark = mediumTypeRemark;
	}

	/**
	 * 获取验证码设备类型代码
	 * @return
	 */
	public int getMediumType() {
		return mediumType;
	}
	
	/**
	 * 获取验证码设备类型文字描述
	 * @return
	 */
	public String getMediumTypeRemark(){
		return mediumTypeRemark;
	}
	
	/**
	 * 根据设备类型代码获取验证码业务枚举信息
	 * @param mediumType
	 * @return
	 */
	public static MessageCodeMediumTypeEnum getTypeEnum(int mediumType){
		MessageCodeMediumTypeEnum re = null;
		for(MessageCodeMediumTypeEnum e:MessageCodeMediumTypeEnum.values()){
			if(e.getMediumType()==mediumType){
				re=e;
				break;
			}
		}
		return re;
	}
}