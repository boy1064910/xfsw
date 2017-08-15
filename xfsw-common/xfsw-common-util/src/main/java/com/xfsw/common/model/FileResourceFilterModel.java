package com.xfsw.common.model;

/**
 * 文件资源过滤配置Model
 * @author 晓鹏
 */
public class FileResourceFilterModel {

	private String folderIncludeRegex;
	private String folderExcludeRegex;
	private String fileIncludeRegex;
	private String fileExcludeRegex;
	
	/**
	 * 文件资源过滤配置Model
	 * @param folderIncludeRegex	包含的文件夹名称-正则表达式	
	 * @param folderExcludeRegex	排除的文件夹名称-正则表达式	"(\\.settings)|(target)|(\\.svn$)"
	 * @param fileIncludeRegex		包含的文件名称-正则表达式
	 * @param fileExcludeRegex		排除的文件名称-正则表达式	"(\\.zip)|(\\.swf)|(\\.jpg)|(\\.gif)|(\\.png)|(\\.jpeg)|(\\.project$)|(\\.classpath$)|(\\.class$)|(\\.svn$)"
	 */
	public FileResourceFilterModel(String folderIncludeRegex,String folderExcludeRegex,String fileIncludeRegex,String fileExcludeRegex){
		this.folderIncludeRegex = folderIncludeRegex;
		this.folderExcludeRegex = folderExcludeRegex;
		this.fileIncludeRegex = fileIncludeRegex;
		this.fileExcludeRegex = fileExcludeRegex;
	}
	
	public String getFolderIncludeRegex() {
		return folderIncludeRegex;
	}
	public String getFolderExcludeRegex() {
		return folderExcludeRegex;
	}
	public String getFileIncludeRegex() {
		return fileIncludeRegex;
	}
	public String getFileExcludeRegex() {
		return fileExcludeRegex;
	}
}
