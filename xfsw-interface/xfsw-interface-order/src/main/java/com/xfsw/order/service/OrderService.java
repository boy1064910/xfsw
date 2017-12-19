/**
 * 
 */
package com.xfsw.order.service;

import java.util.Map;

import com.xfsw.order.model.wx.WxGenerateOrderInfo;
import com.xfsw.order.model.wx.WxPayInfo;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public interface OrderService {

	Map<String, Object> generateWxOrder(WxGenerateOrderInfo orderInfo,WxPayInfo wxPayInfo);
	
	boolean wxNotifyCallback(String context,String appKey);
}
