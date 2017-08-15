/**
 * 
 */
package com.xfsw.common.classes;

import java.io.Serializable;

/**
 * 页面表格分页参数
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class DataTablePageInfo implements Serializable {

	private static final long serialVersionUID = 4094314537457961130L;
	
	public DataTablePageInfo() { }
	
	public DataTablePageInfo(int currentIndex, int pageSize) {
		this.currentIndex = currentIndex;
		this.pageSize = pageSize;
	}
	
	private int currentIndex = 1;
	private int pageSize = 20;
	public int getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
