/**
 * 
 */
package net.xueshupa.enums;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<KnowledgeInfoTypeModel> toModelList(){
		List<KnowledgeInfoTypeModel> typeList = new ArrayList<KnowledgeInfoTypeModel>();
		for(KnowledgeInfoTypeEnum typeEnum:KnowledgeInfoTypeEnum.values()){
			typeList.add(new KnowledgeInfoTypeModel(typeEnum.toString(),typeEnum.getName()));
		}
		return typeList;
	}
}
