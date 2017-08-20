package com.xfsw.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xfsw.common.model.FileModel;
import com.xfsw.common.model.FileResourceFilterModel;
import com.xfsw.common.model.ReEncodeFolderModel;

public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	/** 文件分隔符，统一windows和linux */
	public final static String SEPERATOR = "/";

	/**
	 * 创建文件路径上级目录
	 * @param path	创建文件夹路径
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:03:13
	 */
	public static void createParentFolder(String path) {
		File file = new File(path);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		else
			logger.info(path + "的上级目录已经存在，无须创建！");
	}

	/**
	 * 创建文件 1.判断父目录是否存在，不存在则mkdirs 2.创建文件，判断文件是否存在
	 * @param path	创建文件路径
	 * @param isFocus	是否强制新建文件
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:03:28
	 */
	public static void createFile(String path, boolean isFocus) {
		File file = new File(path);
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		if (file.exists() && isFocus)
			file.delete();
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException("系统创建目标文件:" + file.getPath() + "失败！", e);
		}
	}

	/**
	 * 创建文件夹
	 * @param path	文件夹路径
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:03:55
	 */
	public static void createFolder(String path) {
		File file = new File(path);
		if (file.exists()) {
			logger.info("目标文件夹：" + file.getPath() + "已存在！");
			return;
		}
		if (!file.mkdirs()) {
			throw new RuntimeException("系统创建目标文件夹:" + file.getPath() + "失败！");
		}
	}

	/**
	 * 创建文件
	 * @param path	文件路径
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:04:11
	 */
	public static void createFile(String path) {
		File file = new File(path);
		file.getParentFile().mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException("系统创建文件失败:" + path, e);
		}
	}

	/**
	 * 删除整个文件下的所有文件夹和文件
	 * @param delpath	删除的文件夹路径
	 * @return	true成功或者false失败
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:04:22
	 */
	public static boolean deleteFolder(String delpath) {
		File file = new File(delpath);
		if (!file.isDirectory()) {// 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
			file.delete();
		} else if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File delfile = new File(delpath + File.separator + filelist[i]);
				if (!delfile.isDirectory()) {
					delfile.delete();
					logger.debug(delfile.getAbsolutePath() + "删除文件成功");
				} else if (delfile.isDirectory()) {
					deleteFolder(delpath + File.separator + filelist[i]);
				}
			}
			logger.debug(file.getAbsolutePath() + "删除成功");
			file.delete();
		}
		return true;
	}

	/**
	 * 删除单个文件
	 * @param path	文件路径
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:04:54
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			if (!file.delete()) {
				throw new RuntimeException("文件:" + file.getName() + "删除失败，请检查是否文件被占用！");
			}
		} else {
			logger.info("文件:" + file.getName() + "文件路径不存在！");
		}
	}

	/**
	 * 往文件当中写入数据
	 * @param content	内容
	 * @param filePath	文件路径
	 * @param encode	编码，为空默认为编码格式UTF-8
	 * @param isOverride	是否覆盖文件
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:05:06
	 */
	public static void writeFile(String content, String filePath, String encode, boolean isOverride) {
		if (StringUtil.isEmpty(encode))// 为空默认为编码格式UTF-8
			encode = "UTF-8";
		File file = new File(filePath);
		try {
			if (file.exists()) {
				if (isOverride) {
					if (file.delete())
						if (file.createNewFile())
							writeFile(content, file, encode, filePath);
						else
							throw new RuntimeException(filePath + "覆盖出错--【文件删除失败】！");
				} else
					logger.info(filePath + "文件已存在，不需要对原文件进行覆盖操作！");
			} else {
				file.getParentFile().mkdirs();
				if (file.createNewFile())
					writeFile(content, file, encode, filePath);
			}
		} catch (IOException e) {
			throw new RuntimeException(filePath + "创建新文件出错!", e);
		}
	}

	private static void writeFile(String content, File file, String encode, String filePath) {
		try (FileOutputStream o = new FileOutputStream(file);) {
			o.write(content.getBytes(encode));
		} catch (IOException e) {
			throw new RuntimeException("写文件数据:" + filePath + "出错!", e);
		}
	}

	public static void writeFile(String filePath, String appendContent) throws Exception {
		// FileWriter有两个参数的构造文件,而第二个参数就是是否追加文本还是重写文本
		Writer fw = new FileWriter(filePath, true);
		fw.write(appendContent);
		fw.close();
	}

	public static void writeFile(InputStream inputStream, String filePath) {
		try (OutputStream os = new FileOutputStream(new File(filePath))) {
			int bytesRead = 0;
			byte[] buffer = new byte[2048];
			while ((bytesRead = inputStream.read(buffer, 0, 2048)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			throw new RuntimeException("写文件数据:" + filePath + "出错!", e);
		}
	}

	public static String readFile(String filePath, String encoding, boolean returnLine) {
		String returnStr = "";
		String lineTxt = "";
		if (StringUtil.isEmpty(encoding)) {// 为空默认为编码格式UTF-8
			encoding = "UTF-8";
		}
		try {
			File file = new File(filePath);
			if (file.exists() && file.isFile()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				while ((lineTxt = bufferedReader.readLine()) != null) {
					returnStr += lineTxt;
					if (returnLine) {
						returnStr += "\r\n";
					}
				}
				read.close();
			} else {
				throw new RuntimeException("读取文件:" + filePath + "不存在!");
			}
		} catch (Exception e) {
			throw new RuntimeException("读取文件:" + filePath + "内容出错 !");
		}
		return returnStr;
	}

	public static void copyFileAndMkdirs(String sourceFilePath, String targetFilePath) {
		File file = new File(targetFilePath);
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				throw new RuntimeException("文件：" + targetFilePath + "目录结构创建失败!");
			}
		}
		copyFile(sourceFilePath, targetFilePath);
	}

	public static void copyFileAndMkdirs(File sourceFile, String targetFilePath) {
		File file = new File(targetFilePath);
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				throw new RuntimeException("文件：" + targetFilePath + "目录结构创建失败!");
			}
		}
		copyFile(sourceFile, targetFilePath);
	}

	public static void copyFile(String sourceFilePath, String targetFilePath) {
		// 新建文件输入流并对它进行缓冲
		try (BufferedInputStream inBuff = new BufferedInputStream(new FileInputStream(sourceFilePath)); BufferedOutputStream outBuff = new BufferedOutputStream(new FileOutputStream(targetFilePath))) {
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} catch (IOException e) {
			throw new RuntimeException("文件：" + sourceFilePath + "copy到" + targetFilePath + "失败!" + e.getMessage(), e);
		}
	}

	public static void copyFile(File sourceFile, String targetFilePath) {
		try (BufferedInputStream inBuff = new BufferedInputStream(new FileInputStream(sourceFile)); BufferedOutputStream outBuff = new BufferedOutputStream(new FileOutputStream(targetFilePath))) {
			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}catch (IOException e) {
			throw new RuntimeException("文件：" + sourceFile.getAbsolutePath() + "copy到" + targetFilePath + "失败!" + e.getMessage(), e);
		}
	}

	/**
	 * 复制整个文件夹内容(递归移动)
	 * @param oldPath	原文件路径 如：c:/fqf
	 * @param newPath	复制后路径 如：f:/fqf/ff
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:05:48
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + File.separator + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + File.separator + file[i], newPath + File.separator + file[i]);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("复制整个文件夹from " + oldPath + " to " + newPath + "内容操作出错!");
		}
	}

	public static void rename(String oldFilePath, String newFilePath) {
		if (!oldFilePath.equals(newFilePath)) {// 新的文件名和以前文件名不同时,才有必要进行重命名
			File oldfile = new File(oldFilePath);
			File newfile = new File(newFilePath);
			if (!oldfile.exists()) {
				throw new RuntimeException("旧文件:" + oldFilePath + "不存在!");
			}
			if (newfile.exists())// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
				throw new RuntimeException("新文件:" + newfile + "已经不存在!");
			else {
				oldfile.renameTo(newfile);
			}
		} else {
			throw new RuntimeException("新文件:" + newFilePath + "和旧文件:" + oldFilePath + "相同!");
		}
	}

	//递归获取文件夹中的所有文件和文件夹
	public static List<FileModel> listTreeFile(String folderPath, String[] fileFilter, String[] folderFilter) throws NoSuchAlgorithmException, IOException {
		List<FileModel> fileModelList = new ArrayList<FileModel>();
		File file = new File(folderPath);
		String[] filelist = file.list();
		Pattern pattern = null;
		for (int i = 0; i < filelist.length; i++) {
			FileModel fileModel = new FileModel();
			File readfile = new File(folderPath + File.separator + filelist[i]);
			if (!readfile.isDirectory()) {
				boolean filterResult = true;
				if (fileFilter != null) {
					for (int j = 0; j < fileFilter.length; j++) {
						pattern = Pattern.compile(fileFilter[j]);
						Matcher matcher = pattern.matcher(readfile.getName());
						if (!matcher.matches()) {
							filterResult = false;
						}
					}
				}
				if (filterResult) {
					fileModel.setFolder(false);
					fileModel.setLastModifyDate(DateUtil.long2Date(readfile.lastModified(), "yyyy-MM-dd HH:mm:ss"));
					fileModel.setName(readfile.getName());
					fileModel.setPath(readfile.getAbsolutePath());
					fileModel.setMd5(getFileMD5(readfile));
				}
			} else {
				boolean filterResult = true;
				if (folderFilter != null) {
					for (int j = 0; j < folderFilter.length; j++) {
						pattern = Pattern.compile(folderFilter[j]);
						Matcher matcher = pattern.matcher(readfile.getName());
						if (!matcher.matches()) {
							filterResult = false;
						}
					}
				}
				if (filterResult) {
					fileModel.setFolder(true);
					fileModel.setLastModifyDate(DateUtil.long2Date(readfile.lastModified(), "yyyy-MM-dd HH:mm:ss"));
					fileModel.setName(readfile.getName());
					fileModel.setPath(readfile.getAbsolutePath());
					fileModel.setMd5(getFileMD5(readfile));
					fileModel.setChildren(listTreeFile(folderPath + File.separator + filelist[i], fileFilter, folderFilter));
				}
			}
			fileModelList.add(fileModel);
		}
		return fileModelList;
	}

	/**
	 * 【递归返回文件夹中的所有文件路径】
	 * 1.正则表达式匹配文件夹名称
	 * 2.调用【循环文件夹子文件】
	 * @param folderPath	文件夹路径
	 * @param folderIncludeRegex	包含的文件夹名称正则表达式
	 * @param folderExcludeRegex	排除的文件夹名称正则表达式
	 * @param fileIncludeRegex	包含的文件名称正则表达式
	 * @param fileExcludeRegex	排除的文件名称正则表达式
	 * @return	返回文件夹中的所有文件路径
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:06:27
	 */
	public static List<String> listFiles(String folderPath, String folderIncludeRegex, String folderExcludeRegex, String fileIncludeRegex, String fileExcludeRegex) {
		List<String> filePathList = new ArrayList<String>();
		if (!StringUtil.isEmpty(folderPath)) {
			if (RegexUtil.match(folderPath, folderIncludeRegex) && (StringUtil.isEmpty(folderExcludeRegex) ? true : !RegexUtil.match(folderPath, folderExcludeRegex)))
				filePathList = listCircleChildren(folderPath, folderIncludeRegex, folderExcludeRegex, fileIncludeRegex, fileExcludeRegex, filePathList);
			else
				return filePathList;
		}
		return filePathList;
	}

	public static List<String> listFiles(String folderPath, FileResourceFilterModel fileResourceFilterModel) {
		return listFiles(folderPath, fileResourceFilterModel.getFolderIncludeRegex(), fileResourceFilterModel.getFolderExcludeRegex(), fileResourceFilterModel.getFileIncludeRegex(), fileResourceFilterModel.getFileExcludeRegex());
	}

	/**
	 * <pre>
	 * 【循环文件夹子文件】
	 * 1.列出文件夹中的所有文件，采用文件夹名称正则表达式判断是否属于目录级别，目录级别调用【递归返回文件夹中的所有文件路径】
	 * 2.非目录级别的文件，采用文件名称正则表达式匹配，符合则加入list返回
	 * </pre>
	 * 
	 * @param folderPath
	 *            文件夹路径
	 * @param folderIncludeRegex
	 *            包含的文件夹名称正则表达式
	 * @param folderExcludeRegex
	 *            排除的文件夹名称正则表达式
	 * @param fileIncludeRegex
	 *            包含的文件名称正则表达式
	 * @param fileExcludeRegex
	 *            排除的文件名称正则表达式
	 * @param filePathList
	 * @return
	 */
	private static List<String> listCircleChildren(String folderPath, String folderIncludeRegex, String folderExcludeRegex, String fileIncludeRegex, String fileExcludeRegex, List<String> filePathList) {
		File[] files = new File(folderPath).listFiles();
		if (files != null)
			for (File file : files) {
				if (file.isDirectory()) {
					if (RegexUtil.match(file.getAbsolutePath(), folderIncludeRegex) && (StringUtil.isEmpty(folderExcludeRegex) ? true : !RegexUtil.match(file.getAbsolutePath(), folderExcludeRegex)))
						filePathList = listCircleChildren(file.getAbsolutePath(), folderIncludeRegex, folderExcludeRegex, fileIncludeRegex, fileExcludeRegex, filePathList);
				} else {
					if (RegexUtil.match(file.getName(), fileIncludeRegex) && (StringUtil.isEmpty(fileExcludeRegex) ? true : !RegexUtil.match(file.getName(), fileExcludeRegex)))
						filePathList.add(file.getAbsolutePath());
				}
			}
		return filePathList;
	}

	public static String getFileMD5(File file) throws NoSuchAlgorithmException, IOException {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		digest = MessageDigest.getInstance("MD5");
		in = new FileInputStream(file);
		while ((len = in.read(buffer, 0, 1024)) != -1) {
			digest.update(buffer, 0, len);
		}
		in.close();
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

	public static boolean checkExsitRepeatMD5(List<FileModel> modelList) {
		boolean result = false;
		Map<String, String> fileMap = new HashMap<String, String>();
		if (modelList != null) {
			for (FileModel m : modelList) {
				if (fileMap.containsKey(m.getMd5())) {
					result = true;
					throw new RuntimeException("文件：" + fileMap.get(m.getMd5()) + "与文件：" + m.getName() + " md5值重复，请检查文件信息！");
				} else {
					fileMap.put(m.getMd5(), m.getName());
				}
			}
		}
		return result;
	}

	/**
	 * 统一文件路径写法，在windows环境下将File.seperator统一替换成"/"
	 * @param filePath	文件路径
	 * @return	返回替换后的值
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:07:08
	 */
	public static String uniteFilePath(String filePath) {
		return filePath.replaceAll("\\\\", "/");
	}
	
	/**
	 * 获取文件名，不含后缀
	 * @param fileName	文件名
	 * @return	文件名
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年11月2日下午6:01:23
	 */
	public static String getPartFileName(String fileName) {
		int dotIndex = fileName.lastIndexOf(".");
		if (dotIndex != 0) {
			return fileName.substring(0,dotIndex);
		} else {
			return null;
		}
	}
	
	/**
	 * 从Url中获取文件名称
	 * @param url
	 * @return
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年12月13日下午1:14:19
	 */
	public static String getFileName(String url){
		int lastSprit = url.lastIndexOf("/")+1;
		int dotIndex = url.lastIndexOf(".");
		return url.substring(lastSprit,dotIndex);
	}
	
	public static String getFileSubfixName(String url) {
		int dotIndex = url.lastIndexOf(".");
		if (dotIndex != 0) {
			return url.substring(dotIndex);
		} else {
			return null;
		}
	}

	/**
	 * 复制文件并且重新编码
	 * @param reEncodeFolderModel	文件夹文件复制并且重新编码 配置项Model,具体内容参考 {@link cn.com.decked.common.model.ReEncodeFolderModel}
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月4日下午10:07:32
	 */
	public static void reEncodeFolder(ReEncodeFolderModel reEncodeFolderModel) {
		if (reEncodeFolderModel.check()) {// 配置项内容检查
			List<String> filePath = listFiles(reEncodeFolderModel.getSourceFolderPath(), reEncodeFolderModel.getFileResourceFilterModel());
			for (String path : filePath) {
				String content = readFile(path, reEncodeFolderModel.getSourceFolderEncoding(), true);
				path = uniteFilePath(path).replace(uniteFilePath(reEncodeFolderModel.getSourceFolderPath()), uniteFilePath(reEncodeFolderModel.getTargetFolderPath()));
				writeFile(content, path, reEncodeFolderModel.getTargetFolderEncoding(), true);
				logger.debug(path + " 转化写入完毕!");
			}
		}
	}
	
	/**
	 * 读取到字节数组2
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年5月16日下午6:37:41
	 */
	public static byte[] toByteArray2(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean exsitFile(String path){
		return new File(path).exists();
	}
	
	/**
	 * 根据文件路径读取文件流信息
	 * @param filePath
	 * @return
	 * @author xiaopeng.liu@decked.com.cn
	 * 2016年7月22日下午2:24:19
	 */
	public static InputStream file2Stream(String filePath){
		File file = new File(filePath);
		if(file!=null&&file.exists()){
			try {
				return new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new RuntimeException("读取文件流失败！");
			}
		}
		else{
			throw new RuntimeException("读取文件失败，文件不存在！");
		}
	}
	
}
