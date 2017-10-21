package net.xueshupa.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.service.CommonService;

@Service("acadamicService")
public class AcadamicService extends CommonService {

	@Resource(name="acadamicCommonMapper")
	ICommonMapper commonMapper;
	
	@Override
	public ICommonMapper getCommonMapper() {
		return this.commonMapper;
	}

}
