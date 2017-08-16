/**
 * 
 */
package com.xfsw.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 注册中心服务启动类
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@EnableDiscoveryClient 
@EnableConfigServer
@SpringBootApplication // springBoot注解,spring在springBoot基础之上来构建项目
public class ConfigApplication {

	/**
	 * spirng boot的标准入口
	 * @param args
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}
}
