package com.xfsw.account.bootstrap;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xfsw.common.util.StringUtil;

public class Bootstrap {

	private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) throws IOException {
		String environment = System.getProperty("environment");// 加载spring配置文件
		if (StringUtil.isEmpty(environment)) {
			environment = "online";
			System.setProperty("environment", environment);
		}

		context = new ClassPathXmlApplicationContext(new String[] { "classpath*:spring/spring-service.xml", "classpath*:spring/dubbo-service.xml", "classpath*:conf/" + environment + "/dubbo.xml" });
		context.start();
		logger.info("XFSW账号服务启动成功...");
		Object lock = new Object();
		synchronized (lock) {
			try {
				while (true) {
					lock.wait();
				}
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
