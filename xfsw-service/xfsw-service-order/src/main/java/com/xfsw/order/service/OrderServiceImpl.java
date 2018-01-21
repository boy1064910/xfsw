/**
 * 
 */
package com.xfsw.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.mq.consts.QueueDestination;
import com.xfsw.common.util.DateUtil;
import com.xfsw.common.util.HttpRequestUtil;
import com.xfsw.common.util.JaxbUtil;
import com.xfsw.common.util.JsonUtil;
import com.xfsw.common.util.MD5Util;
import com.xfsw.common.util.MapUtil;
import com.xfsw.common.util.RandomUtil;
import com.xfsw.order.entity.OrderDetail;
import com.xfsw.order.entity.OrderInfo;
import com.xfsw.order.model.wx.WxGenerateOrderInfo;
import com.xfsw.order.model.wx.WxPayInfo;
import com.xfsw.order.model.wx.api.WxNotifyRequester;
import com.xfsw.order.model.wx.api.WxUnionOrderRequester;
import com.xfsw.order.model.wx.api.WxUnionOrderResponser;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
@Service
public class OrderServiceImpl implements OrderService {

	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	/**微信统一下单接口url*/
	private String wxUnionOrderApiUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	@Resource(name="orderCommonMapper")
	ICommonMapper commonMapper;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Override
	@Transactional(value="orderTxManager")
	public Map<String, Object> generateWxOrder(WxGenerateOrderInfo wxGenerateOrderInfo,WxPayInfo wxPayInfo) {
		Date currentTime = new Date();
		// TODO 参数教研
		String orderNumber = DateUtil.date2Str(currentTime, "yyyyMMddHHmmSSSS");
		String nonceStr = RandomUtil.getCharAndNumr(10);// 随机字符串
		
		SortedMap<String, Object> packageParams = new TreeMap<String, Object>();
		packageParams.put("appid", wxPayInfo.getAppId());
		packageParams.put("mch_id", wxPayInfo.getMchId());
		packageParams.put("trade_type", wxPayInfo.getTradeType());
		packageParams.put("notify_url", wxPayInfo.getNotifyUrl());
		packageParams.put("nonce_str", nonceStr);
		packageParams.put("spbill_create_ip", wxPayInfo.getSpbillCreateIp());
		packageParams.put("body", wxPayInfo.getBody());
		packageParams.put("out_trade_no", orderNumber);
		packageParams.put("openid", wxGenerateOrderInfo.getOpenId());
		int totalFee = (int) (wxGenerateOrderInfo.getSumCount()*100);
		packageParams.put("total_fee", totalFee);
		
		logger.debug("===微信交易参数信息===");
		logger.debug(JsonUtil.entity2Json(packageParams));
		
		String sign = this.sign(packageParams, wxPayInfo.getAppKey());
		WxUnionOrderRequester wxUnionOrderRequester = MapUtil.map2Entity(packageParams, WxUnionOrderRequester.class);
		wxUnionOrderRequester.setSign(sign);
		
		String xml = new JaxbUtil(WxUnionOrderRequester.class).toXml(wxUnionOrderRequester, true);
		logger.debug("===微信统一下单接口请求参数===");
		logger.debug(xml);
		String result = HttpRequestUtil.post(wxUnionOrderApiUrl, xml, "UTF-8");
		logger.debug("===微信交易结果信息===");
		logger.debug(result);
		
		WxUnionOrderResponser wxUnionOrderResponser = new JaxbUtil(WxUnionOrderResponser.class).fromXml(result);
		
		//生成数据库订单信息
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setTenantId(wxGenerateOrderInfo.getTenantId());//机构ID
		orderInfo.setUserId(wxGenerateOrderInfo.getUserId());
		orderInfo.setCreateTime(currentTime);
		orderInfo.setLastUpdater(wxGenerateOrderInfo.getOperator());
		orderInfo.setLastUpdateTime(currentTime);
		orderInfo.setOrderNumber(orderNumber);
		orderInfo.setPayment(wxGenerateOrderInfo.getPayment().toString());
		orderInfo.setSumCount(wxGenerateOrderInfo.getSumCount());
		orderInfo.setBizCode(wxGenerateOrderInfo.getBizCode());
		orderInfo.setBizExtra(wxGenerateOrderInfo.getBizExtra());
		Map<String,String> paymentExtra = new HashMap<String,String>();
		paymentExtra.put("openId", wxGenerateOrderInfo.getOpenId());
		orderInfo.setPaymentExtra(JsonUtil.entity2Json(paymentExtra));
		commonMapper.insert(OrderInfo.class, orderInfo);
		
		wxGenerateOrderInfo.getDetailList().forEach(x->{
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setCount(x.getCount());
			orderDetail.setDetailExtra(x.getDetailExtra());
			orderDetail.setLastUpdater(wxGenerateOrderInfo.getOperator());
			orderDetail.setLastUpdateTime(currentTime);
			orderDetail.setOrderInfoId(orderInfo.getId());
			orderDetail.setOriginPrice(x.getOriginPrice());
			orderDetail.setPrice(x.getPrice());
			commonMapper.insert(OrderDetail.class, orderDetail);
		});

		SortedMap<String, Object> paySignParams = new TreeMap<String,Object>();
		paySignParams.put("timeStamp", String.valueOf(currentTime.getTime()/1000));
		paySignParams.put("nonceStr", nonceStr);
		paySignParams.put("package", "prepay_id="+wxUnionOrderResponser.getPrepay_id());
		paySignParams.put("signType", "MD5");
		paySignParams.put("appId", wxPayInfo.getAppId());
		String paySign = this.sign(paySignParams, wxPayInfo.getAppKey());
		paySignParams.put("paySign", paySign);
		return paySignParams;
	}
	
