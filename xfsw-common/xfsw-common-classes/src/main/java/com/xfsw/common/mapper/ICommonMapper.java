package com.xfsw.common.mapper;

import java.util.List;
import java.util.Map;

import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;

/**
 * 操作数据库层公共接口
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface ICommonMapper {
	
	boolean check(String sqlId);
	
	boolean check(String sqlId,Object entity);
	
	boolean check(String sqlId,Map<String,Object> params);
	
	/**
	 * 通过mybatis定义的sqlId关联语句查询单条数据
	 * @param sqlId	Mybatis中定义的SQL语句Id
	 * @return	返回结果为单条数据，结果泛型请查看Mybatis中定义的sqlId
	 */
	Object get(String sqlId);
	
	/**
	 * 通过mybatis定义的sqlId关联语句+参数值查询单条数据
	 * @param sqlId	Mybatis中定义的SQL语句Id
	 * @param entity	查询参数值
	 * @return	返回结果为单条数据，结果泛型请查看Mybatis中定义的sqlId
	 */
	Object get(String sqlId,Object entity);
	
	Object get(Class<? extends Object> clazz,Integer id);
	
	Object get(Class<? extends Object> clazz,Class<? extends Object> targetClazz,Integer id);
	
	Object get(Class<? extends Object> clazz,Map<String,Object> params);
	
	Object get(Class<? extends Object> clazz,Object entity);
	
	Object get(Class<? extends Object> clazz,String fieldName,Object value);
	
	Object get(Class<? extends Object> clazz,Class<? extends Object> targetClazz,String fieldName,Object value);
	
	/**
	 * 通过mybatis定义的sqlId关联语句查询多条数据
	 * @param sqlId	Mybatis中定义的SQL语句Id
	 * @return	返回结果为数组，结果泛型请查看Mybatis中定义的sqlId
	 */
	List<?> selectList(String sqlId);
	
	/**
	 * 通过mybatis定义的sqlId关联语句+参数值查询多条数据
	 * @param sqlId	Mybatis中定义的SQL语句Id
	 * @param object	查询参数值
	 * @return	返回结果为数组，结果泛型请查看Mybatis中定义的sqlId
	 */
	List<?> selectList(String sqlId, Object object);
	
	List<?> selectList(String sqlId, Map<String,Object> params);
	
	List<?> selectList(Class<? extends Object> clazz);
	
	List<?> selectList(Class<? extends Object> clazz,Map<String,Object> params);
	
	List<?> selectList(Class<? extends Object> clazz,Object entity);
	
	List<?> selectAll(Class<? extends Object> clazz);
	
	/**
	 * 通过SQL语句查询数据（严禁“请求带的参数”作为SQL拼接，防止SQL注入）
	 * @param sql	SQL语句，不应有SQL拼接参数的情况
	 * @return	返回结果为数组
	 */
	List<?> selectListBySql(String sql);
	
	/**
	 * 通过SQL语句和参数查询数据
	 * @param sql	SQL语句，遵循Mybatis SQL语句规则
	 * @param params	SQL语句中的参数
	 * @return	返回结果为数组
	 */
	List<?> selectListBySql(String sql,Map<String,Object> params);
	
	List<?> selectListInPageBySql(String dataSqlId,DataTablePageInfo pageInfo, Map<String, Object> params);
	
	/**
	 * 分页数据查询，返回DataTable需要的数据格式
	 * @param countSql
	 * @param dataSql
	 * @param pageInfo
	 * @param params
	 * @return
	 * @author liuxifan
	 */
	DataTableResponseModel selectPageBySql(String countSql,String dataSql,DataTablePageInfo pageInfo, Map<String, Object> params);
	
	DataTableResponseModel selectPage(String countSqlId,String dataSqlId,DataTablePageInfo pageInfo,Map<String,Object> params);
	
	void insert(String sqlId,Object entity);
	
	/**
	 * 数据库保存数据(通过类方式生成SQL语句，拼接entity的参数值信息)<br/>
	 * 此处注意如果entity的参数值是由外部传入，则需要注意参数值的SQL注入校验
	 * @param clazz	保存数据的类
	 * @param entity	对象的参数值
	 */
	void insert(Class<? extends Object> clazz,Object entity);
	
	void insert(Class<? extends Object> clazz,Object entity,String primaryKey);
	
	void update(String sqlId);
	
	void update(String sqlId,Object entity);
	
	void update(String sqlId,Map<String,Object> params);
	
	void delete(String sqlId, Object obj);
	
	void delete(Class<? extends Object> clazz,Map<String,Object> params);
	
	void delete(Class<? extends Object> clazz,Object entity);
	
	void deleteAndBak(String sql,String operator);

	void deleteAndBak(Class<? extends Object> clazz,Integer id,String operator);
	
	void deleteAndBak(Class<? extends Object> clazz,Object entity,String operator);
	
	void deleteAndBak(Class<? extends Object> clazz,Map<String,Object> params,String operator);
	
	void deleteAndBak(Class<? extends Object> clazz,String majorKey,Object majorKeyValue,String operator);
	
	void deleteAndBak(Class<? extends Object> clazz,String majorKeyName,Object[] ids,String operator);
	
	void deleteAndBak(String sql,Class<? extends Object> clazz,Map<String,Object> params,String operator);
}