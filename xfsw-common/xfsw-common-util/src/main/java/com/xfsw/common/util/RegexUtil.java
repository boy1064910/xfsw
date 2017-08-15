package com.xfsw.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public static boolean match(String str, String regex) {
		if (StringUtil.isEmpty(regex)) {
			return true;
		} else {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			return matcher.find();
		}
	}
}
