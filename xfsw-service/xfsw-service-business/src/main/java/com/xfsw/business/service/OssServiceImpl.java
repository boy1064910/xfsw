package com.xfsw.business.service;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CopyObjectRequest;
import com.aliyun.oss.model.CopyObjectResult;

@Lazy
@Service("ossService")
public class OssServiceImpl implements OssService {

	private static Logger logger = LoggerFactory.getLogger(OssServiceImpl.class);
	
	@Value("${ali.oss.endpoint}")
	private String endpoint;

	@Value("${ali.oss.accessId}")
	private String accessId;

	@Value("${ali.oss.accessKey}")
	private String accessKey;
	
	@Value("${ali.oss.bucket}")
	private String bucket;//存储空间名称
	
	@Value("${ali.oss.define.domain}")
	private String defineDomain;//自定义域名
	
	private String defaultTmpFolder = "tmp/";

	private OSSClient client = null;

	@PostConstruct
	private void init() {
		ClientConfiguration conf = new ClientConfiguration();
		conf.setSupportCname(true);
		client = new OSSClient(endpoint, accessId, accessKey, conf);
	}


	@Override
	public String saveObject(String fileName, String destFolder) {
		if(StringUtils.isEmpty(fileName)) {
			return null;
		}
		// 创建CopyObjectRequest对象
		CopyObjectRequest copyObjectRequest = new CopyObjectRequest(bucket, defaultTmpFolder + fileName, bucket, destFolder + fileName);
		// 复制Object
		CopyObjectResult result = client.copyObject(copyObjectRequest);
		logger.info("oss copy file result:"+result.getETag());
		return this.defineDomain + destFolder + fileName;
	}
	
	@Override
	public String[] saveObject(String[] fileNames,String destFolder){
		if(!ArrayUtils.isEmpty(fileNames)) {
			return null;
		}
		String[] fileUrls = new String[fileNames.length];
		int index = 0;
		for(String fileName:fileNames) {
			fileUrls[index] = this.saveObject(fileName, destFolder);
			index++;
		}
		return fileUrls;
	}
	
	@Override
	public String getDefineDomain(){
		return this.defineDomain;
	}
}
