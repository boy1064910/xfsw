package com.xfsw.common.model;

import java.util.Date;
import java.util.List;

public class FileModel {

	private String name;
	private String path;
	private boolean isFolder;
	private long size;
	private Date lastModifyDate;
	private String md5;
	private List<FileModel> children;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isFolder() {
		return isFolder;
	}
	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public List<FileModel> getChildren() {
		return children;
	}
	public void setChildren(List<FileModel> children) {
		this.children = children;
	}
}
