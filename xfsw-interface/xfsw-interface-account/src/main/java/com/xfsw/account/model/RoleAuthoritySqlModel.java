package com.xfsw.account.model;

import java.io.Serializable;
import java.util.List;

import com.xfsw.account.entity.RoleAuthoritySqlParam;

/**
 * 角色权限SQL配置信息（包括参数信息）模型
 * @author liuxifan
 *
 */
public class RoleAuthoritySqlModel implements Serializable {

	private static final long serialVersionUID = -7852897568073304148L;
	private Integer id;
	private String name;
	private String url;
	private Integer roleAuthoritySqlId;
	private String countSql;
	private String dataSql;
	private String dataSubfixSql;
	private String dataPool;
	private List<RoleAuthoritySqlParam> params;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public List<RoleAuthoritySqlParam> getParams() {
		return params;
	}
	public void setParams(List<RoleAuthoritySqlParam> params) {
		this.params = params;
	}
	public Integer getRoleAuthoritySqlId() {
		return roleAuthoritySqlId;
	}
	public void setRoleAuthoritySqlId(Integer roleAuthoritySqlId) {
		this.roleAuthoritySqlId = roleAuthoritySqlId;
	}
	public String getDataPool() {
		return dataPool;
	}
	public void setDataPool(String dataPool) {
		this.dataPool = dataPool;
	}
	public String getDataSql() {
		return dataSql;
	}
	public void setDataSql(String dataSql) {
		this.dataSql = dataSql;
	}
	
}
