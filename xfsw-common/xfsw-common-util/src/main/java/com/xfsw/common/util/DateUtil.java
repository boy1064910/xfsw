package com.xfsw.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String date2Str(Date date) {
		if (date != null)
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		else
			throw new RuntimeException("日期格式转换错误：日期不能为null！");
	}
	
	public static String date2Str(Date date, String pattern) {
		if (date != null)
			return new SimpleDateFormat(pattern).format(date);
		else
			throw new RuntimeException("日期格式转换错误：日期不能为null！");
	}

	public static String currentDate2Str(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}

	public static String date2Str(Timestamp timestamp, String pattern) {
		if (timestamp != null)
			return new SimpleDateFormat(pattern).format(timestamp);
		else
			throw new RuntimeException("日期格式转换错误：日期不能为null！");
	}

	public static Date long2Date(Long time, String pattern) {
		return new Date(time * 1000);
	}

	public static Date str2Date(String str, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("日期：" + str + "转换格式" + pattern + "失败!", e);
		}
	}
}
