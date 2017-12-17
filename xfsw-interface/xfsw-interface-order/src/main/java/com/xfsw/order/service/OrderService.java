/**
 * 
 */
package com.xfsw.order.service;

import com.xfsw.order.model.wx.WxGenerateOrderInfo;
import com.xfsw.order.model.wx.WxGenerateOrderResponser;
import com.xfsw.order.model.wx.WxPayInfo;

/**
 * 
 * @author xiaopeng.liu
 * @version 
 */
public interface OrderService {

	WxGenerateOrderResponser generateWxOrder(WxGenerateOrderInfo orderInfo,WxPayInfo wxPayInfo);
	
	boolean notifyCallback(String context,String appKey);
}