	@Override
	public boolean wxNotifyCallback(String context,String appKey){
		logger.debug("===微信支付notify消息===");
		logger.debug(context);
		WxNotifyRequester wxNotifyRequester =new JaxbUtil(WxNotifyRequester.class).fromXml(context);
		if(wxNotifyRequester.getResult_code().equals("SUCCESS")){//交易结果成功
			String resultSign = wxNotifyRequester.getSign();//获取微信回调的加密参数
			wxNotifyRequester.setSign(null);
			SortedMap<String, Object> packageParams = MapUtil.pojoToSortedMapNotNullField(wxNotifyRequester);
			String checkedSign = sign(packageParams,appKey);
			if(checkedSign.equals(resultSign)){//校验通过
				logger.debug("订单号："+wxNotifyRequester.getOut_trade_no()+"微信回调结果校验通过！");
				// 商户订单号
				String out_trade_no = wxNotifyRequester.getOut_trade_no();
				// 微信支付订单号
				String trade_no = wxNotifyRequester.getTransaction_id();
				// 用户在商户appid下的唯一标识
//				String openid = wxNotifyRequester.getOpenid();
				// 现金支付金额(返回结果以分为单位，换算单位为元)
				double total_fee = (double)wxNotifyRequester.getTotal_fee()/100;
				logger.info("交易金额："+total_fee);
				
				WxOrderPayInfo model  = new WxOrderPayInfo(out_trade_no, trade_no, total_fee);
				jmsTemplate.convertAndSend(QueueDestination.WX_ORDER_PAY_INFO_QUEUE, model);
				
				return true;
			}
			else{
				logger.error("===微信校验失败===");
				return false;
			}
		}
		else{//交易结果失败
			logger.error("===微信交易失败===");
			return false;
		}
	}
	
	private String sign(SortedMap<String, Object> packageParams,String appKey){
		StringBuffer unsign = new StringBuffer();
		for (Map.Entry<String, Object> entry : packageParams.entrySet()) {
			unsign.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		unsign.append("key="+appKey);// 商户密钥
		logger.debug("===微信待签名信息===");
		logger.debug(unsign.toString());
		String sign = MD5Util.md5(unsign.toString()).toUpperCase();// 签名
		logger.debug("===微信签名信息===");
		logger.debug(sign);
		return sign;
	}
}
