package com.xfsw.common.util;

import java.security.MessageDigest;

/**
 * 
 * @author liuxiaopeng
 *
 */
public class MD5Util {
	/**
	 * MD5加密(高位)
	 * @param s	待加密字符串
	 * @return	加密字符串
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:13:07
	 */
	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			throw new RuntimeException("MD5加密失败！",e);
		}
	}
	
	/**
	 * MD5低位加密
	 * @param s	待加密字符串
	 * @param charset	编码
	 * @return	加密后字符串
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:13:33
	 */
	public final static String lowMd5(String s,String charset) {
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(s.getBytes(charset));
		    byte[] result = md.digest();
		    StringBuffer sb = new StringBuffer(32);
		    for (int i = 0; i < result.length; i++) {
		        int val = result[i] & 0xff;
		        if (val <= 0xf) {
		            sb.append("0");
		        }
		        sb.append(Integer.toHexString(val));
		    }
		    return sb.toString().toLowerCase();
		}
		catch(Exception e){
			throw new RuntimeException("MD5加密失败！",e);
		}
	}
	
	public static void main(String[] args){
		System.out.println(md5("123456"));
	}
}
