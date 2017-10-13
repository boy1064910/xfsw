package com.xfsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.xfsw.common.filter.WebSiteMeshFilter;

/**
 * 权限web服务系统启动类
 * @author xiaopeng.liu
 * @version 0.0.1
 */
//@EnableDiscoveryClient
@SpringBootApplication
@ImportResource(locations={"classpath:spring-web.xml"})
@EnableAutoConfiguration(exclude={RedisAutoConfiguration.class,RedisRepositoriesAutoConfiguration.class})
public class WebAuthApplication {
	
	/**
	 * spirng boot的标准入口
	 * @param args
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebAuthApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean siteMeshFilter() {
		FilterRegistrationBean fitler = new FilterRegistrationBean();
		WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();
		fitler.setFilter(siteMeshFilter);
		return fitler;
	}
}
