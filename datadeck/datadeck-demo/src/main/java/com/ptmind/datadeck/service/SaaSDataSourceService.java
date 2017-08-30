package com.ptmind.datadeck.service;

import java.util.List;
import java.util.Map;

import com.ptmind.datadeck.entity.connection.AbstractConnection;

/**
 * Created by Garry on 2017/7/21.
 */
public interface SaaSDataSourceService extends DataSourceService {

	//TODO 可删除
//    List<ConnectStep> getConnectSteps(String code);	关于授权认证接口在general中定义+实现readAuthConfigInfo--(可支持saas和dataset)

	//TODO 可删除
//    EditorConfig getEditorConfig(String code);关于数据源编辑器功能初始化接口在general中定义+实现readEditorInfos--(可支持saas和dataset)

	//TODO 可删除
//    List<EditorStep> getEditorSteps(String code);关于数据源连接步骤接口在general中定义+实现readEditorSteps--(可支持saas和dataset)

//    List<Profile> getProfile(String code, DataSource dataSource, Map<String, Object> params);
//
//    List<Report> getReport(String code, DataSource dataSource, Map<String, Object> params);
//
//    List<Segment> getSegments(String code, DataSource dataSource, Map<String, Object> params);
//
//    List<Filter> getFilters(String code, DataSource dataSource, Map<String, Object> params);
//
//    List<DateFilter> getDateFilters(String code, DataSource dataSource, Map<String, Object> params);
//
//    List<Field> getFields(String code, DataSource dataSource, Map<String, Object> params);

    List<Map<String, Object>> getData(String code, AbstractConnection dataSource, Map<String, Object> param);

}


