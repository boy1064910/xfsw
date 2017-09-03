package com.ptmind.datadeck.service;

import java.util.List;

import com.ptmind.datadeck.entity.datasource.AuthConfigDataSource;
import com.ptmind.datadeck.entity.datasource.ConfigDataSource;
import com.ptmind.datadeck.entity.datasource.ConfigRequestInfoDataSource;
import com.ptmind.datadeck.entity.datasource.EditorDataSource;
import com.ptmind.datadeck.entity.datasource.field.FieldParseConfig;

/**
 * Created by Garry on 2017/8/23.
 */
public interface DataSourceService {

	/**
	 * 获取所有数据源配置的列表数据(包括数据源授权配置信息)
	 * @return
	 * @author lxp
	 * @version 3.0.0
	 */
    List<AuthConfigDataSource> readAllDefinitions();
    
    /**
     * 获取数据源授权配置信息,前端根据不同的授权配置展示页面(包括点击新增连接或者连接之后的操作逻辑所需要的数据信息)
     * @param id	数据源ID
     * @return
     * @author lxp
     * @version 3.0.0
     */
    AuthConfigDataSource readAuthConfigInfo(String code);
    
    /**
     * 获取数据源连接步骤信息
     * @return
     * @author xiaopeng.liu
     * @version 3.0.0
     */
    List<ConfigDataSource> readConfigDataSourceList();
    
    /**
     * 读取某个数据源的步骤信息(携带第三方api请求信息)
     * @param code
     * @return
     * @author xiaopeng.liu
     * @version
     */
    ConfigRequestInfoDataSource readConfigRequestInfoByCode(String code);
    
    /**
     * 获取数据源字段解析配置
     * @param code
     * @return
     * @author xiaopeng.liu
     * @version
     */
    FieldParseConfig getFieldParseConfigByCode(String code);
    
    /**
     * 获取数据源的编辑器配置信息
     * @param id
     * @return
     * @author lxp
     * @version 3.0.0
     */
    EditorDataSource readEditorInfo(String id);

}
