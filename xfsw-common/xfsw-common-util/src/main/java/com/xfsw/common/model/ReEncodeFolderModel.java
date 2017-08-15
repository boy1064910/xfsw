package com.xfsw.common.model;

import com.xfsw.common.util.StringUtil;

/**
 * 
 * 文件夹文件复制并且重新编码 配置项Model
 * @author xiaopeng.liu@dekced.com.cn
 * 2016年5月4日下午9:02:45
 */
public class ReEncodeFolderModel {

	private String sourceFolderPath;
	private String sourceFolderEncoding = "UTF-8";
	private String targetFolderPath;
	private String targetFolderEncoding = "UTF-8";
	private FileResourceFilterModel fileResourceFilterModel;

	public ReEncodeFolderModel(){
		
	}
	
	/**
	 * 文件夹文件复制并且重新编码 配置项Model
	 * @param sourceFolderPath	源文件夹目录路径
	 * @param sourceFolderEncoding	源文件编码格式
	 * @param targetFolderPath	目标文件夹目录路径
	 * @param targetFolderEncoding	目标文件编码格式
	 * @param fileResourceFilterModel	文件资源过滤配置Model
	 */
	public ReEncodeFolderModel(String sourceFolderPath, String sourceFolderEncoding, String targetFolderPath, String targetFolderEncoding, FileResourceFilterModel fileResourceFilterModel) {
		this.sourceFolderPath = sourceFolderPath;
		this.sourceFolderEncoding = sourceFolderEncoding;
		this.targetFolderPath = targetFolderPath;
		this.targetFolderEncoding = targetFolderEncoding;
		this.fileResourceFilterModel = fileResourceFilterModel;
	}

	public String getSourceFolderPath() {
		return sourceFolderPath;
	}

	public String getSourceFolderEncoding() {
		return sourceFolderEncoding;
	}

	public String getTargetFolderPath() {
		return targetFolderPath;
	}

	public String getTargetFolderEncoding() {
		return targetFolderEncoding;
	}

	public FileResourceFilterModel getFileResourceFilterModel() {
		return fileResourceFilterModel;
	}

	public void setSourceFolderPath(String sourceFolderPath) {
		this.sourceFolderPath = sourceFolderPath;
	}

	public void setSourceFolderEncoding(String sourceFolderEncoding) {
		this.sourceFolderEncoding = sourceFolderEncoding;
	}

	public void setTargetFolderPath(String targetFolderPath) {
		this.targetFolderPath = targetFolderPath;
	}

	public void setTargetFolderEncoding(String targetFolderEncoding) {
		this.targetFolderEncoding = targetFolderEncoding;
	}

	public void setFileResourceFilterModel(FileResourceFilterModel fileResourceFilterModel) {
		this.fileResourceFilterModel = fileResourceFilterModel;
	}
	
	public boolean check(){
		if(StringUtil.isEmpty(this.sourceFolderPath))
			throw new RuntimeException("源文件夹不能为空");
		if(StringUtil.isEmpty(this.targetFolderPath))
			throw new RuntimeException("目标文件夹不能为空");
		return true;
	}


}
