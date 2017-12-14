/**
 * 
 */
package net.xueshupa.enums;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class KnowledgeInfoTypeModel {

	private String code;
	private String name;
	
	public KnowledgeInfoTypeModel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
