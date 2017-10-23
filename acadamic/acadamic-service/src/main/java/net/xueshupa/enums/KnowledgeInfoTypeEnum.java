/**
 * 
 */
package net.xueshupa.enums;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public enum KnowledgeInfoTypeEnum {

	DISCOVER("发现"),
	EXPLORE("探索"),
	GAME("游戏"),
	SUMMARY("总结"),
	STEP("套路"),
	EXERCISE("练习");
	
	private String name;
	
	private KnowledgeInfoTypeEnum(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
