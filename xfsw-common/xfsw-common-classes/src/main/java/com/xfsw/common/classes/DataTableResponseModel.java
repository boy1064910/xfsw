package com.xfsw.common.classes;

import java.io.Serializable;

/**
 * 通信数据包返回结构体最外层公共数据结构（针对bootstrap组件datatable,若是其他table组件,建议兼容此格式）
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class DataTableResponseModel implements Serializable {

	private static final long serialVersionUID = -7802086418767231451L;
	private int code = 200;//返回值
	private String msg;//说明或者错误信息描述
	private Object rows;//数据
	private int total;//数据总数
	
	public DataTableResponseModel(){}
	
	public DataTableResponseModel(Object rows,int total) {
		this.rows = rows;
		this.total = total;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
