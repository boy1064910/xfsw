package com.ptmind.datadeck.service;

import java.util.List;
import java.util.Map;

import com.ptmind.datadeck.entity.connection.AbstractConnection;

/**
 * Created by Garry on 2017/7/21.
 */
public interface SaaSDataSourceService extends DataSourceService {

    List<Map<String, Object>> getData(String code, AbstractConnection dataSource, Map<String, Object> param);

}


