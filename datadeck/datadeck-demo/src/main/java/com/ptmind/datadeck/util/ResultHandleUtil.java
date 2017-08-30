package com.ptmind.datadeck.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultHandleUtil {

	public static List<ResultNode> dealResult(List<List<Map<?,?>>> list,List<String> idKeyList,List<String> nameKeyList,List<String> pidKeyList) {
		List<List<Node>> allNodeList = new ArrayList<List<Node>>(list.size());
		for(int i=0;i<list.size();i++) {
			List<Map<?,?>> mapList = list.get(i);
			List<Node> nodeList = new ArrayList<Node>();
			for(Map<?,?> map:mapList) {
				String id = map.get(idKeyList.get(i)).toString();
				String name = map.get(nameKeyList.get(i)).toString();
				String pid = map.get(pidKeyList.get(i)).toString();
				nodeList.add(new Node(id,name,pid));
			}
			allNodeList.add(nodeList);
		}
		return dealResultNode(allNodeList);
	}
	
	public static List<ResultNode> dealResultNode(List<List<Node>> list) {
		Map<String,ResultNode> resultNodeMap = new HashMap<String,ResultNode>();
		List<ResultNode> resultNodeList = new ArrayList<ResultNode>();
		for(int i=0;i<list.size();i++) {
			if(i==0) {
				for(Node node:list.get(i)) {
					ResultNode resultNode = new ResultNode(node);
					resultNodeList.add(resultNode);
					resultNodeMap.put(resultNode.getId(), resultNode);
				}
			}
			else {
				for(Node node:list.get(i)) {
					ResultNode resultNode = new ResultNode(node);
					resultNodeMap.put(resultNode.getId(), resultNode);
					if(resultNodeMap.containsKey(node.getPid())) {
						resultNodeMap.get(node.getPid()).getChildren().add(resultNode);
					}
				}
			}
		}
		return resultNodeList;
	}
	
	public static void main(String[] args) {
		List<List<Node>> list = new ArrayList<List<Node>>();
		
		List<Node> list1 = new ArrayList<Node>();
		Node node = new Node("1","1","0");
		list1.add(node);
		node = new Node("2","2","0");
		list1.add(node);
		node = new Node("3","3","0");
		list1.add(node);
		
		List<Node> list2 = new ArrayList<Node>();
		node = new Node("11","11","1");
		list2.add(node);
		node = new Node("12","12","1");
		list2.add(node);
		node = new Node("13","13","1");
		list2.add(node);
		node = new Node("14","14","1");
		list2.add(node);
		node = new Node("15","15","1");
		list2.add(node);
		node = new Node("22","22","2");
		list2.add(node);
		node = new Node("23","23","2");
		list2.add(node);
		node = new Node("33","33","3");
		list2.add(node);
		
		List<Node> list3 = new ArrayList<Node>();
		node = new Node("111","111","11");
		list3.add(node);
		node = new Node("112","112","11");
		list3.add(node);
		node = new Node("113","113","11");
		list3.add(node);
		node = new Node("223","223","22");
		list3.add(node);
		node = new Node("333","333","33");
		list3.add(node);
		
		list.add(list1);
		list.add(list2);
		list.add(list3);
		
		ResultHandleUtil.dealResultNode(list);
	}
}

class Node{
	private String id;
	private String name;
	private String pid;
	
	public Node(String id,String name,String pid) {
		this.id = id;
		this.name = name;
		this.pid = pid;
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
}

class ResultNode{
	String id;
	String name;
	List<ResultNode> children = new ArrayList<ResultNode>();
	
	public ResultNode(Node node) {
		this.id = node.getId();
		this.name = node.getName();
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
	public List<ResultNode> getChildren() {
		return children;
	}
	public void setChildren(List<ResultNode> children) {
		this.children = children;
	}
}
