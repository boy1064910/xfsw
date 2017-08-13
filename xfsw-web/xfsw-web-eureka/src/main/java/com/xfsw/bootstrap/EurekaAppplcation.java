/**
 * 
 */
package com.xfsw.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心服务启动类
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@EnableEurekaServer // 开启eureka服务
@SpringBootApplication // springBoot注解,spring在springBoot基础之上来构建项目
public class EurekaAppplcation {

	/**
	 * spirng boot的标准入口
	 * @param args
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaAppplcation.class, args);
	}
}
