package com.ptmind.datadeck.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ptmind.datadeck.entity.datasource.field.FieldParseConfig;
import com.ptmind.datadeck.entity.datasource.field.FieldParseSetting;
import com.ptmind.datadeck.model.datasource.Field;
import com.ptmind.datadeck.service.FieldService;
import com.xfsw.common.util.HttpRequestUtil;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.StringUtil;

@Service("fieldService")
public class FieldServiceImpl implements FieldService {

	@Resource(name="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	

	public Map<?,?> readFieldList(String code,String connectionId,String fieldType,FieldParseConfig fieldParseConfig){
		
		String json = redisTemplate.opsForValue().get(code+"-"+fieldType+"-"+connectionId);
		if(!StringUtil.isEmpty(json)) {
			return JsonUtil.json2Map(json);
		}
		
		Map<String,List<Field>> dimensionMap = new HashMap<String,List<Field>>();
		Map<String,List<Field>> metricMap = new HashMap<String,List<Field>>();
		Map<String,List<Field>> filterMap = new HashMap<String,List<Field>>();
		Map<String,List<Field>> segmentMap = new HashMap<String,List<Field>>();
		
		this.loadFieldInfo(fieldParseConfig, dimensionMap, metricMap, filterMap, segmentMap);
		redisTemplate.opsForValue().set(code+"-DIMENSION-"+connectionId,JsonUtil.entity2Json(dimensionMap));
		redisTemplate.opsForValue().set(code+"-METRIC-"+connectionId,JsonUtil.entity2Json(metricMap));
		redisTemplate.opsForValue().set(code+"-FILTER-"+connectionId,JsonUtil.entity2Json(filterMap));
		redisTemplate.opsForValue().set(code+"-SEGMENT-"+connectionId,JsonUtil.entity2Json(segmentMap));
		
		switch(fieldType.toUpperCase()) {
			case "DIMENSION":{
				return dimensionMap;
			}
			case "METRIC":{
				return metricMap;
			}
			case "FILTER":{
				return filterMap;
			}
			case "SEGMENT":{
				return segmentMap;
			}
			default:return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadFieldInfo(FieldParseConfig fieldParseConfig,
			Map<String,List<Field>> dimensionMap,
			Map<String,List<Field>> metricMap,
			Map<String,List<Field>> filterMap,
			Map<String,List<Field>> segmentMap
			) {
		String result = HttpRequestUtil.get(fieldParseConfig.getUrl());
		Map<?, ?> resultMap = JsonUtil.json2Map(result);
		
		List<Map<?,?>> itemList = (List<Map<?, ?>>) resultMap.get(fieldParseConfig.getItemsKey());
		for(Map<?,?> map:itemList) {
			FieldParseSetting fieldParseSetting = fieldParseConfig.getParseSetting();
			String id = recurrentParse(map, fieldParseSetting.getIdKey().split("/"), 0);
			String name = recurrentParse(map, fieldParseSetting.getNameKey().split("/"), 0);
			String group = recurrentParse(map, fieldParseSetting.getGroupKey().split("/"), 0);
			String dataType = recurrentParse(map, fieldParseSetting.getDataTypeKey().split("/"), 0);
			String type = recurrentParse(map, fieldParseSetting.getTypeKey().split("/"), 0);
			String description = recurrentParse(map, fieldParseSetting.getDescriptionKey().split("/"), 0);
			String allowedSegment = recurrentParse(map, fieldParseSetting.getAllowedSegmentsKey().split("/"), 0);
			allowedSegment = allowedSegment==null?"FALSE":allowedSegment;
			
			if(type.toUpperCase().equals("DIMENSION")) {
				if(!dimensionMap.containsKey(group)) {
					dimensionMap.put(group, new ArrayList<Field>());
				}
				dimensionMap.get(group).add(new Field(id, name, description, dataType));
			}
			else if(type.toUpperCase().equals("METRIC")) {
				if(!metricMap.containsKey(group)) {
					metricMap.put(group, new ArrayList<Field>());
				}
				metricMap.get(group).add(new Field(id, name, description, dataType));
			}
			else {
				//TODO logger.error记录
			}
			if(allowedSegment.toUpperCase().equals("TRUE")) {
				if(!segmentMap.containsKey(group)) {
					segmentMap.put(group, new ArrayList<Field>());
				}
				segmentMap.get(group).add(new Field(id, name, description, dataType));
			}
		}
	}
	
	private String recurrentParse(Map<?,?> map,String[] keys,int indexLevel) {
		if(indexLevel<keys.length-1) {
			Map<?,?> tmpMap = (Map<?, ?>) map.get(keys[indexLevel]);
			indexLevel++;
			return recurrentParse(tmpMap, keys, indexLevel);
		}
		else if(indexLevel==keys.length-1) {
			return (String) map.get(keys[indexLevel]);
		}
		else 
			return null;
	}
	
}
