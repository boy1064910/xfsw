/**
 * 
 */
package com.xfsw.common.filter.response;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.util.JsonUtil;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Aspect
@Component
public class ResponseFilterAspector {

	protected static Logger logger = LoggerFactory.getLogger(ResponseFilterAspector.class);
	
	@Pointcut(value = "@annotation(com.xfsw.common.filter.response.ResponseFilterRetention)")
	private void pointcut() {
	}
	
	@Around(value = "pointcut()")
	public ResponseModel il8nData(ProceedingJoinPoint joinPoint) throws Throwable{
		ResponseModel responseModel = (ResponseModel) joinPoint.proceed();
//		Object data = responseModel.getData();
		
////		String[] ignores = retention.ignores();
//		if(ArrayUtils.isEmpty(ignores)){
//			return responseModel;
//		}
//		
//		ObjectMapper mapper = new ObjectMapper();
//		SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept(ignores);  
//        FilterProvider filters = new SimpleFilterProvider().setDefaultFilter(theFilter);
//       
//        String dataStr = mapper.writer(filters).writeValueAsString(data);  
//        responseModel.setData(JsonUtil.json2Map(dataStr));
		return responseModel;
	}
}
