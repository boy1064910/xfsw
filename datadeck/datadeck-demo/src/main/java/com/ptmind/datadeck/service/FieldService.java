/**
 * 
 */
package com.ptmind.datadeck.service;

import java.util.Map;

import com.ptmind.datadeck.entity.datasource.field.FieldParseConfig;

/**
 * 数据源字段接口服务
 * @author xiaopeng.liu
 * @version 
 */
public interface FieldService {
	
	/**
	 * 获取数据源连接的字段信息 
	 * @param code
	 * @param connectionId
	 * @param type
	 * @param fieldParseConfig
	 * @return
	 * @author xiaopeng.liu
	 * @version
	 */
	Map<?,?> readFieldList(String code,String connectionId,String type,FieldParseConfig fieldParseConfig);
}
