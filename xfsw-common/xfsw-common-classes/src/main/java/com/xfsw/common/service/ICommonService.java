package com.xfsw.common.service;

import java.util.List;
import java.util.Map;

import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;
import com.xfsw.common.mapper.ICommonMapper;

/**
 * 业务服务层公共接口,可通过方法直接调用数据库操作执行接口
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface ICommonService {

	ICommonMapper getCommonMapper();
	
	DataTableResponseModel selectPageBySql(String countSql,String dataSql,DataTablePageInfo pageInfo, Map<String, Object> params);
	
	List<?> selectListBySql(String sql,Map<String,Object> params);
}
