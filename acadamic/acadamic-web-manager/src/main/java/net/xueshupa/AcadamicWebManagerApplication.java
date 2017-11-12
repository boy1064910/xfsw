/**
 * 
 */
package net.xueshupa;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.xfsw.common.filter.WebSiteMeshFilter;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
//@EnableEurekaClient
@SpringBootApplication
@ImportResource(locations={"classpath:spring-web.xml","classpath*:spring/spring-service-*.xml"})
@EnableAutoConfiguration(exclude={RedisAutoConfiguration.class,RedisRepositoriesAutoConfiguration.class,DataSourceAutoConfiguration.class})
public class AcadamicWebManagerApplication {

	public static void main(String[] args) {
		String env = System.getProperty("env");//加载spring配置文件
		if (StringUtils.isEmpty(env)){
			System.setProperty("env", "online");
		}
        SpringApplication.run(AcadamicWebManagerApplication.class, args);  
    }  
	
	@Bean
	public FilterRegistrationBean siteMeshFilter() {
		FilterRegistrationBean fitler = new FilterRegistrationBean();
		WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();
		fitler.setFilter(siteMeshFilter);
		return fitler;
	}
}
