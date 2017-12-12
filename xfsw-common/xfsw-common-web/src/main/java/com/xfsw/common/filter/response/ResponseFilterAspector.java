/**
 * 
 */
package com.xfsw.common.filter.response;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xfsw.common.classes.ResponseModel;

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
	public ResponseModel filter(ProceedingJoinPoint joinPoint) throws Throwable {
		ResponseModel responseModel = (ResponseModel) joinPoint.proceed();
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method targetMethod = methodSignature.getMethod();
		if (!ArrayUtils.isEmpty(targetMethod.getAnnotations())) {
			String[] ignores = null;
			for (Annotation annotation : targetMethod.getAnnotations()) {
				if (annotation instanceof ResponseFilterRetention) {
					ResponseFilterRetention filterRetention = (ResponseFilterRetention) annotation;
					ignores = filterRetention.ignores();
					break;
				}
			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.getSerializationConfig().addMixInAnnotations(Object.class, ResponseFilterMixIn.class);
			FilterProvider filters = new SimpleFilterProvider().addFilter("ResponseFilterMixIn", SimpleBeanPropertyFilter.serializeAllExcept(ignores));
			String dataStr = mapper.writer(filters).writeValueAsString(responseModel.getData());
			responseModel.setData(mapper.readValue(dataStr, Object.class));
		}
		return responseModel;
	}
}

@JsonFilter("ResponseFilterMixIn")
class ResponseFilterMixIn {
}