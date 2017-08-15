/**
 * 
 */
package com.xfsw.common.util;

/**
 * 图片处理工具
 * @author xiaopeng.liu@dekced.com.cn
 * 2016年12月14日上午11:25:41
 */
public class ImageUtil {

//	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	
//	public final static byte[] cut(InputStream inputStream,double sx,double sy,double ex,double ey,double sourceWidth,double sourceHeight,String extName){
//		BufferedImage bi = null;
//		ByteArrayOutputStream out = null;
//		ImageInputStream iis = null;
//		ImageReader reader = null;
//		try {
//			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(extName);
//			reader = it.next();  
//			// 获取图片流  
//			iis = ImageIO.createImageInputStream(inputStream);  
//			// 输入源中的图像将只按顺序读取  
//			reader.setInput(iis, true);  
//			
//			double width = reader.getWidth(0);
//			double height = reader.getHeight(0);
//			double scale = 1;
//			if(width>height){
//				scale = width / sourceWidth;
//			}
//			else{
//				scale = height / sourceHeight;
//			}
//			
//			double targetWidth  = scale * ex;
//			if(targetWidth>width){
//				targetWidth = width;
//			}
//			
//			// 描述如何对流进行解码  
//            ImageReadParam param = reader.getDefaultReadParam();  
//  
//            // 图片裁剪区域  
//            Rectangle rect = new Rectangle((int)(scale*sx), (int)(scale*sy), (int)targetWidth, (int)targetWidth);  
//  
//            // 提供一个 BufferedImage，将其用作解码像素数据的目标  
//            param.setSourceRegion(rect);  
//  
//            // 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象  
//            bi = reader.read(0, param);  
//			out = new ByteArrayOutputStream();
//			ImageIO.write(bi,extName,out);
//			return out.toByteArray();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new BusinessException(com.xfsw.common.consts.ErrorConstant.ERROR_BUSINESS_KNOWN,"图片缩放处理失败！",e);
//		}
//		finally{
//			if(out!=null){
//				try {
//					out.close();
//				} catch (IOException e) {
//					logger.error("图片裁剪处理ByteArrayOutputStream关闭失败！");
//				}
//			}
//		}
//	}
//	
//	public static void main(String[] args) throws IOException{
//		File file = new File("D:/2.png");
//		byte[] bytes = ImageUtil.cut(new FileInputStream(file), 0, 0, 300, 300, 554, 300, "png");
//		
//		FileImageOutputStream imageOutput = new FileImageOutputStream(new File("D:\\2-1.png"));
//	    imageOutput.write(bytes, 0, bytes.length);
//	    imageOutput.close();
//	}
}
