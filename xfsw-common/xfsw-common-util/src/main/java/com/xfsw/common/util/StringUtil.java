package com.xfsw.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isEmpty(Object obj) {
		return obj == null || obj.toString().length() == 0;
	}

	public static String getRandomJianHan(int len) {
		String ret = "";
		for (int i = 0; i < len; i++) {
			String str = null;
			int hightPos, lowPos; // 定义高低位
			Random random = new Random();
			hightPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
			lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
			byte[] b = new byte[2];
			b[0] = (new Integer(hightPos).byteValue());
			b[1] = (new Integer(lowPos).byteValue());
			try {
				str = new String(b, "GBk"); // 转成中文
			} catch (UnsupportedEncodingException ex) {
				throw new RuntimeException("生成随机汉字失败！",ex);
			}
			ret += str;
		}
		return ret;
	}

	public static String getRandomString(int length) {
		String val = "";

		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

			if ("char".equalsIgnoreCase(charOrNum)) // 字符串
			{
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) // 数字
			{
				val += String.valueOf(random.nextInt(10));
			}
		}

		return val;
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 *            字符串
	 * @return 首字母大写的字符串
	 */
	public static String initialFirstUppercase(String str) {
		if (!isEmpty(str) && str.length() > 1)
			return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
		else
			throw new RuntimeException("首字母大写失败，传入的字符串参数为空或者长度小于1，字符串内容：" + str);
	}

	/**
	 * 首字母小写
	 * 
	 * @param str
	 *            字符串
	 * @return 首字母大写的字符串
	 */
	public static String initialFirstLowercase(String str) {
		if (!isEmpty(str) && str.length() > 1)
			return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
		else
			throw new RuntimeException("首字母大写失败，传入的字符串参数为空或者长度小于1，字符串内容：" + str);
	}

	public static Integer[] splitIntoIntArray(String str, String split) {
		String[] strs = str.split(split);
		Integer[] intArray = new Integer[strs.length];
		for (int i = 0; i < strs.length; i++) {
			if (!isEmpty(strs[i]))
				intArray[i] = Integer.valueOf(strs[i]);
		}
		return intArray;
	}

	/**
	 * 数字自动补全变成编号
	 * 
	 * @param size
	 *            编号长度
	 * @param num
	 *            数字
	 * @return 补全后的编号
	 * @author xiaopeng.liu@decked.com.cn 2016年5月6日下午11:56:35
	 */
	public static String generateNumber(int size, int num) {
		int leaveLength = size - String.valueOf(num).length();
		String result = String.valueOf(num);
		if (leaveLength > 0) {
			for (int i = 0; i < leaveLength; i++) {
				result = "0" + result;
			}
		}

		return result;
	}

	public static int toInt(String str) {

		if (str.equals("") || str == null) {
			return 0;
		} else {
			return Integer.parseInt(str);
		}
	}

	public static Double toDouble(String str) {

		if (str.equals("") || str == null) {
			return 0.0;
		} else {
			return Double.parseDouble(str);
		}
	}

	public static boolean isAlpha(char c) {
		int i = (int) c;
		if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 模板中的变量按照字符串数组下标替换
	 * @param template	模板内容
	 * @param strArray	待替换的字符串数组
	 * @return
	 */
	public static String variableReplace(String template,String... strArray){
		// 用参数替换模板中的${}变量
		Matcher m = Pattern.compile("\\$\\{\\w+\\}").matcher(template);
		int matchCount = 0;
		while(m.find()){
			matchCount++;
		}
		
		if(matchCount!=0 && matchCount > strArray.length){
			throw new RuntimeException("字符串变量匹配数量不一致，模板："+template+",待替换数据："+JsonUtil.entity2Json(strArray));
		}
		
		if(matchCount==0){
			return template;
		}
		else{
			StringBuffer sb = new StringBuffer();
			m.reset();
			int index = 0;
			
			while (m.find()) {
				m.appendReplacement(sb, strArray[index] == null ? "null" : strArray[index]);
				index++;
			}
			return sb.toString();
		}
	}
	
	public static String variableReplace(String template,String replacer){
		// 用参数替换模板中的${}变量
		Matcher m = Pattern.compile("\\$\\{\\w+\\}").matcher(template);
		return m.replaceAll(replacer);
	}

}
