/**
 * 
 */
package com.xfsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@ImportResource(locations={"classpath*:spring/spring-service-*.xml"})
@EnableAutoConfiguration(exclude={RedisAutoConfiguration.class,RedisRepositoriesAutoConfiguration.class})
public class EntryApplication {

	public static void main(String[] args) {  
        SpringApplication.run(EntryApplication.class, args);  
    }  
}
