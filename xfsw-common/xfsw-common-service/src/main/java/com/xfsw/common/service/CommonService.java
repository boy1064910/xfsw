package com.xfsw.common.service;

import java.util.Map;

import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;

public abstract class CommonService implements ICommonService {

	public DataTableResponseModel selectPageBySql(String countSql,String dataSql,DataTablePageInfo pageInfo, Map<String, Object> params){
		return this.getCommonMapper().selectPageBySql(countSql, dataSql, pageInfo, params);
	}
	
//	public List<?> selectListBySql(String sql,Map<String,Object> params){
//		return this.getCommonMapper().selectListBySql(sql, params);
//	}
}
