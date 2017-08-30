package com.ptmind.datadeck.entity.datasource.editor;

/**
 * 编辑器配置信息 
 * @author lxp
 * @version 
 */
public abstract class AbstractEditorConfig {
	private Boolean enableSegment = false; //是否支持细分,默认不支持
	private Graph[] graphs;//支持的图表类型列表
	private TimeConditionConfig timeConditionConfig;//支持的时间范围条件列表
	
	public Graph[] getGraphs() {
		return graphs;
	}
	public void setGraphs(Graph[] graphs) {
		this.graphs = graphs;
	}
	public TimeConditionConfig getTimeConditionConfig() {
		return timeConditionConfig;
	}
	public void setTimeConditionConfig(TimeConditionConfig timeConditionConfig) {
		this.timeConditionConfig = timeConditionConfig;
	}
	public Boolean getEnableSegment() {
		return enableSegment;
	}
	public void setEnableSegment(Boolean enableSegment) {
		this.enableSegment = enableSegment;
	}
}

/**
 * 编辑器数据展示类型结构体
 * @author lxp
 * @version
 */
class Graph{
	private String name;//数据展示类型名称
	private String code;//数据展示类型代码
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}

/**
 * 编辑器时间条件结构体
 * @author lxp
 * @version 3.0.0
 */
class TimeConditionConfig{
	private String defaultCode;//默认时间条件code
	private TimeCondition[] items;//时间条件列表
	
	public TimeCondition[] getItems() {
		return items;
	}
	public void setItems(TimeCondition[] items) {
		this.items = items;
	}
	public String getDefaultCode() {
		return defaultCode;
	}
	public void setDefaultCode(String defaultCode) {
		this.defaultCode = defaultCode;
	}
}

/**
 * 编辑器时间条件中的具体选项结构体
 * @author lxp
 * @version 3.0.0
 */
class TimeCondition{
	private String code;//时间条件代号
	private String name;//时间显示名称
	private String type;//时间选择器条件类型
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}