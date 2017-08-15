package com.xfsw.common.util;

/**
 * 排序工具类
 * 
 * @author xiaopeng.liu@dekced.com.cn 2016年8月16日上午2:56:08
 */
public class SortUtil {

	/**
	 * 二分法查找
	 * 
	 * @param arr
	 * @param key
	 * @return
	 * @author xiaopeng.liu@decked.com.cn 2016年8月16日上午2:55:09
	 */
	public static int search(int[] arr, int key) {
		if(arr==null) return -1;
		int low = 0;
		int high = arr.length - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (key == arr[middle]) {
				return middle;
			} else if (key < arr[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}
	
	public static int search(Integer[] arr, Integer key) {
		if(arr==null) return -1;
		int low = 0;
		int high = arr.length - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (key.intValue()== arr[middle].intValue()) {
				return middle;
			} else if (key < arr[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return -1;
	}
}
