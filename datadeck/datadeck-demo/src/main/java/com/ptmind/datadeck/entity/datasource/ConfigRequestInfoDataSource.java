package com.ptmind.datadeck.entity.datasource;

import java.util.List;

import com.ptmind.datadeck.entity.DataSource;
import com.ptmind.datadeck.entity.datasource.config.ConfigStepRequestInfo;
import com.xfsw.common.util.JsonUtil;

/**
 * 携带配置步骤中第三方api请求信息的数据源结构体
 * @author lxp
 * @version 3.0.0
 */
public class ConfigRequestInfoDataSource extends AbstractDataSource {

	private ConfigStepRequestInfo[] steps;
	
	@SuppressWarnings("unchecked")
	public ConfigRequestInfoDataSource(DataSource dataSource) {
		super(dataSource);
		List<ConfigStepRequestInfo> stepList = (List<ConfigStepRequestInfo>) JsonUtil.json2List(dataSource.getStepConfig(), ConfigStepRequestInfo.class);
		this.steps = new ConfigStepRequestInfo[stepList.size()];
		stepList.toArray(this.steps);
	}

	public ConfigStepRequestInfo[] getSteps() {
		return steps;
	}

	public void setSteps(ConfigStepRequestInfo[] steps) {
		this.steps = steps;
	}
}

