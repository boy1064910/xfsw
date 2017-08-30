package com.ptmind.datadeck.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ptmind.datadeck.entity.Connection;
import com.ptmind.datadeck.model.connection.ConnectionAccountInfo;
import com.xfsw.common.mapper.ICommonMapper;

@Service("connectionService")
public class ConnectionServiceImpl implements ConnectionService {

	@Resource(name="ddCommonMapper")
	ICommonMapper ddCommonMapper;
	
	@Override
	public void initConnection(Connection connection) {
		ddCommonMapper.insert("Connection.initConnection", connection);
	}

	public Connection readById(String id) {
		return (Connection) ddCommonMapper.get("Connection.readById", id);
	}
	
	public Connection readByClientId(String clientId) {
		return (Connection) ddCommonMapper.get("Connection.readByClientId",clientId);
	}
	
	public void saveConnectionAccountInfo(String clientId,String accountInfo) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("clientId", clientId);
		params.put("accountInfo", accountInfo);
		ddCommonMapper.update("Connection.saveConnectionAccountInfo",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<ConnectionAccountInfo> readAccountInfosByCode(String ownerId,String tenantId,String code){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ownerId", ownerId);
		params.put("tenantId", tenantId);
		params.put("code", code);
		return (List<ConnectionAccountInfo>) ddCommonMapper.selectList("Connection.readAccountInfosByCode",params);
	}
}

