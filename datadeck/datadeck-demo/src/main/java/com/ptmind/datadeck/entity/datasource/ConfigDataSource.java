package com.ptmind.datadeck.entity.datasource;

import java.util.List;

import com.ptmind.datadeck.entity.DataSource;
import com.ptmind.datadeck.entity.datasource.config.ConfigStep;
import com.xfsw.common.util.JsonUtil;

/**
 * 数据源选择配置操作的结构体(提供给编辑器数据源选择配置的接口使用)
 * @author lxp
 * @version 3.0.0
 */
public class ConfigDataSource extends AbstractDataSource {

	private ConfigStep[] steps;
	
	@SuppressWarnings("unchecked")
	public ConfigDataSource(DataSource dataSource) {
		super(dataSource);
		List<ConfigStep> stepList = (List<ConfigStep>) JsonUtil.json2List(dataSource.getStepConfig(), ConfigStep.class);
		this.steps = new ConfigStep[stepList.size()];
		stepList.toArray(this.steps);
	}

	public ConfigStep[] getSteps() {
		return steps;
	}

	public void setSteps(ConfigStep[] steps) {
		this.steps = steps;
	}
}

