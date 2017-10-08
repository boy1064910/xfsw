package com.xfsw.common.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.util.CollectionUtils;

import com.xfsw.common.classes.DataTablePageInfo;
import com.xfsw.common.classes.DataTableResponseModel;
import com.xfsw.common.util.ArrayUtil;
import com.xfsw.common.util.MapUtil;
import com.xfsw.common.util.ReflectUtil;
import com.xfsw.common.util.StringUtil;

public class CommonMapper implements ICommonMapper {

	SqlSessionTemplate sqlSessionTemplate;

	public CommonMapper(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public boolean check(String sqlId){
		Integer count = this.sqlSessionTemplate.selectOne(sqlId);
		if(count!=null)
			if(count>0)
				return true;
		return false;
	}
	
	public boolean check(String sqlId,Object entity){
		Integer count = this.sqlSessionTemplate.selectOne(sqlId, entity);
		if(count!=null)
			if(count>0)
				return true;
		return false;
	}
	
	public boolean check(String sqlId,Map<String,Object> params){
		Integer count = this.sqlSessionTemplate.selectOne(sqlId, params);
		if(count!=null)
			if(count>0)
				return true;
		return false;
	}
	
	public <T> T get(String sqlId){
		return this.sqlSessionTemplate.selectOne(sqlId);
	}
	
	public <T> T get(String sqlId,Object entity){
		return this.sqlSessionTemplate.selectOne(sqlId,entity);
	}
	
	public Object get(Class<? extends Object> clazz,Integer id){
		Map<String,Object> params = new HashMap<String,Object>();
		String clazzName = clazz.getSimpleName();
		String sql = "select * from " + clazzName + " where id = #{id}";
		params.put("id",id);
		params.put("sql", sql);
		Map<String,Object> result = this.sqlSessionTemplate.selectOne("Common.queryOperation",params);
		if(result==null||result.size()==0){
			return null;
		}
		this.dealDataResultMap(result);
		return MapUtil.map2Pojo(result, clazz);
	}
	
	public Object get(Class<? extends Object> clazz,Class<? extends Object> targetClazz,Integer id){
		Map<String,Object> params = new HashMap<String,Object>();
		String clazzName = clazz.getSimpleName();
		String sql = "select * from " + clazzName + " where id = #{id}";
		params.put("id",id);
		params.put("sql", sql);
		Map<String,Object> result = this.sqlSessionTemplate.selectOne("Common.queryOperation",params);
		if(result==null||result.size()==0){
			return null;
		}
		this.dealDataResultMap(result);
		return MapUtil.map2Pojo(result, targetClazz);
	}
	
	public Object get(Class<? extends Object> clazz,Map<String,Object> params){
		if(CollectionUtils.isEmpty(params)) {
			throw new RuntimeException("查询参数为空！");
		}
		String clazzName = clazz.getSimpleName();
		String sql = "select * from " + clazzName + " where 1=1 ";
		for(Map.Entry<String, Object> entry:params.entrySet()){
			sql+=" AND "+entry.getKey()+"=#{"+entry.getKey()+"}";
		}
		params.put("sql", sql);
		Map<String,Object> result = sqlSessionTemplate.selectOne("Common.queryOperation",params);
		if(result==null||result.size()==0){
			return null;
		}
		this.dealDataResultMap(result);
		return MapUtil.map2Pojo(result, clazz);
	}
	
	public Object get(Class<? extends Object> clazz,Object entity){
		if(entity==null){
			throw new RuntimeException("查询对象实例参数为空！");
		}
		Map<String,Object> params = MapUtil.pojoToMapNotNullField(entity);
		return this.get(clazz, params);
	}
	
	public Object get(Class<? extends Object> clazz,String fieldName,Object value){
		return this.get(clazz, clazz, fieldName, value);
	}
	
	public Object get(Class<? extends Object> clazz,Class<? extends Object> targetClazz,String fieldName,Object value){
		if(StringUtil.isEmpty(fieldName)){
			throw new RuntimeException("查询参数为空！");
		}
		if(value==null){
			throw new RuntimeException("查询参数数据为空！");
		}
		String clazzName = clazz.getSimpleName();
		String sql = "select * from " + clazzName + " where "+fieldName+" = #{"+fieldName+"}";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sql", sql);
		Map<String,Object> result = sqlSessionTemplate.selectOne("Common.queryOperation",params);
		if(result==null||result.size()==0){
			return null;
		}
		this.dealDataResultMap(result);
		return MapUtil.map2Pojo(result, targetClazz);
	}

	public <T> List<T> selectList(String sqlId) {
		return this.sqlSessionTemplate.selectList(sqlId);
	}

	public <T> List<T> selectList(String sqlId, Object object) {
		return this.sqlSessionTemplate.selectList(sqlId, object);
	}
	
	public <T> List<T> selectList(String sqlId, Map<String,Object> params) {
		return this.sqlSessionTemplate.selectList(sqlId, params);
	}
	
	public <T> List<T> selectListInPage(String sqlId,DataTablePageInfo pageInfo, Map<String, Object> params){
		Map<String, Object> queryParams = new HashMap<String, Object>();
		if (params != null) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				queryParams.put(entry.getKey(), entry.getValue());
			}
		}
		queryParams.put("startIndex", pageInfo.getCurrentIndex()-1);
		queryParams.put("pageSize", pageInfo.getPageSize());
		return sqlSessionTemplate.selectList(sqlId,queryParams);
	}
	
	public <T> List<T> selectList(Class<T> clazz,Map<String,Object> params){
		if(MapUtil.isEmpty(params)){
			throw new RuntimeException("查询参数为空！");
		}
		Map<String,Object> queryParams = new HashMap<String,Object>();
		String clazzName = clazz.getSimpleName();
		String sql = "SELECT * FROM " + clazzName + " WHERE 1=1 ";
		if(params!=null){
			for(Map.Entry<String, Object> entry:params.entrySet()){
				sql+=" AND "+entry.getKey()+"=#{"+entry.getKey()+"}";
				queryParams.put(entry.getKey(), entry.getValue());
			}
		}
		queryParams.put("sql", sql);
		List<Map<?,?>> list = sqlSessionTemplate.selectList("Common.queryOperation",queryParams);
		return this.dealResultList(list, clazz);
	}
	
	public <T> List<T> selectList(Class<T> clazz,Object entity){
		if(entity==null){
			throw new RuntimeException("更新对象实例参数为空！");
		}
		Map<String,Object> params = MapUtil.pojoToMapNotNullField(entity);
		return this.selectList(clazz, params);
	}
	
	public <T> List<T> selectAll(Class<T> clazz){
		String clazzName = clazz.getSimpleName();
		String sql = "SELECT * FROM " + clazzName;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sql", sql);
		List<Map<?,?>> list = this.sqlSessionTemplate.selectList("Common.queryOperation",params);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return this.dealResultList(list, clazz);
	}

	public <T> List<T> selectListBySql(String sql,Class<T> clazz) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sql", sql);
		List<Map<?, ?>> list = this.sqlSessionTemplate.selectList("Common.queryOperation", params); 
		return this.dealResultList(list,clazz);
	}

	public <T> List<T> selectListBySql(String sql,Map<String,Object> params,Class<T> clazz) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		if (params != null) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				queryParams.put(entry.getKey(), entry.getValue());
			}
		}
		queryParams.put("sql", sql);
		List<Map<?, ?>> list = this.sqlSessionTemplate.selectList("Common.queryOperation", queryParams);
		return this.dealResultList(list,clazz);
	}
	
	public DataTableResponseModel selectPageBySql(String countSql,String dataSql,DataTablePageInfo pageInfo, Map<String, Object> params){
		if(params==null) params = new HashMap<String,Object>();
		DataTableResponseModel model = new DataTableResponseModel();
		params.put("sql", countSql);
		model.setTotal((Integer)this.sqlSessionTemplate.selectOne("Common.getCount", params));
		
		params.put("startIndex", pageInfo.getCurrentIndex()-1);
		params.put("pageSize", pageInfo.getPageSize());
		dataSql+=" LIMIT #{startIndex},#{pageSize}";
		params.put("sql", dataSql);
		model.setRows(this.sqlSessionTemplate.selectList("Common.queryOperation",params));
		return model;
	}
	
	public DataTableResponseModel selectPage(String countSqlId,String dataSqlId,DataTablePageInfo pageInfo,Map<String,Object> params){
		DataTableResponseModel model = new DataTableResponseModel();
		model.setTotal((Integer)sqlSessionTemplate.selectOne(countSqlId, params));
		
		if(params==null) params = new HashMap<String,Object>();
		params.put("startIndex", pageInfo.getCurrentIndex()-1);
		params.put("pageSize", pageInfo.getPageSize());
		model.setRows(sqlSessionTemplate.selectList(dataSqlId,params));
		return model;
	}
	
	public void insert(String sqlId,Object entity){
		this.sqlSessionTemplate.insert(sqlId, entity);
	}
	
	public void insert(Class<? extends Object> clazz,Object entity){
		if(entity==null){
			throw new RuntimeException("保存数据参数为空！");
		}	
		Map<String,Object> params = MapUtil.pojoToMapNotNullField(entity);
		StringBuffer fieldSql = new StringBuffer();
		StringBuffer valueSql = new StringBuffer();
		fieldSql.append("(");
		valueSql.append("(");
		int index = 0;
		for(Map.Entry<String, Object> entry : params.entrySet()){
			if(index!=0){
				fieldSql.append(",");
				valueSql.append(",");
			}
			fieldSql.append(entry.getKey());
			valueSql.append("#{").append(entry.getKey()).append("}");
			index++;
		}
		fieldSql.append(")");
		valueSql.append(")");
		
		String sql = "INSERT INTO "+clazz.getSimpleName()+fieldSql.toString()+" VALUES "+valueSql.toString();
		params.put("sql", sql);
		this.sqlSessionTemplate.insert("Common.insertOperation", params);
		//将Long类型转换成Integer 类型
		Object primaryKey = params.get("id");
		if(primaryKey!=null){
			ReflectUtil.invoke(entity, "setId", Integer.valueOf(primaryKey.toString()));//将生成的主键id持久化（保存到实体类中）
		}
	}
	
	public void insert(Class<? extends Object> clazz,Object entity,String primaryKey){
		if(entity==null){
			throw new RuntimeException("保存数据参数为空！");
		}	
		Map<String,Object> params = MapUtil.pojoToMapNotNullField(entity);
		StringBuffer fieldSql = new StringBuffer();
		StringBuffer valueSql = new StringBuffer();
		fieldSql.append("(");
		valueSql.append("(");
		int index = 0;
		for(Map.Entry<String, Object> entry : params.entrySet()){
			if(index!=0){
				fieldSql.append(",");
				valueSql.append(",");
			}
			fieldSql.append(entry.getKey());
			valueSql.append("#{").append(entry.getKey()).append("}");
			index++;
		}
		fieldSql.append(")");
		valueSql.append(")");
		
		String sql = "INSERT INTO "+clazz.getSimpleName()+fieldSql.toString()+" VALUES "+valueSql.toString();
		params.put("sql", sql);
		this.sqlSessionTemplate.insert("Common.insertOperation", params);
		//将Long类型转换成Integer 类型
		Object primaryKeyValue = params.get(primaryKey);
		if(primaryKey!=null){
			ReflectUtil.invoke(entity, "set"+StringUtil.initialFirstUppercase(primaryKey), Integer.valueOf(primaryKeyValue.toString()));//将生成的主键id持久化（保存到实体类中）
		}
	}
	
	public void update(String sqlId){
		this.sqlSessionTemplate.update(sqlId);
	}
	
	public void update(String sqlId,Object entity){
		this.sqlSessionTemplate.update(sqlId, entity);
	}
	
	public void update(String sqlId,Map<String,Object> params){
		this.sqlSessionTemplate.update(sqlId, params);
	}
	
	public void delete(String sqlId, Object obj) {
		this.sqlSessionTemplate.delete(sqlId, obj);
	}
	
	public void delete(Class<? extends Object> clazz,Map<String,Object> params){
		if(MapUtil.isEmpty(params)){
			throw new RuntimeException("map参数为空！");
		}
		//每次调用初始化执行参数map，避免修改原来的map参数信息
		Map<String,Object> excuteParams = new HashMap<String,Object>();
		for(Map.Entry<String, Object> entry : params.entrySet()){
			excuteParams.put(entry.getKey(), entry.getValue());
		}
		String clazzName = clazz.getSimpleName();
		String delSql = this.generateDelSql(clazzName, excuteParams);
		excuteParams.put("sql", delSql);
		this.sqlSessionTemplate.delete("Common.delOperation",excuteParams);
	}
	
	public void delete(Class<? extends Object> clazz,Object entity){
		if(entity==null){
			throw new RuntimeException("对象参数为空！");
		}
		Map<String,Object> params = MapUtil.pojoToMapNotNullField(entity);
		this.delete(clazz, params);
	}

	public void deleteAndBak(String sql, String operator) {
		// //处理备份SQL
		// String bakSql = this.generateBakSqlByDelSql(sql);
		// //处理更新SQL
		// String updateBakSql = null;
		// if(!StringUtil.isEmpty(operator)){
		// updateBakSql = this.generateUpdateBakSqlByDelSql(sql);
		// }
		// Map<String,Object> excuteParams = new HashMap<String,Object>();
		// excuteParams.put("lastUpdater", operator);
		// del(sql, bakSql, updateBakSql, excuteParams);
	}
	
	public void deleteAndBak(Class<? extends Object> clazz,Integer id,String operator){
		Map<String,Object> params = new HashMap<String,Object>();
		String clazzName = clazz.getSimpleName();
		String bakSql = this.generateBakSql(clazzName, "id");
		String updateBakSql = null;
		if(!StringUtil.isEmpty(operator)){
			updateBakSql = this.generateUpdateBakSql(clazzName, "id");
		}
		String delSql = this.generateDelSql(clazzName, "id");
		params.put("id",id);
		params.put("lastUpdater", operator);
		del(delSql, bakSql, updateBakSql, params);
	}

	public void deleteAndBak(Class<? extends Object> clazz,Object entity,String operator){
		if(entity==null){
			throw new RuntimeException("对象参数为空！");
		}
		Map<String,Object> params = MapUtil.pojoToMapNotNullField(entity);
		this.deleteAndBak(clazz, params, operator);
	}
	
	public void deleteAndBak(Class<? extends Object> clazz,Map<String,Object> params,String operator){
		if(MapUtil.isEmpty(params)){
			throw new RuntimeException("map参数为空！");
		}
		
		//每次调用初始化执行参数map，避免修改原来的map参数信息
		Map<String,Object> excuteParams = new HashMap<String,Object>();
		for(Map.Entry<String, Object> entry : params.entrySet()){
			excuteParams.put(entry.getKey(), entry.getValue());
		}
		String clazzName = clazz.getSimpleName();
		String bakSql = this.generateBakSql(clazzName, excuteParams);
		String updateBakSql = null;
		if(!StringUtil.isEmpty(operator)){
			updateBakSql = this.generateUpdateBakSql(clazzName, excuteParams);
		}
		String delSql = this.generateDelSql(clazzName, excuteParams);
		excuteParams.put("lastUpdater", operator);
		del(delSql, bakSql, updateBakSql, excuteParams);
	}
	
	public void deleteAndBak(Class<? extends Object> clazz,String majorKey,Object majorKeyValue,String operator){
		Map<String,Object> params = new HashMap<String,Object>();
		String clazzName = clazz.getSimpleName();
		String bakSql = this.generateBakSql(clazzName, majorKey);
		String updateBakSql = null;
		if(!StringUtil.isEmpty(operator)){
			updateBakSql = this.generateUpdateBakSql(clazzName, majorKey);
		}
		String delSql = this.generateDelSql(clazzName, majorKey);
		params.put(majorKey,majorKeyValue);
		params.put("lastUpdater", operator);
		del(delSql, bakSql, updateBakSql, params);
	}
	
	public void deleteAndBak(Class<? extends Object> clazz,String majorKeyName,Object[] ids,String operator){
		if(ArrayUtil.isEmpty(ids)){
			throw new RuntimeException("删除参数数组为空！");
		}
		
		for(int i=0;i<ids.length;i++){
			this.deleteAndBak(clazz, majorKeyName, ids[i], operator);
		}
	}
	
	public void deleteAndBak(String sql,Class<? extends Object> clazz,Map<String,Object> params,String operator){
		//每次调用初始化执行参数map，避免修改原来的map参数信息
		Map<String,Object> excuteParams = new HashMap<String,Object>();
		for(Map.Entry<String, Object> entry : params.entrySet()){
			excuteParams.put(entry.getKey(), entry.getValue());
		}
		String clazzName = clazz.getSimpleName();
		//处理备份SQL
		String bakSql = this.generateBakSqlByDelSql(clazzName, sql);
		//处理更新SQL
		String updateBakSql = null;
		if(!StringUtil.isEmpty(operator)){
			updateBakSql = this.generateUpdateBakSqlByDelSql(clazzName, sql);
		}
		excuteParams.put("lastUpdater", operator);
		del(sql, bakSql, updateBakSql, excuteParams);
	}
	
	private void dealDataResultMap(Map<String,Object> result){
		for(Map.Entry<String,Object> entry:result.entrySet()){
			if(entry.getValue() instanceof BigDecimal){
				BigDecimal value = (BigDecimal) entry.getValue();
				result.put(entry.getKey(), value.doubleValue());
			}
		}
	}
	
	private String generateBakSql(String clazzName,String majorKey){
		return "INSERT INTO " + clazzName + "_copy (SELECT * FROM " + clazzName + " WHERE "+majorKey+"=#{"+majorKey+"}) "
				+ "ON DUPLICATE KEY UPDATE lastUpdater = #{lastUpdater},lastUpdateTime = NOW()";
	}
	
	private String generateUpdateBakSql(String clazzName,String majorKey){
		return "UPDATE "+clazzName+"_copy SET lastUpdater = #{lastUpdater},lastUpdateTime = NOW() WHERE "+majorKey+"=#{"+majorKey+"}";
	}
	
	private String generateDelSql(String clazzName,String majoyKey){
		return "DELETE FROM " + clazzName + " WHERE "+majoyKey+" = #{"+majoyKey+"}";
	}
	
	private String generateBakSql(String clazzName,Map<String,Object> params){
		StringBuffer conditionSQL = new StringBuffer();
		for(Map.Entry<String, Object> entry:params.entrySet()){
			conditionSQL.append(" and ").append(entry.getKey()).append("=").append("#{").append(entry.getKey()).append("}");
		}
		String bakSql = "INSERT INTO " + clazzName + "_copy (SELECT * FROM " + clazzName + " WHERE 1=1 "+conditionSQL.toString()+")"
				+ "ON DUPLICATE KEY UPDATE lastUpdater = #{lastUpdater},lastUpdateTime = NOW()";
		return bakSql;
	}
	
	private String generateUpdateBakSql(String clazzName,Map<String,Object> params){
		StringBuffer conditionSQL = new StringBuffer();
		for(Map.Entry<String, Object> entry:params.entrySet()){
			conditionSQL.append(" and ").append(entry.getKey()).append("=").append("#{").append(entry.getKey()).append("}");
		}
		return "UPDATE "+clazzName+"_copy SET lastUpdater = #{lastUpdater},lastUpdateTime = NOW() where 1=1 "+conditionSQL.toString();
	}
	
	private String generateDelSql(String clazzName,Map<String,Object> params){
		StringBuffer conditionSQL = new StringBuffer();
		for(Map.Entry<String, Object> entry:params.entrySet()){
			conditionSQL.append(" and ").append(entry.getKey()).append("=").append("#{").append(entry.getKey()).append("}");
		}
		return "DELETE FROM " + clazzName + " WHERE 1=1 "+conditionSQL.toString();
	}
	
	private String generateBakSqlByDelSql(String clazzName,String delSql){
		return "INSERT INTO "+clazzName+"_copy (SELECT * "+delSql.trim().substring(6)+") "
				+ "ON DUPLICATE KEY UPDATE lastUpdater = #{lastUpdater},lastUpdateTime = NOW()";
	}
	
	private String generateUpdateBakSqlByDelSql(String clazzName,String delSql){
		//处理更新SQL
		int whereIndex = delSql.trim().toUpperCase().indexOf("WHERE")+5;
		String paramSql = delSql.trim().substring(whereIndex);
		return "UPDATE "+clazzName+"_copy set lastUpdater = #{lastUpdater},lastUpdateTime = NOW() WHERE 1=1 AND "+paramSql;
	}
	
