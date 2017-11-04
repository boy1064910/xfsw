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
	 * @param fileName			上传文件名称（前端上传文件统一上传到tmp文件夹中）
	 * @param destFolder		相对根目录路径（such as：data/）
	 * @return					上传后的文件URL
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	String saveObject(String fileName, String destFolder);
	
	/**
	 * 批量从tmp中复制到目标文件夹中
	 * @param fileNames			上传文件名称数组（前端上传文件统一上传到tmp文件夹中）
	 * @param destFolder		相对根目录路径（such as：data/）
	 * @return					上传后的文件URL（顺序按照fileNames数组的入参顺序）
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	String[] saveObject(String[] fileNames,String destFolder);
	
	/**
	 * 获取http前缀信息
	 * @return
	 * @author xiaopeng.liu
	 * @version 0.0.1
	 */
	String getDefineDomain();
}
