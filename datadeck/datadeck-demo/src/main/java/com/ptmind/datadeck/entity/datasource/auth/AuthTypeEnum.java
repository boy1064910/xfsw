package com.ptmind.datadeck.entity.datasource.auth;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ptmind.datadeck.util.JsonUtil;



/**
 * 数据源连接类型枚举
 * @author lxp
 * @version 3.0.0
 */
public enum AuthTypeEnum {

	OAUTH2("oauth2","oAuth2授权",AuthOAuth2Config.class),
	API("api","远程API授权",AuthRestConfig.class);
	
	private String authType;//连接方式类型
	private String connectName;//连接方式名称
	private Class<?> clazz;//连接方式对应的连接配置json转换类
	
	private AuthTypeEnum(String authType,String connectName,Class<?> clazz) {
		this.clazz = clazz;
		this.connectName = connectName;
		this.authType = authType;
	}
	
	/**
	 * 根据连接类型,自动将json数据实例化成相应的实体
	 * @param connectType
	 * @param configJson
	 * @return
	 * @author lxp
	 * @version 3.0.0
	 */
	public static AbstractAuthConfig initAuthConfig(String authType,String configJson) {
		AbstractAuthConfig authConfig = null;
		for(AuthTypeEnum e:AuthTypeEnum.values()) {
			if(e.authType.equals(authType)) {
				authConfig = (AbstractAuthConfig) JsonUtil.json2Entity(configJson, e.clazz);
				break;
			}
		}
		return authConfig;
	}
	
	public String getAuthType(){
		return this.authType;
	}
	
	/**
	 * TODO 获取连接配置相关信息list,提供给界面化数据源配置那一步调用
	 * @return	有序的map,key为数据库存储的值,value为显示的连接类型名称
	 * @author lxp
	 * @version 3.0.0
	 */
	public static Map<String,String> readAuthTypes() {
		Map<String,String> map = new LinkedHashMap<String,String>(AuthTypeEnum.values().length);
		for(AuthTypeEnum e:AuthTypeEnum.values()) {
			map.put(e.authType, e.connectName);
		}
		return map;
	}
}
