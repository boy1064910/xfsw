/**
 * 
 */
package net.xueshupa.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.xueshupa.filter.WebSiteMeshFilter;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public FilterRegistrationBean siteMeshFilter() {
		FilterRegistrationBean fitler = new FilterRegistrationBean();
		WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();
		fitler.setFilter(siteMeshFilter);
		return fitler;
	}
}
