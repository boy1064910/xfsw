package com.xfsw.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JsonUtil {

	public static String entity2Json(Object entity) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(entity);
			return json;
		} catch (Exception e) {
			throw new RuntimeException("实体类转换json字符串失败!", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, Map.class);
		} catch (IOException e) {
			throw new RuntimeException("json转map出错!", e);
		}
	}

	public static <T> T json2Entity(String json, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("json转entity出错!", e);
		}
	}

	public static <T> List<T> json2List(String json, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
		try {
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			throw new RuntimeException("json转entity出错!", e);
		}
	}
	
	private static List<Map<String, Object>> recurrentParseJsonList(String json) {
		List<Map<String, Object>> jsonMapList = new ArrayList<Map<String, Object>>();
		if (json != null && !json.trim().equals("")) {
			json = json.trim().substring(1, json.length() - 1);// 去除[]符号
			String[] jsonStrings = json.split(",");
			for (String content : jsonStrings) {
				jsonMapList.add(recurrentParseJsonMap(content));
			}
		}
		return jsonMapList;
	}

	private static Map<String, Object> recurrentParseJsonMap(String json) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (json != null && !json.trim().equals("")) {
			json = json.trim().substring(1, json.length() - 1);// 去除{}符号
			String[] jsonStrings = json.split(",");
			for (String content : jsonStrings) {
				String[] contents = content.split(":");
				String key = contents[0].trim();
				String value = contents[1].trim();
				if (checkJson(value) == 1) {
					jsonMap.put(key, recurrentParseJsonList(value));
				} else if (checkJson(value) == 2) {
					jsonMap.put(key, recurrentParseJsonMap(value));
				} else {
					jsonMap.put(key, value);
				}
			}
		}
		return jsonMap;
	}

	/**
	 * 检查json的格式类型
	 * 
	 * @param json
	 * @return 1：数组格式 2：map格式 3：字符串格式
	 */
	private static int checkJson(String json) {
		json = json.trim();
		if (json.startsWith("[")) {
			if (json.endsWith("]"))
				return 1;
			else
				throw new RuntimeException("json格式错误,json内容：" + json);
		} else if (json.startsWith("{")) {
			if (json.endsWith("}"))
				return 2;
			else
				throw new RuntimeException("json格式错误,json内容：" + json);
		} else {
			return 3;
		}
	}
}

class ResultNode{
	String id;
	String name;
	List<ResultNode> children = new ArrayList<ResultNode>();
	
	public ResultNode(String id,String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ResultNode> getChildren() {
		return children;
	}
	public void setChildren(List<ResultNode> children) {
		this.children = children;
	}
}

