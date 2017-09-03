package com.ptmind.datadeck.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ptmind.datadeck.entity.DataSource;
import com.ptmind.datadeck.entity.datasource.AuthConfigDataSource;
import com.ptmind.datadeck.entity.datasource.ConfigDataSource;
import com.ptmind.datadeck.entity.datasource.ConfigRequestInfoDataSource;
import com.ptmind.datadeck.entity.datasource.EditorDataSource;
import com.ptmind.datadeck.entity.datasource.field.FieldParseConfig;
import com.ptmind.datadeck.service.DataSourceService;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.ListUtil;

/**
 * TODO 后期检查代码,所有实现中加上缓存
 * Created by Garry on 2017/8/23.
 */
@Service("dataSourceService")
public class DataSourceServiceImpl implements DataSourceService{

	@Resource(name="ddCommonMapper")
	ICommonMapper ddCommonMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AuthConfigDataSource> readAllDefinitions() {
		List<DataSource> dataSourceList = (List<DataSource>) ddCommonMapper.selectAll(DataSource.class);
		if(!ListUtil.isEmpty(dataSourceList)) {
			List<AuthConfigDataSource> resultList = new ArrayList<AuthConfigDataSource>(dataSourceList.size());
			for(DataSource dataSource:dataSourceList) {
				resultList.add(new AuthConfigDataSource(dataSource));
			}
			return resultList;
		}
		return null;
	}

	@Override
	public AuthConfigDataSource readAuthConfigInfo(String code) {
		DataSource dataSource = this.getByCode(code);
		return new AuthConfigDataSource(dataSource);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConfigDataSource> readConfigDataSourceList(){
		List<DataSource> dataSourceList = (List<DataSource>) ddCommonMapper.selectAll(DataSource.class);
		List<ConfigDataSource> configDataSourceList = new ArrayList<ConfigDataSource>();
		for(DataSource dataSource:dataSourceList) {
			configDataSourceList.add(new ConfigDataSource(dataSource));
		}
		return configDataSourceList;
	}
	
	public ConfigRequestInfoDataSource readConfigRequestInfoByCode(String code) {
		DataSource dataSource = this.getByCode(code);
		return new ConfigRequestInfoDataSource(dataSource);
	}
	
	public FieldParseConfig getFieldParseConfigByCode(String code) {
		DataSource dataSource = this.getByCode(code);
		FieldParseConfig fieldParseConfig = (FieldParseConfig) JsonUtil.json2Entity(dataSource.getFieldConfig(), FieldParseConfig.class);
		return fieldParseConfig;
	}
	
	/**
	 * 通过code获取数据源信息
	 * @param code
	 * @return
	 * @author xiaopeng.liu
	 * @version
	 */
	private DataSource getByCode(String code) {
		//TODO 后期优化加上数据源信息缓存
		return (DataSource) ddCommonMapper.get("DataSource.readByCode",code);
	}

	@Override
	public EditorDataSource readEditorInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}


}
