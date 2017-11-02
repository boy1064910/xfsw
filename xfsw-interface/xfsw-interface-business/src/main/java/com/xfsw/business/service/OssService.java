/**
 * 
 */
package com.xfsw.business.service;

/**
 * 阿里云oss服务接口
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public interface OssService {

	/**
	 * 从tmp文件夹中获取文件，复制到目标文件夹中
	 * @param fileName			上传文件名称（前段上传文件统一上传到tmp文件夹中）
	 * @param destFolder		相对根目录路径
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	void saveObject(String fileName, String destFolder);
	
	/**
	 * 获取http前缀信息
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	String getDefineDomain();
}
