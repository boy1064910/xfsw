package com.ptmind.datadeck.entity.datasource.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ptmind.datadeck.entity.datasource.config.request.RequestSetting;
import com.ptmind.datadeck.entity.datasource.config.response.ResponseNode;
import com.ptmind.datadeck.entity.datasource.config.response.ResponseSetting;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.ListUtil;

/**
 * 配置步骤中携带的第三方api请求信息数据结构
 * 
 * @author xiaopeng.liu
 * @version 3.0.0
 */
public class ConfigStepRequestInfo extends ConfigStep {

	List<RequestSetting> requestSettings;

	List<ResponseSetting> responseSettings;

	public boolean checkNeedToken() {
		boolean result = false;
		if (!ListUtil.isEmpty(requestSettings)) {
			for (RequestSetting requestSetting : requestSettings) {
				if (requestSetting.getNeedToken()) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<ResponseNode> resolveResponse(String json) {
		Map<?, ?> map = JsonUtil.json2Map(json);
		int levelIndex = 0;
		ResponseSetting responseSetting = this.responseSettings.get(levelIndex);
		if (!map.containsKey(responseSetting.getItemKey()))
			return null;// TODO 如果不存在报错提示

		List<Map<?, ?>> itemList = (List<Map<?, ?>>) map.get(responseSetting.getItemKey());
		List<ResponseNode> responseNodeList = new ArrayList<ResponseNode>();
		for (Map<?, ?> itemMap : itemList) {
			String idKey = responseSetting.getIdKey();
			String valueKey = responseSetting.getNameKey();
			ResponseNode resultNode = new ResponseNode(itemMap.get(idKey).toString(), itemMap.get(valueKey).toString());
			recurrentParse(itemMap, levelIndex + 1, resultNode);
			responseNodeList.add(resultNode);
		}
		return responseNodeList;
	}

	@SuppressWarnings("unchecked")
	private void recurrentParse(Map<?, ?> map, int levelIndex, ResponseNode responseNode) {
		if (levelIndex >= this.responseSettings.size())
			return;
		if (!map.containsKey(this.responseSettings.get(levelIndex)))
			return;
		List<Map<?, ?>> itemList = (List<Map<?, ?>>) map.get(this.responseSettings.get(levelIndex).getItemKey());
		for (Map<?, ?> itemMap : itemList) {
			ResponseSetting responseSetting = this.responseSettings.get(levelIndex);
			String idKey = responseSetting.getIdKey();
			String valueKey = responseSetting.getNameKey();
			ResponseNode resultNode = new ResponseNode(itemMap.get(idKey).toString(), itemMap.get(valueKey).toString());
			recurrentParse(itemMap, levelIndex + 1, resultNode);
			responseNode.getChildren().add(resultNode);
		}
	}

	public List<RequestSetting> getRequestSettings() {
		return requestSettings;
	}

	public void setRequestSettings(List<RequestSetting> requestSettings) {
		this.requestSettings = requestSettings;
	}

	public List<ResponseSetting> getResponseSettings() {
		return responseSettings;
	}

	public void setResponseSettings(List<ResponseSetting> responseSettings) {
		this.responseSettings = responseSettings;
	}
}
