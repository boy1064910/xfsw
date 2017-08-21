package com.xfsw.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xfsw.business.entity.MessageCode;
import com.xfsw.business.enums.MessageCodeMediumTypeEnum;
import com.xfsw.business.service.MessageCodeService;
import com.xfsw.common.mapper.ICommonMapper;
import com.xfsw.common.util.DomUtil;
import com.xfsw.common.util.HttpRequestUtil;
import com.xfsw.common.util.RandomUtil;
import com.xfsw.common.util.StringUtil;

@Service("messageCodeService")
public class MessageCodeServiceImpl implements MessageCodeService {
	
	//单个账号单个业务当天发送短信次数
	@Value("${message.account.business.day.max.count}")
	int messageAccountBusinessDayMaxCount;
	String messageAccountBusinessDayMaxError = "当前账号：${account}，${type}今天获取验证码次数超过限制：${count}次，如有问题，请打开【学术葩教育资源平台】或者拨打客服电话联系客服人员！";
	
	//单个IP当天发送短信次数
	@Value("${message.ip.day.max.count}")
	int messageIpDayMaxCount;
	String messageIpDayMaxError = "当前IP地址：${ip}，${type}今天获取验证码次数超过限制：${count}次，如有问题，请打开【学术葩教育资源平台】或者拨打客服电话联系客服人员！";
	
	//单个账号单个IP单个业务当天发送短信次数
	@Value("${message.account.ip.day.max.count}")
	int messageAccountIpDayMaxCount;
	String messageAccountIpDayMaxError = "当前账号：${account}，IP地址：${ip}，${type}今天获取验证码次数超过限制：${count}次，如有问题，请打开【学术葩教育资源平台】或者拨打客服电话联系客服人员！";
	
	//单个账号单个IP地址单个业务60秒内发送次数
	@Value("${message.account.ip.business.minute.max.count}")
	int messageAccountIpBusinessMinuteMaxCount;
	String messageAccountIpBusinessMinuteMaxError = "60秒内不允许重复获取短信验证码！";
	
	@Value("${message.send.url}")
	String messageSendUrl;
	
	@Value("${message.send.account}")
	String messageSendAccount;
	
	@Value("${message.send.pwd}")
	String messageSendPwd;

	@Resource(name = "accountCommonMapper")
	ICommonMapper commonMapper;
	
	@PostConstruct
	private void init(){
		System.out.println("init start...");
		System.out.println("init end...");
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public void sendPhoneCode(String account, Integer businessType, String ip) {
		String code = RandomUtil.getRandomNumber(4);
		
		MessageCode messageCode = new MessageCode(MessageCodeMediumTypeEnum.PHONE, code, ip, account, businessType);
		
		Map<String,Object> resultMap = (Map<String,Object>) commonMapper.get("MessageCode.checkMessageCode", messageCode);
		int count0 = (Integer) resultMap.get("c0");
		if(count0>messageAccountBusinessDayMaxCount){
			throw new RuntimeException(StringUtil.variableReplace(messageAccountBusinessDayMaxError, new String[]{account,messageCode.getBusinessTypeRemark(),String.valueOf(messageAccountBusinessDayMaxCount)}));
		}
		int count1 = (Integer) resultMap.get("c1");
		if(count1>messageIpDayMaxCount){
			throw  new RuntimeException(StringUtil.variableReplace(messageIpDayMaxError, new String[]{ip,messageCode.getBusinessTypeRemark(),String.valueOf(messageIpDayMaxCount)}));
		}
		int count2 = (Integer) resultMap.get("c2");
		if(count2>messageAccountIpDayMaxCount){
			throw  new RuntimeException(StringUtil.variableReplace(messageAccountIpDayMaxError, new String[]{account,ip,messageCode.getBusinessTypeRemark(),String.valueOf(messageAccountIpDayMaxCount)}));
		}
		int count3 = (Integer) resultMap.get("c3");
		if(count3>messageAccountIpBusinessMinuteMaxCount){
			throw  new RuntimeException(StringUtil.variableReplace(messageAccountIpBusinessMinuteMaxError));
		}
		
		String content = StringUtil.variableReplace(messageCode.getTemplate(), code);
		// 发送短信
		this.sendSms(content, account);
		// 插入发送数据
		commonMapper.insert("MessageCode.sendMessageCode", messageCode);
	}
	
	public boolean verifyPhoneCode(String account, Integer businessType, String code) {
		MessageCode messageCode = new MessageCode(MessageCodeMediumTypeEnum.PHONE, code, account, businessType);
		Integer id = (Integer) commonMapper.get("MessageCode.verifyCode", messageCode);
		if (id!=null){
			commonMapper.update("MessageCode.updateCodeStatus", id);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void sendSms(String content,String phoneNumber){
		NameValuePair account = new BasicNameValuePair("account", messageSendAccount);
		NameValuePair password = new BasicNameValuePair("password", messageSendPwd);
		NameValuePair mobileParam = new BasicNameValuePair("mobile", phoneNumber);
		NameValuePair contentParam = new BasicNameValuePair("content", content);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(account);
		params.add(password);
		params.add(mobileParam);
		params.add(contentParam);

		Map<String,String> header = new HashMap<String,String>();
		header.put("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
		String SubmitResult = HttpRequestUtil.post(messageSendUrl, params, "UTF-8",header);
		Document doc = DomUtil.string2Document(SubmitResult);
		Element root = doc.getRootElement();
		String code = root.elementText("code");
		String msg = root.elementText("msg");
		if (!"2".equals(code)) {
			throw new RuntimeException("调用接口发送短信失败："+msg);
		}
	}
}
