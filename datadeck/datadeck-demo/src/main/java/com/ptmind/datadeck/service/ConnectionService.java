package com.ptmind.datadeck.service;

import java.util.List;

import com.ptmind.datadeck.entity.Connection;
import com.ptmind.datadeck.model.connection.ConnectionAccountInfo;

public interface ConnectionService {

	/**
	 * 初始化连接
	 * @param connection
	 * @author xiaopeng.liu
	 * @version
	 */
	void initConnection(Connection connection);
	
	Connection readById(String id);
	
	Connection readByClientId(String clientId);
	
	void saveConnectionAccountInfo(String clientId,String accountInfo);
	
	/**
	 * 查询空间和用户所属的已建立数据源连接的数据
	 * @param ownerId
	 * @param tenantId
	 * @param code
	 * @return
	 * @author xiaopeng.liu
	 * @version
	 */
	List<ConnectionAccountInfo> readAccountInfosByCode(String ownerId,String tenantId,String code);
}
