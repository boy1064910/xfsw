/**
 * 
 */
package com.xfsw.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringCloudApplication
public class ZuulApplication {

	public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
