package com.xfsw.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	
	public static boolean isEmpty(List<?> list){
		if(list==null){
			return true;
		}
		else{
			return list.size()==0;
		}
	}
	
	public static boolean isExsit(String source,List<String> values){
		boolean result=false;
		for(String str:values){
			if(str.equals(source)){
				result=true;
				break;
			}
		}
		return result;
	}
	
	public static boolean isExsit(Integer source,List<Integer> values){
		boolean result=false;
		for(Integer val:values){
			if(val.intValue()==source.intValue()){
				result=true;
				break;
			}
		}
		return result;
	}
	
	public static int isExsitIndex(Integer source,List<Integer> values){
		int index = -1;
		for(int i=0;i<values.size();i++){
			if(values.get(i).intValue()==source.intValue()){
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static List<String> str2List(String str,String split){
		String[] strs=str.split(split);
		List<String> strList=new ArrayList<String>();
		for(String s:strs){
			strList.add(s);
		}
		return strList;
	}
}
