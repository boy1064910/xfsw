package com.ptmind.datadeck.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ptmind.datadeck.entity.Connection;
import com.ptmind.datadeck.entity.datasource.AuthConfigDataSource;
import com.ptmind.datadeck.entity.datasource.ConfigDataSource;
import com.ptmind.datadeck.entity.datasource.auth.AuthOAuth2Config;
import com.ptmind.datadeck.entity.datasource.auth.request.AuthRequestParam;
import com.ptmind.datadeck.entity.datasource.field.FieldParseConfig;
import com.ptmind.datadeck.model.datasource.DataSourceAuthConfig;
import com.ptmind.datadeck.service.ConnectionService;
import com.ptmind.datadeck.service.ConnectionTokenService;
import com.ptmind.datadeck.service.DataSourceService;
import com.ptmind.datadeck.service.FieldService;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.util.ArrayUtil;
import com.xfsw.common.util.HttpRequestUtil;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.ListUtil;
import com.xfsw.common.util.ReflectUtil;

/**
 * 
 * @author lxp
 * @version
 */
@Controller
@RequestMapping("/datasource")
public class DataSourceController {

	@Resource(name = "dataSourceService")
	DataSourceService dataSourceService;

	@Resource(name = "connectionService")
	ConnectionService connectionService;

	@Resource(name = "connectionTokenService")
	ConnectionTokenService connectionTokenService;
	
	@Resource(name="fieldService")
	FieldService fieldService;

	/**
	 * 读取数据源配置信息,供数据源管理界面调用
	 * 
	 * @return
	 * @author xiaopeng.liu
	 * @version
	 */
	@RequestMapping("/readAllDefinitions")
	@ResponseBody
	public ResponseModel readAllDefinitions() {
		List<AuthConfigDataSource> dataSourceList = dataSourceService.readAllDefinitions();
		List<DataSourceAuthConfig> modelList = new ArrayList<DataSourceAuthConfig>();
		if(!ListUtil.isEmpty(dataSourceList)) {
			for(AuthConfigDataSource dataSource:dataSourceList) {
				DataSourceAuthConfig model = new DataSourceAuthConfig();
				//TODO 此处后期可利用缓存记录类信息优化
				ReflectUtil.copyValue(dataSource, model, true);
				modelList.add(model);
			}
		}
		return new ResponseModel(modelList);
	}
	
	/**
	 * saas接口的获取token统一返回url
	 * @param code
	 * @param state
	 * @param request
	 * @author xiaopeng.liu
	 * @version
	 */
	@RequestMapping("/getToken")
	@ResponseBody
	public void getToken(String code, String state, HttpServletRequest request) {
		// TODO 后期完善,加入填写的第三方账号信息填写错误,获取code失败的情况,删除已保存的连接信息,返回页面提示错误
		// 获取数据源配置信息
		Connection connection = connectionService.readByClientId(state);
		AuthConfigDataSource dataSource = dataSourceService.readAuthConfigInfo(connection.getCode());
		AuthOAuth2Config authOAuth2Config = (AuthOAuth2Config) dataSource.getAuthConfig();

		String tokenUrl = authOAuth2Config.getTokenUrl();
		AuthRequestParam[] tokenParams = authOAuth2Config.getTokenParams();

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 部分参数从授权token的请求参数配置中获取
		if (!ArrayUtil.isEmpty(tokenParams)) {
			for (AuthRequestParam param : tokenParams) {
				params.add(new BasicNameValuePair(param.getKey(), param.getValue()));
			}
		}
		// 固定逻辑
		params.add(new BasicNameValuePair("code", code));
		// TODO 如果后期关于key各个数据源都是不一样的,则在AuthOAuth2Config类中添加clientId和key对应的参数名称
		params.add(new BasicNameValuePair("client_id", connection.getClientId()));
		params.add(new BasicNameValuePair("client_secret", connection.getClientKey()));

		// TODO 后期需要对httpclient做出连接池
		String tokenResult = HttpRequestUtil.post(tokenUrl, params, "UTF-8");
		System.out.println(tokenResult);
		Map<?, ?> resultMap = JsonUtil.json2Map(tokenResult);

		String token = resultMap.get("access_token").toString();
		int timeout = (int) resultMap.get("expires_in");
		// TODO 保存缓存信息
		connectionTokenService.setToken(connection.getId(), token, timeout, TimeUnit.SECONDS);

		// TODO 获取账号信息,后期根据数据源优化此处的配置字段和逻辑
		String accountResponseInfo = (String) resultMap.get(authOAuth2Config.getAccountResultKey());
		params.clear();
		params.add(new BasicNameValuePair(authOAuth2Config.getAccountParamKey(), accountResponseInfo));
		String accountResult = HttpRequestUtil.post(authOAuth2Config.getAccountUrl(), params, "UTF-8");
		resultMap = JsonUtil.json2Map(accountResult);
		String accountInfo = (String) resultMap.get(authOAuth2Config.getAccountParseKey());
		// TODO 保存到数据库中
		connectionService.saveConnectionAccountInfo(state, accountInfo);
	}

	/**
	 * 查询数据源基础信息
	 * 
	 * @return
	 * @author xiaopeng.liu
	 * @version
	 */
	@RequestMapping("/readConfigDataSourceList")
	@ResponseBody
	public List<ConfigDataSource> readConfigDataSourceList() {
		return dataSourceService.readConfigDataSourceList();
	}
	
	@RequestMapping("/readFieldList")
	@ResponseBody
	public ResponseModel readFieldList(String type,String code,String connectionId) {
		FieldParseConfig fieldParseConfig = dataSourceService.getFieldParseConfigByCode(code);
		return new ResponseModel(fieldService.readFieldList(code, connectionId, type, fieldParseConfig));
	}
}
