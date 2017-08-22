/**
 * 
 */
package com.xfsw;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@EnableZuulProxy  
@SpringCloudApplication    
public class ZuulApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);  
    }
	
//	@Bean
//    public AccessFilter accessFilter() {
//        return new AccessFilter();
//    }
}
