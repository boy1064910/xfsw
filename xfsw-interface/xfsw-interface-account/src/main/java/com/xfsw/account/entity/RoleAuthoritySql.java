/**
 * 
 */
package com.xfsw.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author liuxiaopeng
 *
 */
public class RoleAuthoritySql implements Serializable {

	private static final long serialVersionUID = 3744965815712745112L;
	private Integer id;
	private Integer roleId;
	private Integer authorityHashId;
	private String countSql;
	private String dataSql;
	private String dataSubfixSql;
	private String dataPool;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	//辅助字段
	private List<RoleAuthoritySqlParam> roleAuthoritySqlParamList;

	public RoleAuthoritySql(){}
	
	public RoleAuthoritySql(Integer roleId){
		super();
		this.roleId = roleId;
	}
	
	public RoleAuthoritySql(Integer roleId, Integer authorityHashId) {
		super();
		this.roleId = roleId;
		this.authorityHashId = authorityHashId;
	}
	
	public RoleAuthoritySql(Integer roleId, Integer authorityHashId,String countSql, String dataSql, String dataSubfixSql, String dataPool, String lastUpdater) {
		super();
		this.roleId = roleId;
		this.authorityHashId = authorityHashId;
		this.countSql = countSql;
		this.dataSubfixSql = dataSubfixSql;
		this.dataSql = dataSql;
		this.dataPool = dataPool;
		this.lastUpdater = lastUpdater;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getAuthorityHashId() {
		return authorityHashId;
	}
	public void setAuthorityHashId(Integer authorityHashId) {
		this.authorityHashId = authorityHashId;
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
	public List<RoleAuthoritySqlParam> getRoleAuthoritySqlParamList() {
		return roleAuthoritySqlParamList;
	}
	public void setRoleAuthoritySqlParamList(List<RoleAuthoritySqlParam> roleAuthoritySqlParamList) {
		this.roleAuthoritySqlParamList = roleAuthoritySqlParamList;
	}

	public String getDataSql() {
		return dataSql;
	}

	public void setDataSql(String dataSql) {
		this.dataSql = dataSql;
	}

	public String getDataPool() {
		return dataPool;
	}

	public void setDataPool(String dataPool) {
		this.dataPool = dataPool;
	}

	public String getCountSql() {
		return countSql;
	}

	public void setCountSql(String countSql) {
		this.countSql = countSql;
	}

	public String getDataSubfixSql() {
		return dataSubfixSql;
	}

	public void setDataSubfixSql(String dataSubfixSql) {
		this.dataSubfixSql = dataSubfixSql;
	}
}
