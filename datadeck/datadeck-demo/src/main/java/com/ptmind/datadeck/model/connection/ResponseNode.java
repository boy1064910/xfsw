package com.ptmind.datadeck.model.connection;

import java.util.ArrayList;
import java.util.List;

/**
 * 步骤处理数据之后返回的数据结构
 * 
 * @author xiaopeng.liu
 * @version 3.0.0
 */
public class ResponseNode {

	String id;
	String name;
	List<ResponseNode> children = new ArrayList<ResponseNode>();

	public ResponseNode(String id,String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResponseNode> getChildren() {
		return children;
	}

	public void setChildren(List<ResponseNode> children) {
		this.children = children;
	}
}
