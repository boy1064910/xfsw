package com.xfsw.account.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.account.entity.SystemErrorLog;
import com.xfsw.common.mapper.ICommonMapper;

@Service("systemErrorLogService")
public class SystemErrorLogServiceImpl implements SystemErrorLogService {

	@Resource(name="accountCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public void insertBackErrorLog(String info, String operator) {
		SystemErrorLog log = new SystemErrorLog();
		log.setInfo(info);
		log.setLastUpdater(operator);
		log.setLastUpdateTime(new Date());
		log.setStatus(0);
		log.setSource("系统后台服务");
		commonMapper.insert(SystemErrorLog.class, log);
	}

}
