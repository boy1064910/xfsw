/**
 * 
 */
package com.xfsw.order.service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.mq.consts.QueueDestination;
import com.xfsw.common.mq.model.OrderReceiverModel;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.order.entity.OrderDetail;
import com.xfsw.order.entity.OrderInfo;
import com.xfsw.order.enums.OrderStatus;

/**
 * 订单付款信息消费器
 * @author xiaopeng.liu
 * @version 
 */
@Component
public class OrderReceiver extends MessageListenerAdapter {

	@Resource(name="orderCommonMapper")
	ICommonMapper orderCommonMapper;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Transactional(value="orderTxManager")
	@JmsListener(destination = QueueDestination.WX_ORDER_PAY_INFO_QUEUE, concurrency = "1",containerFactory="jmsListenerContainerFactory")
	public void onMessage(Message message, Session session) throws JMSException {
		WxOrderPayInfo wxOrderPayInfo = (WxOrderPayInfo) getMessageConverter().fromMessage(message);
		logger.debug("===接收到的参数信息===");
		logger.debug(JsonUtil.entity2Json(wxOrderPayInfo));
		OrderInfo orderInfo = orderCommonMapper.get(OrderInfo.class, "orderNumber", wxOrderPayInfo.getOrderNumber());
		Date currentTime = new Date();
		orderInfo.setOrderNumber(wxOrderPayInfo.getOrderNumber());
		orderInfo.setPayCount(wxOrderPayInfo.getTradeSum());
		orderInfo.setPayTime(currentTime);
		orderInfo.setLastUpdateTime(currentTime);
		orderInfo.setTradeNumber(wxOrderPayInfo.getTradeNumber());
		orderInfo.setStatus(OrderStatus.PAYED.getStatus());
		String sql = "UPDATE OrderInfo SET payCount = #{payCount},payTime = #{payTime},lastUpdateTime = #{lastUpdateTime},tradeNumber = #{tradeNumber} WHERE orderNumber = #{orderNumber}";
		orderCommonMapper.updateBySql(sql, orderInfo);
		
		//发送业务逻辑
		String bizCode = orderInfo.getBizCode();
		if(!StringUtils.isEmpty(bizCode)) {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("orderInfoId", orderInfo.getId());
			List<OrderDetail> orderDetailList = orderCommonMapper.selectList(OrderDetail.class, params);
			List<String> detailBizExtra = orderDetailList.stream().map(x->x.getDetailExtra()).collect(Collectors.toList());
			
			OrderReceiverModel model = new OrderReceiverModel();
			model.setUserId(orderInfo.getUserId());
			model.setBizExtra(orderInfo.getBizExtra());
			model.setDetailBizExtra(detailBizExtra);
			jmsTemplate.convertAndSend(bizCode, model);
		}
		message.acknowledge();
	}
}


class WxOrderPayInfo implements Serializable {

	private static final long serialVersionUID = -4477682309631293996L;
	public WxOrderPayInfo(){}
	
	public WxOrderPayInfo(String orderNumber, String tradeNumber, Double tradeSum) {
		super();
		this.orderNumber = orderNumber;
		this.tradeNumber = tradeNumber;
		this.tradeSum = tradeSum;
	}
	
	private String orderNumber;
	private String tradeNumber;
	private Double tradeSum;
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getTradeNumber() {
		return tradeNumber;
	}
	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}
	public Double getTradeSum() {
		return tradeSum;
	}
	public void setTradeSum(Double tradeSum) {
		this.tradeSum = tradeSum;
	}
}
