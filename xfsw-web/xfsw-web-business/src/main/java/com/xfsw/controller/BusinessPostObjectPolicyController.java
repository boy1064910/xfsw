package com.xfsw.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;

import cn.com.decked.common.classes.ResponseModel;
import cn.com.decked.common.util.Base64;
import cn.com.decked.common.util.JsonUtil;

@Controller
@RequestMapping("/business/postObjectPolicy")
public class BusinessPostObjectPolicyController {
	@Value("${decked.oss.endpoint}")
	private String endpoint;
	@Value("${decked.oss.accessId}")
	private String accessId;
	@Value("${decked.oss.accessKey}")
	private String accessKey;
	@Value("${decked.oss.bucket}")
	private String bucket;
	@Value("${decked.oss.dir}")
	private String dir;
	@Value("${decked.oss.expire}")
	private int expire;
	
	@Value("${decked.system.domain.name}")
	private String domainName;

	@RequestMapping("/successReceive")
	@ResponseBody
	public ResponseModel successReceive(String filename) {
		return new ResponseModel(filename);
	}

	@RequestMapping("/list")
	@ResponseBody
	public ResponseModel list() {
		Map<Object, Object> map = new HashMap<>();
		map.put("callbackUrl", "http://"+domainName+"/decked-web-platform-business/business/postObjectPolicy/successReceive.shtml");
		map.put("callbackBody", "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
		map.put("callbackBodyType", "application/x-www-form-urlencoded");
		/* map.put("callbackHost", "business.materia.mobi"); */
		String callbackBody = JsonUtil.entity2Json(map);
		String base64CallbackBody = Base64.encode(callbackBody.getBytes());

		ClientConfiguration conf = new ClientConfiguration();
		conf.setSupportCname(true);
		String host = "http://" + bucket + "." + endpoint;
		OSSClient client = new OSSClient(endpoint, accessId, accessKey, conf);

		long expireTime = expire;
		long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
		Date expiration = new Date(expireEndTime);
		PolicyConditions policyConds = new PolicyConditions();
		policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
		policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
		String postPolicy = client.generatePostPolicy(expiration, policyConds);
		byte[] binaryData = null;
		try {
			binaryData = postPolicy.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String encodedPolicy = BinaryUtil.toBase64String(binaryData);
		String postSignature = client.calculatePostSignature(postPolicy);

		Map<String, String> respMap = new LinkedHashMap<String, String>();
		respMap.put("accessid", accessId);
		respMap.put("policy", encodedPolicy);
		respMap.put("signature", postSignature);
		// respMap.put("expire", formatISO8601Date(expiration));
		respMap.put("dir", dir);
		respMap.put("host", host);
		respMap.put("expire", String.valueOf(expireEndTime / 1000));
		respMap.put("callback", base64CallbackBody);
		return new ResponseModel(respMap);
	}
}
