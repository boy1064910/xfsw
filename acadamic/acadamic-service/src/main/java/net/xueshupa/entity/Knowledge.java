package net.xueshupa.entity;
import java.util.Date;

/**
* Knowledge 
* @author lxp
* Mon Jul 24 22:18:23 CST 2017
*/ 
public class Knowledge{

	private Integer id;
	private String code;
	private String name;
	private Integer orderIndex;
	private String videoUrl;
	private Integer exampleCount;
	private Integer videoSeconds;
	private String lastUpdater;
	private Date lastUpdateTime;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setCode(String code){
		this.code=code;
	}
	public String getCode(){
		return code;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setOrderIndex(Integer orderIndex){
		this.orderIndex=orderIndex;
	}
	public Integer getOrderIndex(){
		return orderIndex;
	}
	public void setVideoUrl(String videoUrl){
		this.videoUrl=videoUrl;
	}
	public String getVideoUrl(){
		return videoUrl;
	}
	public void setExampleCount(Integer exampleCount){
		this.exampleCount=exampleCount;
	}
	public Integer getExampleCount(){
		return exampleCount;
	}
	public void setVideoSeconds(Integer videoSeconds){
		this.videoSeconds=videoSeconds;
	}
	public Integer getVideoSeconds(){
		return videoSeconds;
	}
	public void setLastUpdater(String lastUpdater){
		this.lastUpdater=lastUpdater;
	}
	public String getLastUpdater(){
		return lastUpdater;
	}
	public void setLastUpdateTime(Date lastUpdateTime){
		this.lastUpdateTime=lastUpdateTime;
	}
	public Date getLastUpdateTime(){
		return lastUpdateTime;
	}
}

