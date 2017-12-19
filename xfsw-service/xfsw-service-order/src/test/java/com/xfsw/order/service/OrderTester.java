package com.xfsw.order.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xfsw.common.mq.consts.QueueDestination;
import com.xfsw.order.service.OrderService;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-service-tester.xml","classpath*:spring/spring-service-*.xml"})
@Rollback(true)
public class OrderTester {

	@Autowired
	OrderService orderService;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Test
	public void jms() {
		WxOrderPayInfo model  = new WxOrderPayInfo("2017121919100499", "4200000038201712197343509514", 0.01);
		jmsTemplate.convertAndSend(QueueDestination.WX_ORDER_PAY_INFO_QUEUE, model);
	}
}
