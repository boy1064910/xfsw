package com.ptmind.datadeck.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ptmind.datadeck.entity.DataSource;
import com.ptmind.datadeck.entity.datasource.AuthConfigDataSource;
import com.ptmind.datadeck.entity.datasource.ConfigDataSource;
import com.ptmind.datadeck.entity.datasource.ConfigRequestInfoDataSource;
import com.ptmind.datadeck.entity.datasource.EditorDataSource;
import com.xfsw.common.mapper.ICommonMapper;
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
		DataSource dataSource = (DataSource) ddCommonMapper.get("DataSource.readByCode",code);
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
		DataSource dataSource = (DataSource) ddCommonMapper.get("DataSource.readByCode",code);
		return new ConfigRequestInfoDataSource(dataSource);
	}

	@Override
	public EditorDataSource readEditorInfo(String id) {
		// TODO Auto-generated method stub
		return null;
	}


}
