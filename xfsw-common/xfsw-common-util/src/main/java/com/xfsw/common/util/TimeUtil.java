package com.xfsw.common.util;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 时间处理类
 * @author liuxp
 */
public class TimeUtil {

	private static Logger logger = LoggerFactory.getLogger(TimeUtil.class);
	
	/**
	 * 获取纳秒
	 * @return	纳秒
	 */
	public static long getNanoSeconds(){
		return System.nanoTime();
	}
	
	public static long getTodayStartTime(){
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime().getTime();
	}
	
	public static long getTodayEndTime(){
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime().getTime();
	}
	
	/**
	 * 日志输出耗时时间
	 * @param startTime	开始时间
	 */
	public static void loggerLostTime(long startTime){
		long endTime = System.currentTimeMillis(); // 排序后取得当前时间 
		Calendar c = Calendar.getInstance();  
        c.setTimeInMillis(endTime - startTime);  
		logger.info("耗时: " + c.get(Calendar.MINUTE) + "分 " + c.get(Calendar.SECOND) + "秒 " + c.get(Calendar.MILLISECOND) + " 毫秒");
	}
}
