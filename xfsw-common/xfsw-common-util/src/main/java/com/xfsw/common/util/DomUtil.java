package com.xfsw.common.util;

public class DomUtil {
	
//	public static Element createElement(String elementName,String elementValue,String[] attributeNames,String[] attributeValues){
//		Element element=DocumentHelper.createElement(elementName);
//		if(!StringUtil.isEmpty(elementValue))
//			element.setText(elementValue);
//		if(attributeNames!=null){
//			for(int i=0;i<attributeNames.length;i++){
//				element.addAttribute(attributeNames[i],attributeValues[i]);
//			}
//		}
//		return element;
//	}
//	
//	public static Document string2Document(String content){
//		// 替代空格，用于xml解析
//		Document doc = null;
//		try {
//			doc = DocumentHelper.parseText(content);
//		} catch (DocumentException e) {
//			throw new RuntimeException("Dom结构文档解析失败！",e);
//		}
//		return doc;
//	}
//	
//	public static Document filePath2Document(String filePath){
//		Document doc = null;
//		try {
//			doc = DocumentHelper.parseText(FileUtil.readFile(filePath, "UTF-8", true).replaceAll("&", "&amp;"));
//		} catch (DocumentException e) {
//			throw new RuntimeException("Dom结构文档解析失败！",e);
//		}
//		return doc;
//	}
//	
//	public static Document filePath2Document(String filePath,String encoding){
//		Document doc = null;
//		try {
//			doc = DocumentHelper.parseText(FileUtil.readFile(filePath, encoding, true));
//		} catch (DocumentException e) {
//			throw new RuntimeException("Dom结构文档解析失败！",e);
//		}
//		return doc;
//	}
//	
//	public static Element string2Element(String content){
//		return string2Document(content).getRootElement();
//	}
//	
//	public static Element file2Element(String filePath, String encoding, boolean returnLine){
//		Document doc = null;
//		try {
//			doc = DocumentHelper.parseText(FileUtil.readFile(filePath, encoding, returnLine));
//		} catch (Exception ex) {
//			throw new RuntimeException("文本转换成dom结构失败,请检查是否存在未转义&之类的关键字！",ex);
//		}
//		return doc.getRootElement();
//	}
//	
//	/**
//	 * 通过结点名称查询结点内容
//	 * @param filePath	文件路径
//	 * @param fileName	文件名称
//	 * @param elementStr	节点字符串
//	 * @param encoding	编码
//	 * @return	XML文件内容
//	 * @throws DocumentException 	dom结构转换异常
//	 */
//	public static String readSingleElementValue(String filePath,String fileName,String elementStr,String encoding) throws DocumentException{
//		String value=null;
//		Document doc = null;
//		String content=FileUtil.readFile(filePath, encoding, true);
//		doc = string2Document(content);
//		List<?> list=doc.selectNodes("//"+elementStr);
//		if(list!=null&&list.size()>0){
//			if(list.size()>1){
//				throw new RuntimeException(fileName +"存在不止一个"+elementStr+"标签，请检查！");
//			}
//			else{
//				Element e=(Element) list.get(0);
//				value=e.getText();
//			}
//		}
//		return value;
//	}
//	
//	/**
//	 * 根据属性名称获取文件中的元素属性值
//	 * @param filePath	文件路径
//	 * @param fileName	文件名称
//	 * @param attribute	节点
//	 * @param targetAttributeName	目标节点名称
//	 * @param encoding	编码
//	 * @return 节点字符串
//	 * @author xiaopeng.liu@decked.com.cn
//	 * 2016年5月4日下午10:00:48
//	 */
//	public static String readSingleAttributeValue(String filePath,String fileName,String attribute,String targetAttributeName,String encoding){
//		String value=null;
//		Document doc = null;
//		String content=FileUtil.readFile(filePath, encoding, true);
//		try {
//			doc = DocumentHelper.parseText(content);
//			List<?> list=doc.selectNodes("//@"+attribute);
//			if(list!=null&&list.size()>0){
//				if(list.size()>1){
//					throw new RuntimeException(fileName +"存在不止一个title标签，请检查！");
//				}
//				else{
//					DefaultAttribute a=(DefaultAttribute) list.get(0);
//					Element e=a.getParent();
//					Attribute target=e.attribute(targetAttributeName);
//					value=target.getValue();
//				}
//			}
//		} catch (Exception ex) {
//			throw new RuntimeException(fileName += "转换dom结构出错！",ex);
//		}
//		return value;
//	}
//	
//	/**
//	 * 根据获取整个element中包括attribute属性名称的所有节点
//	 * @param el	节点元素
//	 * @param attribute	节点属性
//	 * @return	节点数组
//	 * @author xiaopeng.liu@decked.com.cn
//	 * 2016年5月4日下午10:01:27
//	 */
//	public static List<?> readDomByAttribute(Element el,String attribute){
//		return el.selectNodes("//@"+attribute);
//	}
//	
//	public static List<?> selectAttrListByAttrName(String content,String xpath){
//		return string2Document(content).selectNodes(xpath);
//	}
//	
//	public static String source2DataXml(Document doc){
//		//格式化XML  
//        OutputFormat format = OutputFormat.createPrettyPrint(); 
//        //设置元素是否有子节点都输出  
//        format.setExpandEmptyElements(true);  
//        //设置不输出XML声明  
//        format.setSuppressDeclaration(true);  
//        StringWriter writer = new StringWriter();
//        XMLWriter htmlWriter = new XMLWriter(writer, format);  
//        try {
//			htmlWriter.write(doc);
//			htmlWriter.close();  
//		} catch (IOException e) {
//			throw new RuntimeException("文本内容转成html结构输出异常!",e);
//		}  
//        return writer.toString();
//	}
//	
//	public static String source2DataXml(String content){
//		Document doc = null;
//		try {
//			doc = DocumentHelper.parseText(content);
//		} catch (DocumentException e1) {
//			throw new RuntimeException("内容转换成dom结构失败!"+e1.getLocalizedMessage(),e1);
//		}
//		//格式化XML  
//        OutputFormat format = OutputFormat.createPrettyPrint(); 
//        //设置元素是否有子节点都输出  
//        format.setExpandEmptyElements(true);  
//        //设置不输出XML声明  
//        format.setSuppressDeclaration(true);  
//        StringWriter writer = new StringWriter();
//        XMLWriter htmlWriter = new XMLWriter(writer, format);  
//        try {
//			htmlWriter.write(doc);
//			htmlWriter.close();  
//		} catch (IOException e) {
//			throw new RuntimeException("文本内容转成html结构输出异常!",e);
//		}  
//        return writer.toString();
//	}
//	
//	public static String data2WebHtml(String content){
//		Document doc = null;
//		try {
//			doc = DocumentHelper.parseText(content);
//		} catch (DocumentException e1) {
//			throw new RuntimeException("内容转换成dom结构失败!"+e1.getLocalizedMessage(),e1);
//		}
//		//格式化XML  
//        OutputFormat format = OutputFormat.createPrettyPrint(); 
//        //设置元素是否有子节点都输出  
//        format.setExpandEmptyElements(true);  
//        //设置不输出XML声明  
//        format.setSuppressDeclaration(true);  
//        StringWriter writer = new StringWriter();
//        HTMLWriter htmlWriter = new HTMLWriter(writer, format);  
//        try {
//			htmlWriter.write(doc);
//			htmlWriter.close();  
//		} catch (IOException e) {
//			throw new RuntimeException("文本内容转成html结构输出异常!",e);
//		}  
//        return writer.toString();
//	}
//	
//	public static String getChildElements(Element elem){
//		List<?> childrenElement=elem.elements();//获取该结点下的所有元素集合
//		StringBuilder childElementContentBuilder=new StringBuilder();//该结点下的子节点字符串集合,刨去<iterator>和<single>
//		for(int i=0;i<childrenElement.size();i++){
//			Element e = (Element) childrenElement.get(i);
//			childElementContentBuilder.append(e.asXML());
//		}
//		return childElementContentBuilder.toString();
//	}
//	
//	
//	/**
//	 * 从doc中根据code使用xpath查找,将返回的结果并入到source对应的addIndex索引处
//	 * @param sourceElement	合并子element的元素
//	 * @param doc	子element的document集合
//	 * @param xpathCode	xpath查找code
//	 * @param addIndex	加入的索引处
//	 * @author xiaopeng.liu@decked.com.cn
//	 * 2016年5月4日下午10:02:00
//	 */
//	@SuppressWarnings("unchecked")
//	public static void combineElements(Element sourceElement, Document doc,String xpathCode,Integer addIndex){
//		List<?> childrenElements = doc.selectNodes(xpathCode);
//		if (!ListUtil.isEmpty(childrenElements)) {
//			Element childElement = (Element) childrenElements.get(0);
//			if(addIndex!=null){
//				sourceElement.content().addAll(addIndex,childElement.elements());
//			}
//			else{
//				sourceElement.content().addAll(childElement.elements());
//			}
//		}
//	}
//	
//	public static List<Element> cloneElements(List<Element> elements){
//		List<Element> elems=new ArrayList<Element>();
//		for(Element e:elements){
//			elems.add((Element) e.clone());
//		}
//		return elems;
//	}
	
}
