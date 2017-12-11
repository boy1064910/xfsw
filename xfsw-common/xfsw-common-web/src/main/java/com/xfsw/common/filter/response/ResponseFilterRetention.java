/**
 * 
 */
package com.xfsw.common.filter.response;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 返回结果过滤器注解
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResponseFilterRetention {

	String[] ignores();
}
