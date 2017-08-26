/**
 * 
 */
package com.xfsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@EnableDiscoveryClient
@SpringBootApplication
@ImportResource(locations={"classpath:spring-web.xml","classpath*:spring/spring-service-*.xml"})
@EnableAutoConfiguration(exclude={RedisAutoConfiguration.class})
public class AuthorityApplication {
	
	/**
	 * spirng boot的标准入口
	 * @param args
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthorityApplication.class, args);
	}
}
