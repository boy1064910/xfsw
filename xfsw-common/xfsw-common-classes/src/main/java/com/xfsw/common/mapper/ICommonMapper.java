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
	<T> T get(String sqlId);
	
	/**
	 * 通过mybatis定义的sqlId关联语句+参数值查询单条数据
	 * @param sqlId	Mybatis中定义的SQL语句Id
	 * @param entity	查询参数值
	 * @return	返回结果为单条数据，结果泛型请查看Mybatis中定义的sqlId
	 */
	<T> T get(String sqlId,Object entity);
	
	/**
	 * 根据id获取单条数据
	 * @param clazz
	 * @param id
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	<T> T get(Class<T> clazz,Integer id);
	
	/**
	 * 根据字段获取单条数据
	 * @param clazz
	 * @param fieldName
	 * @param value
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	<T> T get(Class<T> clazz,String fieldName,Object value);
	
	/**
	 * 根据字段信息查询单条数据
	 * @param clazz			查询的类名,需要和表名一致
	 * @param targetClazz	目标返回类名
	 * @param fieldName		字段名称
	 * @param value			字段值
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	<T> T get(Class<? extends Object> clazz,Class<T> targetClazz,String fieldName,Object value);
	
	Object get(Class<? extends Object> clazz,Class<? extends Object> targetClazz,Integer id);
	
	Object get(Class<? extends Object> clazz,Map<String,Object> params);
	
	Object get(Class<? extends Object> clazz,Object entity);
	
	
	/**
	 * 通过mybatis定义的sqlId关联语句查询多条数据
	 * @param sqlId	Mybatis中定义的SQL语句Id
	 * @return	返回结果为数组，结果泛型请查看Mybatis中定义的sqlId
	 */
	<T> List<T> selectList(String sqlId);
	
	/**
	 * 通过mybatis定义的sqlId关联语句+参数值查询多条数据
	 * @param sqlId	Mybatis中定义的SQL语句Id
	 * @param object	查询参数值
	 * @return	返回结果为数组，结果泛型请查看Mybatis中定义的sqlId
	 */
	<T> List<T> selectList(String sqlId, Object object);
	
	/**
	 * 通过mybatis定义的sqlId关联语句+参数值查询多条数据
	 * @param sqlId
	 * @param params
	 * @return	返回结果为数组，结果泛型请查看Mybatis中定义的sqlId
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	<T> List<T> selectList(String sqlId, Map<String,Object> params);
	
	/**
	 * 通过mybatis定义的sqlId分页查询
	 * @param sqlId
	 * @param pageInfo
	 * @param params
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	<T> List<T> selectListInPage(String sqlId,DataTablePageInfo pageInfo, Map<String, Object> params);
	
	<T> List<T> selectList(Class<T> clazz,Map<String,Object> params);
	
	<T> List<T> selectList(Class<T> clazz,Object entity);
	
	/**
	 * 查询所有数据
	 * @param clazz
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	<T> List<T> selectAll(Class<T> clazz);
	
	/**
	 * 通过SQL语句查询数据（严禁前端请求带的参数SQL拼接，防止SQL注入，如需传参，请调用selectListBySql携带参数的方法，sql中参数必须使用#{}方式传递）
	 * @param sql		SQL语句，不应有SQL拼接参数的情况
	 * @param clazz
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	<T> List<T> selectListBySql(String sql,Class<T> clazz);
	
	/**
	 * 通过SQL语句和参数查询数据
	 * @param sql		SQL语句，遵循Mybatis SQL语句规则,sql中参数必须使用#{}方式传递
	 * @param params		SQL语句中的参数
	 * @param clazz
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	<T> List<T> selectListBySql(String sql,Map<String,Object> params,Class<T> clazz);
	
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