//	private String generateBakSqlByDelSql(String delSql){
//		int fromIndex = delSql.trim().toUpperCase().indexOf("FROM");
//		int whereIndex = delSql.trim().toUpperCase().indexOf("WHERE");
//		String clazzName = delSql.substring(fromIndex+4, whereIndex);
//		return "INSERT INTO "+clazzName.trim()+"_copy (SELECT * "+delSql.trim().substring(fromIndex)+") "
//				+ "ON DUPLICATE KEY UPDATE lastUpdater = #{lastUpdater},lastUpdateTime = NOW()";
//	}
	
//	private String generateUpdateBakSqlByDelSql(String delSql){
//		int fromIndex = delSql.trim().toUpperCase().indexOf("FROM");
//		int whereIndex = delSql.trim().toUpperCase().indexOf("WHERE");
//		String clazzName = delSql.substring(fromIndex+4, whereIndex);
//		String paramSql = delSql.trim().substring(whereIndex);
//		return "UPDATE "+clazzName.trim()+"_copy set lastUpdater = #{lastUpdater},lastUpdateTime = NOW() "+paramSql;
//	}
	
	private void del(String delSQL,String delBakSQL,String updateBakSql,Map<String,Object> params){
		if(!StringUtil.isEmpty(delBakSQL)){
			params.put("sql", delBakSQL);
			this.sqlSessionTemplate.insert("Common.delBakOperation",params);
			if(!StringUtil.isEmpty(updateBakSql)){
				params.put("sql", updateBakSql);
				this.sqlSessionTemplate.update("Common.updateOperation",params);
			}
		}
		params.put("sql", delSQL);
		this.sqlSessionTemplate.delete("Common.delOperation",params);
	}

	private <T> List<T> dealResultList(List<Map<?,?>> list,Class<T> clazz){
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<T> resultList = new ArrayList<T>();
		for(int i=0;i<list.size();i++){
			resultList.add(MapUtil.map2Entity(list.get(i), clazz));
		}
		return resultList;
	}
}
