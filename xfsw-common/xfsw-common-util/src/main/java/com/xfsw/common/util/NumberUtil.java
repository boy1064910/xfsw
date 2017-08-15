package com.xfsw.common.util;

public class NumberUtil {

	/**
	 * 生成带0编号
	 * @param num	编号数字
	 * @param codeLength	生成的编号长度
	 * @return	编号
	 */
	public static String toZeroCode(int num,int codeLength){
		int count = 0;
		int tmpNum = num;
		while(tmpNum>0){
			count++;
			tmpNum = tmpNum/10;
		}
		int leftCount = codeLength-count;
		String code = String.valueOf(num);
		for(int i=0;i<leftCount;i++){
			code = "0" + code;
		}
		return code;
	}
	
	public static String ToCH(int intInput) {
		String si = String.valueOf(intInput);
		String sd = "";
		if (si.length() == 1) // 個
		{
			sd += GetCH(intInput);
			return sd;
		} else if (si.length() == 2)// 十
		{
			if (si.substring(0, 1).equals("1"))
				sd += "十";
			else
				sd += (GetCH(intInput / 10) + "十");
			sd += ToCH(intInput % 10);
		} else if (si.length() == 3)// 百
		{
			sd += (GetCH(intInput / 100) + "百");
			if (String.valueOf(intInput % 100).length() < 2)
				sd += "零";
			sd += ToCH(intInput % 100);
		} else if (si.length() == 4)// 千
		{
			sd += (GetCH(intInput / 1000) + "千");
			if (String.valueOf(intInput % 1000).length() < 3)
				sd += "零";
			sd += ToCH(intInput % 1000);
		} else if (si.length() == 5)// 萬
		{
			sd += (GetCH(intInput / 10000) + "萬");
			if (String.valueOf(intInput % 10000).length() < 4)
				sd += "零";
			sd += ToCH(intInput % 10000);
		}

		return sd;
	}

	private static String GetCH(int input) {
		String sd = "";
		switch (input) {
		case 1:
			sd = "一";
			break;
		case 2:
			sd = "二";
			break;
		case 3:
			sd = "三";
			break;
		case 4:
			sd = "四";
			break;
		case 5:
			sd = "五";
			break;
		case 6:
			sd = "六";
			break;
		case 7:
			sd = "七";
			break;
		case 8:
			sd = "八";
			break;
		case 9:
			sd = "九";
			break;
		default:
			break;
		}
		return sd;
	}
}
