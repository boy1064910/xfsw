/**
 * 
 */
package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuxiaopeng
 *
 */
public class RoleAuthoritySqlParam implements Serializable {

	private static final long serialVersionUID = 4567665658727442626L;
	private Integer id;
	private Integer roleAuthoritySqlId;
	private String paramsName;
	private String paramsSql;
	private String countParamSql;
	private Integer orderIndex;
	private Integer sign;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	//辅助字段
	private Integer authorityHashId;
	
	public RoleAuthoritySqlParam() {}
	
	public RoleAuthoritySqlParam(Integer id, String paramsName, String paramsSql, String countParamSql, Integer sign, String lastUpdater) {
		super();
		this.id = id;
		this.paramsName = paramsName;
		this.paramsSql = paramsSql;
		this.countParamSql = countParamSql;
		this.sign = sign;
		this.lastUpdater = lastUpdater;
	}
	
	public RoleAuthoritySqlParam(Integer id, String paramsName, String paramsSql, String countParamSql, Integer sign, String lastUpdater,Date lastUpdateTime) {
		super();
		this.id = id;
		this.paramsName = paramsName;
		this.paramsSql = paramsSql;
		this.countParamSql = countParamSql;
		this.sign = sign;
		this.lastUpdater = lastUpdater;
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public RoleAuthoritySqlParam(String paramsName, String paramsSql, String countParamSql, Integer sign, String lastUpdater,Date lastUpdateTime,Integer roleAuthoritySqlId) {
		super();
		this.roleAuthoritySqlId = roleAuthoritySqlId;
		this.paramsName = paramsName;
		this.paramsSql = paramsSql;
		this.countParamSql = countParamSql;
		this.sign = sign;
		this.lastUpdater = lastUpdater;
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastUpdater() {
		return lastUpdater;
	}
	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getRoleAuthoritySqlId() {
		return roleAuthoritySqlId;
	}
	public void setRoleAuthoritySqlId(Integer roleAuthoritySqlId) {
		this.roleAuthoritySqlId = roleAuthoritySqlId;
	}
	public String getParamsName() {
		return paramsName;
	}
	public void setParamsName(String paramsName) {
		this.paramsName = paramsName;
	}
	public String getParamsSql() {
		return paramsSql;
	}
	public void setParamsSql(String paramsSql) {
		this.paramsSql = paramsSql;
	}
	public Integer getAuthorityHashId() {
		return authorityHashId;
	}
	public void setAuthorityHashId(Integer authorityHashId) {
		this.authorityHashId = authorityHashId;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public String getCountParamSql() {
		return countParamSql;
	}

	public void setCountParamSql(String countParamSql) {
		this.countParamSql = countParamSql;
	}
}
