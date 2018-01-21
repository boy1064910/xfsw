package net.xueshupa.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xfsw.common.classes.ResponseModel;
import com.xfsw.common.mq.consts.QueueDestination;
import com.xfsw.common.thread.ThreadUserInfoManager;
import com.xfsw.common.util.HttpServletRequestUtil;
import com.xfsw.common.util.JaxbUtil;
import com.xfsw.order.enums.Payment;
import com.xfsw.order.model.GenerateOrderDetail;
import com.xfsw.order.model.wx.WxGenerateOrderInfo;
import com.xfsw.order.model.wx.WxPayInfo;
import com.xfsw.order.service.OrderService;
import com.xfsw.session.model.UserSessionModel;
import com.xfsw.session.service.UserSessionService;

import net.xueshupa.entity.Chapter;
import net.xueshupa.entity.Course;
import net.xueshupa.service.ChapterService;
import net.xueshupa.service.CourseService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Value("${wx.mini.appid}")
	private String wxMiniAppId;
	@Value("${wx.mini.partnerid}")
	private String wxMiniPartnerid;
	@Value("${wx.mini.key}")
	private String wxMiniKey;
	@Value("${wx.mini.notify.url}")
	private String wxNotifyUrl;
	
	@Value("${acadamic.tenant.id}")
	private Integer acadamicTenantId;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	ChapterService chapterService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserSessionService userSessionService;
	
	/**
	 * 购买章节
	 * @param chapterId
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@PostMapping(value="orderChapter")
	public ResponseModel orderChapter(Integer chapterId,HttpServletRequest request) {
		Chapter chapter = chapterService.getById(chapterId);
		
		UserSessionModel u = ThreadUserInfoManager.getUserInfo();
		WxGenerateOrderInfo orderInfoModel = new WxGenerateOrderInfo();
		orderInfoModel.setUserId(u.getId());
		orderInfoModel.setTenantId(acadamicTenantId);
		orderInfoModel.setPayment(Payment.WX_MINI);
		orderInfoModel.setSumCount(chapter.getPrice());
		orderInfoModel.setBizCode(QueueDestination.ACADAMIC_CHAPTER_ORDER);
		orderInfoModel.setBizExtra(chapter.getCourseId().toString());
		//设置openid为小程序的openid，后期接入app或者公众号需要调整
		orderInfoModel.setOpenId(ThreadUserInfoManager.getUserInfo().getWxOpenIdExtra().getMiniOpenId());
		
		List<GenerateOrderDetail> detailList = new ArrayList<GenerateOrderDetail>();
		GenerateOrderDetail detail = new GenerateOrderDetail();
		detail.setCount(1);
		detail.setOriginPrice(chapter.getOriginPrice());
		detail.setPrice(chapter.getPrice());
		detail.setDetailExtra(chapter.getId().toString());
		detailList.add(detail);
		orderInfoModel.setDetailList(detailList);
		
		WxPayInfo wxPayInfo = new WxPayInfo();
		wxPayInfo.setAppId(wxMiniAppId);
		wxPayInfo.setAppKey(wxMiniKey);
		wxPayInfo.setBody(chapter.getName());
		wxPayInfo.setDeviceInfo("WEB");
		wxPayInfo.setMchId(wxMiniPartnerid);
		wxPayInfo.setNotifyUrl(wxNotifyUrl);
		wxPayInfo.setSpbillCreateIp(HttpServletRequestUtil.getIpAddr(request));
		wxPayInfo.setTradeType("JSAPI");
		
		return new ResponseModel(orderService.generateWxOrder(orderInfoModel, wxPayInfo));
	}
	
	/**
	 * 
	 * @param chapterIds
	 * @param courseId
	 * @param allBuyed
	 * @param request
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	@PostMapping(value="orderChapterList")
	public ResponseModel orderChapterList(Integer[] chapterIds,Integer courseId,boolean allBuyed,HttpServletRequest request) {
		Course course = courseService.getById(courseId);
		List<Integer> chapterIdList = Arrays.asList(chapterIds);
		List<Chapter> chapterList = chapterService.selectListByIdList(chapterIdList);
		Double sumPrice = chapterList.stream().mapToDouble(x->x.getPrice()).sum();
		
		UserSessionModel u = ThreadUserInfoManager.getUserInfo();
		WxGenerateOrderInfo orderInfoModel = new WxGenerateOrderInfo();
		orderInfoModel.setUserId(u.getId());
		orderInfoModel.setTenantId(acadamicTenantId);
		orderInfoModel.setPayment(Payment.WX_MINI);
		orderInfoModel.setSumCount(sumPrice);
		orderInfoModel.setBizCode(QueueDestination.ACADAMIC_CHAPTER_ORDER);
		orderInfoModel.setBizExtra(courseId.toString());
		//设置openid为小程序的openid，后期接入app或者公众号需要调整
		orderInfoModel.setOpenId(ThreadUserInfoManager.getUserInfo().getWxOpenIdExtra().getMiniOpenId());
		
		List<GenerateOrderDetail> detailList = chapterList.stream().map(x->{
			GenerateOrderDetail detail = new GenerateOrderDetail();
			detail.setCount(1);
			detail.setOriginPrice(x.getOriginPrice());
			detail.setPrice(x.getPrice());
			detail.setDetailExtra(x.getId().toString());
			return detail;
		}).collect(Collectors.toList());
		orderInfoModel.setDetailList(detailList);
		
		WxPayInfo wxPayInfo = new WxPayInfo();
		wxPayInfo.setAppId(wxMiniAppId);
		wxPayInfo.setAppKey(wxMiniKey);
		if(!allBuyed){//部分章节批量购买
			wxPayInfo.setBody(course.getName()+"(部分购买)");
		}
		else{
			wxPayInfo.setBody(course.getName());
		}
		wxPayInfo.setDeviceInfo("WEB");
		wxPayInfo.setMchId(wxMiniPartnerid);
		wxPayInfo.setNotifyUrl(wxNotifyUrl);
		wxPayInfo.setSpbillCreateIp(HttpServletRequestUtil.getIpAddr(request));
		wxPayInfo.setTradeType("JSAPI");
		
		return new ResponseModel(orderService.generateWxOrder(orderInfoModel, wxPayInfo));
	}
	
	@PostMapping(value="notify")
	public String notify(HttpServletRequest request,HttpServletRequest response){
		byte[] buffer = new byte[64*1024];
		InputStream in;
		try {
			in = request.getInputStream();
			int length = in.read(buffer);
			String encode = request.getCharacterEncoding();
			byte[] data = new byte[length];
			System.arraycopy(buffer, 0, data, 0, length);
			String context = new String(data, encode);
			boolean result = orderService.wxNotifyCallback(context, wxMiniKey);
			WxNotifyResponser wx = null;
			if(result){
				wx = new WxNotifyResponser("SUCCESS","");
			}
			else{
				wx = new WxNotifyResponser("FAIL","微信支付失败！");
			}
			return new JaxbUtil(WxNotifyResponser.class).toXml(wx, true);
		} catch (IOException e) {
			logger.error("微信支付结果通知xml数据接收转换异常！",e);
			WxNotifyResponser wx = new WxNotifyResponser("FAIL","微信支付结果通知xml数据接收转换异常！");
			return new JaxbUtil(WxNotifyResponser.class).toXml(wx, true);
		}
	}
}

@XmlRootElement(name="xml")
class WxNotifyResponser {

	private String return_code;
	private String return_msg;
	
	public WxNotifyResponser() {
		super();
	}
	
	public WxNotifyResponser(String return_code, String return_msg) {
		super();
		this.return_code = return_code;
		this.return_msg = return_msg;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
}

/**
 * 小程序微信支付统一下单接口返回结构
 * @author xiaopeng.liu
 * @version 0.0.1
 */
class WxPaymentResponser{
	String timeStamp;
	String nonceStr;
	String packageInfo;
	String signType;
	String paySign;
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPackageInfo() {
		return packageInfo;
	}
	public void setPackageInfo(String packageInfo) {
		this.packageInfo = packageInfo;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
}
