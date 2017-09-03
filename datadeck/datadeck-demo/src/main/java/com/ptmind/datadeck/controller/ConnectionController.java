package com.ptmind.datadeck.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptmind.datadeck.entity.Connection;
import com.ptmind.datadeck.entity.datasource.ConfigRequestInfoDataSource;
import com.ptmind.datadeck.entity.datasource.config.ConfigStepRequestInfo;
import com.ptmind.datadeck.entity.datasource.config.request.RequestParam;
import com.ptmind.datadeck.entity.datasource.config.request.RequestSetting;
import com.ptmind.datadeck.model.connection.ConnectionAccountInfo;
import com.ptmind.datadeck.model.connection.ResponseNode;
import com.ptmind.datadeck.service.ConnectionService;
import com.ptmind.datadeck.service.ConnectionTokenService;
import com.ptmind.datadeck.service.DataSourceService;
import com.xfsw.common.util.HttpRequestUtil;
import com.xfsw.common.util.ListUtil;
import com.xfsw.common.util.StringUtil;


/**
 * 
 * @author lxp
 * @version 
 */
@RestController
@RequestMapping("/connection")
public class ConnectionController {

	@Resource(name="dataSourceService")
	DataSourceService dataSourceService;
	
	@Resource(name="connectionService")
	ConnectionService connectionService;
	
	@Resource(name="connectionTokenService")
	ConnectionTokenService connectionTokenService;
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	/**
	 * 初始化数据源连接信息
	 * @param clientId	第三方平台开发者账号client id
	 * @param clientKey	第三方平台开发者账号client 秘钥
	 * @param code	数据源代码
	 * @return
	 * @author xiaopeng.liu
	 * @version 3.0.0
	 */
	@PostMapping("/initSaasConnection")
	public String initSaasConnection(String clientId,String clientKey,String code) {
		//TODO 后期判断该clientId是否已经连接过数据源,同时
		Connection connection = new Connection();
		String id = StringUtil.getRandomString(20);
		connection.setId(id);
		connection.setClientId(clientId);
		connection.setClientKey(clientKey);
		connection.setCode(code);
		connection.setOwnerId(Long.valueOf(1));
		connection.setTenantId("1");
		connectionService.initConnection(connection);
		return clientId;
	}
	
	/**
	 * 查询数据源下的账号列表
	 * @param code	数据源代码
	 * @return
	 * @author xiaopeng.liu
	 * @version 3.0.0
	 */
	@GetMapping("/readAllAccountInfos")
	public List<ConnectionAccountInfo> readAllAccountInfos(String code) {
		String ownerId = "1";
		String tenantId = "1";
		return connectionService.readAccountInfosByCode(ownerId, tenantId,code);
	}
	
	/**
	 * 数据连接步骤的数据来源请求,统一请求此接口
	 * @param code
	 * @param stepIndex
	 * @return
	 * @author xiaopeng.liu
	 * @version
	 */
	@GetMapping("/readStepDataList")
	public List<ResponseNode> readStepDataList(String code,int stepIndex,String connectionId) {
		ConfigRequestInfoDataSource configRequestInfoDataSource = dataSourceService.readConfigRequestInfoByCode(code);
		ConfigStepRequestInfo[] steps = configRequestInfoDataSource.getSteps();
		ConfigStepRequestInfo configStepRequestInfo = steps[stepIndex];
		String token = "";
		if(configStepRequestInfo.checkNeedToken()) {
			token = connectionTokenService.getTokenByConnectionId(connectionId);
		}
		List<RequestSetting> requestSettings = configStepRequestInfo.getRequestSettings();
		List<ResponseNode> responseNodeList = null;
		if(!ListUtil.isEmpty(requestSettings)) {
			if(requestSettings.size()==1) {
				RequestSetting requestSetting = requestSettings.get(0);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				if(requestSetting.getNeedToken()) {
					params.add(new BasicNameValuePair("access_token", token));
				}
				if(requestSetting.getParams()!=null) {
					for(RequestParam requestParam:requestSetting.getParams()) {
						params.add(new BasicNameValuePair(requestParam.getKey(), requestParam.getValue()));
					}
				}
				//TODO 考虑分页的情况 
				String responseBody = HttpRequestUtil.get(requestSetting.getUrl(), params);
				responseNodeList = configStepRequestInfo.resolveResponse(responseBody);
			}
			else {
				//TODO 后续多种list拼装
			}
		}
		return responseNodeList;
	}
	
	@GetMapping("/readDataValue")
	public void readDataValue() {
		
	}
}
