/**
 * 
 */
package com.xfsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = "com.xfsw.controller")
public class EntryApplication {

	public static void main(String[] args) {  
        SpringApplication.run(EntryApplication.class, args);  
    }  
}
