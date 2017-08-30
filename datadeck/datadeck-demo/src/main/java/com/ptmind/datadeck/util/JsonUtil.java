package com.ptmind.datadeck.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Garry on 2017/7/21.
 */
public class JsonUtil {

	public static Map<String, Object> unformat(String extensions) {
		return JSON.parseObject(extensions, HashMap.class);
	}

	/**
	 * Map 转成 T Class
	 *
	 * @param map    the map
	 * @param tClass the T class
	 * @param <T>    泛型
	 * @return <T> 实际类型
	 * @author shaoqiang.guo
	 */
	public static <T> T unformat(Map<String, Object> map, Class<T> tClass) {
		String configJson = JSON.toJSONString(map);
		return JSON.parseObject(configJson, tClass);
	}

	public static String format(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * json字符串转实体
	 * @param json
	 * @param clazz
	 * @return
	 * @author lxp
	 * @version 3.0.0
	 */
	public static <T> T json2Entity(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

}
