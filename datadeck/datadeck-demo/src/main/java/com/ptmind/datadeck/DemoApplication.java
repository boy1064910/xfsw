package com.ptmind.datadeck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created with com.ptmind.datadeck.dashboard.
 * Author: ZhaiChen
 * Date: 2017/7/14 18:48
 * Description:
 */

@SpringBootApplication
@ImportResource(locations={"classpath:spring-web.xml"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
}
