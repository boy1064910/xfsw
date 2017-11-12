
package com.xfsw.common.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.xfsw.common.classes.BusinessException;
import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.consts.ErrorConstant;
import com.xfsw.common.util.HttpServletRequestUtil;
import com.xfsw.common.util.MapUtil;

/**
 * 异常处理类
 * @author liuxiaopeng
 */
public class ExceptionHandler implements HandlerExceptionResolver {
	protected static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
		ResponseModel resultModel = new ResponseModel();
		if(e instanceof BusinessException){
			String msg = ((BusinessException) e).getMessage();
			int code = ((BusinessException) e).getCode();
			if(code == 0){
				code = ErrorConstant.ERROR_BUSINESS_KNOWN;
			}
			resultModel.setCode(code);
			resultModel.setMsg(msg);
//		} else if (e instanceof RpcException) {//TODO工程转换成dubbo的时候开启
//			int resultCode = ((RpcException) e).getCode();
//			resultModel.setResultCode(resultCode);
//			resultModel.setMsg(((RpcException) e).getMessage());
//			if(resultCode==0){
//				//TODO 记录错误日志
//			}
		}
		else if(e instanceof RuntimeException){
			resultModel.setCode(ErrorConstant.ERROR_SYSTEM_KNOWN);
			logger.error("系统程序异常", e);
		}
		else{
			resultModel.setCode(ErrorConstant.ERROR_SYSTEM_KNOWN);
		}
		
		ModelAndView mav = new ModelAndView();
		if (HttpServletRequestUtil.isAjaxRequest(request)) {// ajax请求
			MappingJackson2JsonView view = new MappingJackson2JsonView();
			Map<String, Object> resultModelMap = MapUtil.entityToMap(resultModel);
			view.setAttributesMap(resultModelMap);
			mav.setView(view);
		} else {// html请求,重定向
			mav.addObject("resultModel", resultModel);
			mav.setViewName("error");
		}
		return mav;
	}
}
