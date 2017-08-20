package com.xfsw.auth.cache.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.xfsw.account.entity.RoleAuthoritySql;
import com.xfsw.account.entity.RoleAuthoritySqlParam;
import com.xfsw.account.service.RoleAuthoritySqlParamService;
import com.xfsw.account.service.RoleAuthoritySqlService;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.ListUtil;
import com.xfsw.common.util.StringUtil;

@Service("roleAuthoritySqlCacheService")
public class RoleAuthoritySqlCacheServiceImpl implements RoleAuthoritySqlCacheService {

	/**所有权限（菜单不包括一级菜单、功能）缓存前缀*/
	private final static String XFSW_ROLE_AUTHORITY_SQL = "role_authority_sql_";
	/**所有权限过期时间*/
	private final static Integer XFSW_ROLE_AUTHORITY_SQL_EXPIRED_TIME = 7*24*3600*1000;//一个星期
	
	@Resource(name="roleAuthoritySqlService")
	RoleAuthoritySqlService roleAuthoritySqlService;
	
	@Resource(name="roleAuthoritySqlParamService")
	RoleAuthoritySqlParamService roleAuthoritySqlParamService;
	
//	private static Logger logger = LoggerFactory.getLogger(SystemAuthorityServiceImpl.class);
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@PostConstruct
	private void init(){
		List<RoleAuthoritySql> roleAuthoritySqlList = roleAuthoritySqlService.selectAll();
		if(!ListUtil.isEmpty(roleAuthoritySqlList)){
			for(RoleAuthoritySql roleAuthoritySql:roleAuthoritySqlList){
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("roleAuthoritySqlId", roleAuthoritySql.getId());
				
				List<RoleAuthoritySqlParam> roleAuthoritySqlParamList = roleAuthoritySqlParamService.selectList(params);
				roleAuthoritySql.setRoleAuthoritySqlParamList(roleAuthoritySqlParamList);
				//redis缓存的key是role_authority_sql_（角色ID）_（权限HashId）
				redisTemplate.opsForValue().set(XFSW_ROLE_AUTHORITY_SQL+roleAuthoritySql.getRoleId()+"_"+roleAuthoritySql.getAuthorityHashId(), JsonUtil.entity2Json(roleAuthoritySql),XFSW_ROLE_AUTHORITY_SQL_EXPIRED_TIME, TimeUnit.MILLISECONDS);
			}
		}
	}
	
	public RoleAuthoritySql get(Integer roleId,Integer authorityHashId){
		String info = redisTemplate.opsForValue().get(XFSW_ROLE_AUTHORITY_SQL+roleId+"_"+authorityHashId);
		if(StringUtil.isEmpty(info)){
			return this.reload(roleId, authorityHashId);
		}
		else{
			return (RoleAuthoritySql) JsonUtil.json2Entity(info, RoleAuthoritySql.class);
		}
	}
	
	public RoleAuthoritySql reload(Integer roleId,Integer authorityHashId){
		RoleAuthoritySql sql = new RoleAuthoritySql(roleId,authorityHashId);
		sql = (RoleAuthoritySql) roleAuthoritySqlService.get(sql);
		
		if(sql==null) return null;
		
		RoleAuthoritySqlParam roleAuthoritySqlParam = new RoleAuthoritySqlParam();
		roleAuthoritySqlParam.setRoleAuthoritySqlId(sql.getId());
		List<RoleAuthoritySqlParam> roleAuthoritySqlParamList = roleAuthoritySqlParamService.selectList(roleAuthoritySqlParam);
		sql.setRoleAuthoritySqlParamList(roleAuthoritySqlParamList);
		
		//redis缓存的key是role_authority_sql_（角色ID）_（权限HashId）
		redisTemplate.opsForValue().set(XFSW_ROLE_AUTHORITY_SQL+roleId+"_"+authorityHashId, JsonUtil.entity2Json(sql), XFSW_ROLE_AUTHORITY_SQL_EXPIRED_TIME, TimeUnit.MILLISECONDS);
		return sql;
	}
	
	public void authorityHashIdChangeEvent(Integer authorityHashId,Integer oldAuthorityHashId){
		this.authorityHashIdDeleteEvent(oldAuthorityHashId);
		this.reloadByAuthorityHashId(authorityHashId);
	}
	
	private void reloadByAuthorityHashId(Integer authorityHashId){
		RoleAuthoritySql sql = new RoleAuthoritySql();
		sql.setAuthorityHashId(authorityHashId);
		List<RoleAuthoritySql> sqlList = roleAuthoritySqlService.selectList(sql);
		
		if(ListUtil.isEmpty(sqlList)) return;
		
		for(RoleAuthoritySql tempSql : sqlList){
			RoleAuthoritySqlParam roleAuthoritySqlParam = new RoleAuthoritySqlParam();
			roleAuthoritySqlParam.setRoleAuthoritySqlId(tempSql.getId());
			List<RoleAuthoritySqlParam> roleAuthoritySqlParamList = roleAuthoritySqlParamService.selectList(roleAuthoritySqlParam);
			sql.setRoleAuthoritySqlParamList(roleAuthoritySqlParamList);
			//redis缓存的key是role_authority_sql_（角色ID）_（权限HashId）
			redisTemplate.opsForValue().set(XFSW_ROLE_AUTHORITY_SQL+tempSql.getRoleId()+"_"+tempSql.getAuthorityHashId(), JsonUtil.entity2Json(tempSql),XFSW_ROLE_AUTHORITY_SQL_EXPIRED_TIME, TimeUnit.MILLISECONDS);
		}
	}
	
	public void authorityHashIdDeleteEvent(Integer authorityHashId){
		Set<String> keysSets = redisTemplate.keys(XFSW_ROLE_AUTHORITY_SQL+"*_"+authorityHashId);
		if(keysSets!=null&&keysSets.size()>0){
			for(String key:keysSets){
				redisTemplate.delete(key);
			}
		}
	}
	
	public void reloadByRoleId(Integer roleId){
		RoleAuthoritySql sql = new RoleAuthoritySql(roleId);
		List<RoleAuthoritySql> roleAuthoritySqlList = roleAuthoritySqlService.selectList(sql);
		
		if(ListUtil.isEmpty(roleAuthoritySqlList)) return;
		
		for(RoleAuthoritySql roleAuthoritySql:roleAuthoritySqlList){
			RoleAuthoritySqlParam roleAuthoritySqlParam = new RoleAuthoritySqlParam();
			roleAuthoritySqlParam.setRoleAuthoritySqlId(roleAuthoritySql.getId());
			List<RoleAuthoritySqlParam> roleAuthoritySqlParamList = roleAuthoritySqlParamService.selectList(roleAuthoritySqlParam);
			roleAuthoritySql.setRoleAuthoritySqlParamList(roleAuthoritySqlParamList);
			//redis缓存的key是role_authority_sql_（角色ID）_（权限HashId）
			redisTemplate.opsForValue().set(XFSW_ROLE_AUTHORITY_SQL+roleAuthoritySql.getRoleId()+"_"+roleAuthoritySql.getAuthorityHashId(), JsonUtil.entity2Json(roleAuthoritySql),XFSW_ROLE_AUTHORITY_SQL_EXPIRED_TIME, TimeUnit.MILLISECONDS);
		}
	}
	
	public void deleteByRoleId(Integer roleId){
		Set<String> keysSets = redisTemplate.keys(XFSW_ROLE_AUTHORITY_SQL+roleId+"_*");
		if(keysSets!=null&&keysSets.size()>0){
			for(String key:keysSets){
				redisTemplate.delete(key);
			}
		}
	}
}
