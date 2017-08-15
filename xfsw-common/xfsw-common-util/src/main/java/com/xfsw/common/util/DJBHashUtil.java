package com.xfsw.common.util;

public class DJBHashUtil {

	public static Integer DJBHashId(String str) {
		int hash = 5381;
		int i = 0;
		while (i < str.length()) {
			hash += (hash << 5) + str.charAt(i);
			i++;
		}
		return (hash & 0x7FFFFFFF);
	}
	
	public static void main(String[] args){
		System.out.println(DJBHashId("/manager/account/category/authority/insertAuthority.shtml"));
	}
}
