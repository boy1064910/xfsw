/**
 * 
 */
package net.xueshupa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import net.xueshupa.filter.WebSiteMeshFilter;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@ImportResource(locations={"classpath:spring-web.xml","classpath*:spring/spring-service-*.xml"})
public class AcadamicManagerApplication {

	public static void main(String[] args) {  	
        SpringApplication.run(AcadamicManagerApplication.class, args);  
    }  
	
	@Bean
	public FilterRegistrationBean siteMeshFilter() {
		FilterRegistrationBean fitler = new FilterRegistrationBean();
		WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();
		fitler.setFilter(siteMeshFilter);
		return fitler;
	}
}
