package com.xfsw.common.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ArrayUtil {

	/**
	 * 字符串数组去重复
	 * @param arr	待去重的数组
	 * @return	去重之后的数组
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月6日下午9:25:08
	 */
	public static String[] unique(String[] arr){
		List<String> list = new LinkedList<String>();
		if(arr==null) return null;
		for(int i=0;i<arr.length;i++){
			if(!list.contains(arr[i])){
				list.add(arr[i]);
			}
		}
		return list.toArray(new String[list.size()]);
	}
	
	public static Integer[] uniqueNumberArray(List<Integer> list){
		Map<Integer,Integer> map = new TreeMap<Integer,Integer>();
		if(list==null) return null;
		for(int i=0;i<list.size();i++){
			if(!map.containsKey(list.get(i))){
				map.put(list.get(i),list.get(i));
			}
		}
		return map.keySet().toArray(new Integer[map.size()]);
	}
	
	public static Integer[] uniqueNumberArray(Integer[] arr){
		Map<Integer,Integer> map = new TreeMap<Integer,Integer>();
		if(arr==null) return null;
		for(int i=0;i<arr.length;i++){
			if(!map.containsKey(arr[i])){
				map.put(arr[i],arr[i]);
			}
		}
		return map.keySet().toArray(new Integer[map.size()]);
	}
	
	public static Integer[] uniqueNumberArray(Integer[]... arrays){
		Integer[] uniqueArray = combineArray(arrays);
		Map<Integer,Integer> map = new TreeMap<Integer,Integer>();
		if(uniqueArray==null) return null;
		for(int i=0;i<uniqueArray.length;i++){
			if(!map.containsKey(uniqueArray[i])){
				map.put(uniqueArray[i],uniqueArray[i]);
			}
		}
		return map.keySet().toArray(new Integer[map.size()]);
	}
	
	public static Integer[] combineArray(Integer[]... arrays){
		int length = 0;
		if(arrays!=null){
			for(int i=0;i<arrays.length;i++){
				if(arrays[i]!=null)
					length+=arrays[i].length;
			}
		}
		Integer[] c = new Integer[length];
		int m=0;
		if(arrays!=null){
			for(int i=0;i<arrays.length;i++){
				if(arrays[i]!=null){
					for(int j=0;j<arrays[i].length;j++){
						c[m] = arrays[i][j];
						m++;
					}
				}
			}
		}
		return c;
	}
	
	/**
	 * 判断数组是否为空
	 * @param arr	数组
	 * @return	数组是否为空
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年7月26日上午12:25:04
	 */
	public static boolean isEmpty(Object[] arr){
		if(arr==null)
			return true;
		else
			return arr.length==0;
	}
	
	public static boolean isEmpty(int[] arr){
		if(arr==null)
			return true;
		else
			return arr.length==0;
	}
	
	public static boolean isExsit(Integer source,Integer[] values){
		boolean result=false;
		for(Integer val:values){
			if(val.intValue()==source.intValue()){
				result=true;
				break;
			}
		}
		return result;
	}
	
	public static boolean isExsit(String source,String[] values){
		boolean result=false;
		for(String val:values){
			if(val.equals(source)){
				result=true;
				break;
			}
		}
		return result;
	}
}
