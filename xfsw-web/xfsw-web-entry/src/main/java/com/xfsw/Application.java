/**
 * 
 */
package com.xfsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
//@EnableEurekaClient
@SpringBootApplication
@ImportResource(locations={"classpath:spring-web.xml"})
@EnableAutoConfiguration(exclude={RedisAutoConfiguration.class,RedisRepositoriesAutoConfiguration.class})
public class Application {

	public static void main(String[] args) {  
        SpringApplication.run(Application.class, args);  
    }  
}
