package net.xueshupa.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.service.CommonService;

import net.xueshupa.service.AcadamicService;

@Service("acadamicService")
public class AcadamicServiceImpl extends CommonService implements AcadamicService{

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public ICommonMapper getCommonMapper() {
		return this.commonMapper;
	}

}
