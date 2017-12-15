package net.xueshupa.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.order.enums.Payment;
import com.xfsw.order.model.GenerateOrderInfo;
import com.xfsw.session.model.UserSessionModel;

@RestController
@RequestMapping("/order")
public class OrderController {

//	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Value("${weixin.mini.appid}")
	private String weixinAppAppid;
	@Value("${weixin.mini.partnerid}")
	private String weixinAppPartnerid;
	@Value("${weixin.mini.key}")
	private String weixinAppKey;
	@Value("${weixin.mini.prepay.url}")
	private String weixinAppPrepayUrl;
	@Value("${weixin.mini.prepay.encode}")
	private String weixinAppPrepayEncode;
	@Value("${weixin.mini.notify.url}")
	private String weixinAppNotifyUrl;
	
	/**
	 * 购买章节
	 * @param chapterId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@PostMapping(value="orderChapter")
	public ResponseModel orderChapter(Integer chapterId) {
		UserSessionModel u = ThreadUserInfoManager.getUserInfo();
		GenerateOrderInfo orderInfoModel = new GenerateOrderInfo();
		orderInfoModel.setUserId(u.getId());
		orderInfoModel.setTenantId(u.getTenantId());
		orderInfoModel.setPayment(Payment.WX_MINI);
		return new ResponseModel();
	}
	
//	@PostMapping(value = "/wx/prePay")
//	public ResponseModel prePay(String bePaidOrderNumber,HttpServletRequest request) {
//		if(StringUtils.isEmpty(bePaidOrderNumber)) {
//			throw new BusinessException(ErrorConstant.ERROR_BUSINESS_KNOWN,"订单号不能为空！");
//		}
//		
//		Double sumCount = bePaidOrderService.getSumCountByOrderNumber(bePaidOrderNumber);
//		if(sumCount == null)  return new BaseResultModel(ErrorConstant.ERROR_BUSINESS,"订单号信息错误！");
//		// //////////////////////////////////请求参数//////////////////////////////////////
//		String appid = weixinAppAppid;// 微信开放平台审核通过的应用APPID
//		String mch_id = weixinAppPartnerid;// 商户号
//		String device_info = "WEB";// 设备号
//		String nonce_str = RandomUtil.getCharAndNumr(10);// 随机字符串
//		String body = "Materia-订单号："+bePaidOrderNumber; // 商品描述
//		String out_trade_no = bePaidOrderNumber;// 商户订单号
//		int total_fee = (int)(sumCount*100);// 总金额，以分为单位
//		String spbill_create_ip = HttpRequestUtil.getIpAddr(request);
//		String notify_url = weixinAppNotifyUrl;
//		String trade_type = "APP";
//
//		SortedMap<String, Object> packageParams = new TreeMap<String, Object>();
//		packageParams.put("appid", appid);
//		packageParams.put("mch_id", mch_id);
//		packageParams.put("device_info", device_info);
//		packageParams.put("nonce_str", nonce_str);
//		packageParams.put("body", body);
//		packageParams.put("out_trade_no", out_trade_no);
//		packageParams.put("total_fee", total_fee);
//		packageParams.put("spbill_create_ip", spbill_create_ip);
//		packageParams.put("notify_url", notify_url);
//		packageParams.put("trade_type", trade_type);
//		
//		logger.debug(JsonUtil.entity2Json(packageParams));
//
//		String sign = appSign(packageParams);
//		WxPrepayRequester wxPayOrderModel = new WxPrepayRequester(appid, mch_id, device_info, nonce_str, body, sign, out_trade_no, total_fee, spbill_create_ip, notify_url, trade_type);
//
//		String xml = new JaxbUtil(WxPrepayRequester.class).toXml(wxPayOrderModel, true);
//		String result = HttpRequestUtil.post(weixinAppPrepayUrl, xml, weixinAppPrepayEncode);
//		logger.debug("结果："+result);
//
//		WxPrepayResponser wxPayOrderResponser = new JaxbUtil(WxPrepayResponser.class).fromXml(result);
//		WxPrypayResultModel wxPrepayResultModel = new WxPrypayResultModel(weixinAppAppid,weixinAppPartnerid,wxPayOrderResponser.getPrepay_id(),wxPayOrderResponser.getNonce_str());
//		logger.debug("预支付ID："+wxPrepayResultModel.getPrepayId());
//		logger.debug("随机数："+wxPrepayResultModel.getNonceStr());
//		return new BaseResultModel(wxPrepayResultModel);
//	}
}
