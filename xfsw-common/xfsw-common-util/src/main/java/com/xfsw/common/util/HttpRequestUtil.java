package com.xfsw.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpRequestUtil {

//	private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

	private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

//	public static void downloadFile(String url, String filePath) {
//		long startTime = System.currentTimeMillis();
//		HttpGet httpget = null;
//		InputStream instream = null;
//		try {
//			httpget = new HttpGet(url);
//			// 伪装成google的爬虫JAVA问题查询
//			httpget.setHeader("User-Agent", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
//			// Execute HTTP request
//			logger.info("executing request " + httpget.getURI());
//			HttpResponse response = httpClient.execute(httpget);
//			FileUtil.createFile(filePath);
//			File storeFile = new File(filePath);
//			FileOutputStream output = new FileOutputStream(storeFile);
//			// 得到网络资源的字节数组,并写入文件
//			HttpEntity entity = response.getEntity();
//			if (entity != null) {
//				instream = entity.getContent();
//				byte b[] = new byte[1024];
//				int j = 0;
//				while ((j = instream.read(b)) != -1) {
//					output.write(b, 0, j);
//				}
//				output.flush();
//				output.close();
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("url:" + url + ",下载文件失败！" + e.getMessage(), e);
//		}
//		TimeUtil.loggerLostTime(startTime);
//	}
//	
	/**
	 * 通过http链接获取文件资源
	 * @param url
	 * @return
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年7月23日下午3:30:21
	 */
//	public static InputStream downloadInputStream(String url) {
//		long startTime = System.currentTimeMillis();
//		HttpGet httpget = null;
//		try {
//			httpget = new HttpGet(url);
//			// 伪装成google的爬虫JAVA问题查询
//			httpget.setHeader("User-Agent", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
//			// Execute HTTP request
//			logger.info("executing request " + httpget.getURI());
//			HttpResponse response = httpClient.execute(httpget);
//			// 得到网络资源的字节数组,并写入文件
//			HttpEntity entity = response.getEntity();
//			TimeUtil.loggerLostTime(startTime);
//			if (entity != null) {
//				return entity.getContent();
//			}
//			else{
//				throw new RuntimeException("url:" + url + ",下载文件为空！");
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("url:" + url + ",下载文件失败！" + e.getMessage(), e);
//		}
//	}

	/**
	 * 通过http链接获取文件资源
	 * @param url	链接
	 * @param params	参数
	 * @return
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年7月23日下午3:36:59
	 */
//	public static InputStream downloadInputStream(String url,List<NameValuePair> params){
//		long startTime = System.currentTimeMillis();
//		HttpGet httpget = null;
//		try {
//			String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(params));
//			httpget = new HttpGet(url+"?"+paramsStr);
//			// 伪装成google的爬虫JAVA问题查询
//			httpget.setHeader("User-Agent", "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)");
//			// Execute HTTP request
//			logger.info("executing request " + httpget.getURI());
//			HttpResponse response = httpClient.execute(httpget);
//			// 得到网络资源的字节数组,并写入文件
//			HttpEntity entity = response.getEntity();
//			TimeUtil.loggerLostTime(startTime);
//			if (entity != null) {
//				return entity.getContent();
//			}
//			else{
//				throw new RuntimeException("url:" + url + ",下载文件为空！");
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("url:" + url + ",下载文件失败！" + e.getMessage(), e);
//		}
//	}
	
	public static String get(String url) {
		HttpGet httpget = new HttpGet(url);
		HttpResponse httpResponse;
		try {
			httpResponse = httpClient.execute(httpget);
			return EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			throw new RuntimeException("http-get请求链接" + url + "失败！", e);
		}
	}

	public static String get(String url, List<NameValuePair> params) {
		String body = null;
		try {
			HttpGet httpget = new HttpGet();// Get请求
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params));// 设置参数
			httpget.setURI(new URI(url + "?" + str));
			HttpResponse httpresponse = httpClient.execute(httpget);// 发送请求
			HttpEntity entity = httpresponse.getEntity();// 获取返回数据
			body = EntityUtils.toString(entity);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
			return body;
		} catch (ParseException | URISyntaxException | IOException e) {
			throw new RuntimeException("http-get请求链接" + url + "失败！", e);
		}
	}

	public static String get(String url, List<NameValuePair> params, Map<String, String> headerMap) {
		String body = null;
		try {
			HttpGet httpget = new HttpGet();// Get请求
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params));// 设置参数
			if (headerMap != null) {
				for (Map.Entry<String, String> entry : headerMap.entrySet()) {
					httpget.addHeader(entry.getKey(), entry.getValue());
				}
			}
			httpget.setURI(new URI(url + "?" + str));
			HttpResponse httpresponse = httpClient.execute(httpget);// 发送请求
			HttpEntity entity = httpresponse.getEntity();// 获取返回数据
			body = EntityUtils.toString(entity);
			if (entity != null) {
				EntityUtils.consume(entity);
			}
			return body;
		} catch (ParseException | URISyntaxException | IOException e) {
			throw new RuntimeException("http-get请求链接" + url + "失败！", e);
		}
	}

	public static String post(String url) {
		HttpPost httppost = new HttpPost(url);
		try {
			HttpResponse httpResponse = httpClient.execute(httppost);
			return EntityUtils.toString(httpResponse.getEntity());
		} catch (ParseException | IOException e) {
			throw new RuntimeException("http-post请求链接" + url + "失败！", e);
		}
	}

	public static String post(String url, String encoding) {
		if (StringUtil.isEmpty(encoding))
			encoding = "UTF-8";
		HttpPost httppost = new HttpPost(url);
		try {
			HttpResponse httpResponse = httpClient.execute(httppost);
			return EntityUtils.toString(httpResponse.getEntity(), encoding);
		} catch (ParseException | IOException e) {
			throw new RuntimeException("http-post请求链接" + url + "失败！", e);
		}
	}

	public static String post(String url, List<NameValuePair> params, String encoding) {
		Map<String, Object> header = new HashMap<String, Object>();
		header.put("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
		HttpPost httppost = new HttpPost(url);
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, encoding));
			HttpResponse httpResponse = httpClient.execute(httppost);
			return EntityUtils.toString(httpResponse.getEntity(), encoding);
		} catch (ParseException | IOException e) {
			throw new RuntimeException("http-post请求链接" + url + "失败！", e);
		}
	}

	public static String post(String url, Map<String, String> params, String encoding) {
		List<NameValuePair> httpParams = new ArrayList<NameValuePair>();
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair nv = new BasicNameValuePair(entry.getKey(), entry.getValue());
				httpParams.add(nv);
			}
		}
		try {
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(httpParams, encoding));
			HttpResponse httpResponse = httpClient.execute(httppost);
			return EntityUtils.toString(httpResponse.getEntity(), encoding);
		} catch (ParseException | IOException e) {
			throw new RuntimeException("http-post请求链接" + url + "失败！", e);
		}
	}

	public static String post(String url, List<NameValuePair> params, String encoding, Map<String, String> header) {
		HttpPost httppost = new HttpPost(url);
		for (Map.Entry<String, String> entry : header.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params, encoding));
			HttpResponse httpResponse = httpClient.execute(httppost);
			return EntityUtils.toString(httpResponse.getEntity(), encoding);
		} catch (ParseException | IOException e) {
			throw new RuntimeException("http-post请求链接" + url + "失败！", e);
		}
	}

	public static String post(String url, String params, String encoding) {
		if (StringUtil.isEmpty(encoding))
			encoding = "UTF-8";
		HttpPost httppost = new HttpPost(url);
		StringEntity postEntity = new StringEntity(params, encoding);
		httppost.addHeader("Content-Type", "text/xml");
		httppost.setEntity(postEntity);

		try {
			HttpResponse httpResponse = httpClient.execute(httppost);
			return EntityUtils.toString(httpResponse.getEntity(), encoding);
		} catch (ParseException | IOException e) {
			throw new RuntimeException("http请求结果解析错误：ParseException | IOException," + e.getLocalizedMessage());
		}
	}
	public static String postJson(String url, String params, String encoding) {
		if (StringUtil.isEmpty(encoding))
			encoding = "UTF-8";
		HttpPost httppost = new HttpPost(url);
		StringEntity postEntity = new StringEntity(params, encoding);
		httppost.addHeader("Content-Type", "application/json");
		httppost.setEntity(postEntity);

		try {
			HttpResponse httpResponse = httpClient.execute(httppost);
			return EntityUtils.toString(httpResponse.getEntity(), encoding);
		} catch (ParseException | IOException e) {
			throw new RuntimeException("http请求结果解析错误：ParseException | IOException," + e.getLocalizedMessage());
		}
	}
	public static String post(String url, String params, String encoding, CloseableHttpClient client) {
		if (StringUtil.isEmpty(encoding))
			encoding = "UTF-8";

		HttpPost httppost = new HttpPost(url);
		StringEntity postEntity = new StringEntity(params, encoding);
		httppost.addHeader("Content-Type", "text/xml");
		httppost.setEntity(postEntity);

		try {
			HttpResponse httpResponse = client.execute(httppost);
			return EntityUtils.toString(httpResponse.getEntity(), encoding);
		} catch (ParseException | IOException e) {
			throw new RuntimeException("http请求结果解析错误：ParseException | IOException," + e.getLocalizedMessage());
		}
	}

	/**
	 * SSL证书加密HTTP请求
	 * @param certFilePath	证书路径
	 * @param pwd	证书密钥
	 * @param encoding	编码
	 * @param url	请求链接
	 * @param params	参数
	 * @return	结果
	 * @author xiaopeng.liu@decked.com.cn 2016年3月23日上午11:47:25
	 */
	public static String sslPKCS12Post(String certFilePath, String pwd, String encoding, String url, String params) {
		FileInputStream instream = null;
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");// 指定读取证书格式为PKCS12
			// 读取本机存放的PKCS12证书文件 1
			instream = new FileInputStream(new File(certFilePath));
			// 指定PKCS12的密码(商户ID) 2
			keyStore.load(instream, pwd.toCharArray());
			
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, pwd.toCharArray()).build();
			// 指定TLS版本
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			// 设置httpclient的SSLSocketFactory
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

			if (StringUtil.isEmpty(encoding))
				encoding = "UTF-8";

			HttpPost httppost = new HttpPost(url);
			StringEntity postEntity = new StringEntity(params, encoding);
			httppost.addHeader("Content-Type", "text/xml");
			httppost.setEntity(postEntity);
			
			HttpResponse httpResponse = httpclient.execute(httppost);
			return EntityUtils.toString(httpResponse.getEntity(), encoding);
		} catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | KeyManagementException | UnrecoverableKeyException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				instream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		Map<String,String> params = new HashMap<String,String>();
		params.put("path","pages/travel/index");
		
		//new一个URL对象    
        URL url = new URL("https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=8lo6x_mOUcDEnes0zZhivQ3pUL3WfC1BkbBMKZuIAol5g86VQFLYjr5fzx9MZ__y_hTyZBd2sug7cV7CghQ1JMhGiaWFHndtSkKSdefxtsKYxhqVFil4j_XUE4WqLPL9RBWcAEAUEZ");    
        //打开链接    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
        //设置请求方式为"GET"    
        conn.setRequestMethod("POST");   
        conn.setDoInput(true);
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.writeBytes(JsonUtil.entity2Json(params));

        out.flush();
        out.close(); 
		
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }
      
        reader.close();
        conn.disconnect();
        
        
	}
	
}
