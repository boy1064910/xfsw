/**
 * 
 */
package net.xueshupa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@EnableEurekaClient
@SpringBootApplication
@ImportResource(locations={"classpath:spring-web.xml","classpath*:spring/spring-service-*.xml"})
public class AcadamicManagerApplication {

	public static void main(String[] args) {  	
        SpringApplication.run(AcadamicManagerApplication.class, args);  
    }  
}
