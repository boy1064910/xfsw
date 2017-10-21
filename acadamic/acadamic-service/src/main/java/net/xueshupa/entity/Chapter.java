package net.xueshupa.entity;
import java.util.Date;

/**
* Chapter 
* @author lxp
* Mon Jul 24 22:18:23 CST 2017
*/ 
public class Chapter{

	private Integer id;
	private String sequence;
	private String code;
	private String name;
	private String info;
	private Integer orderIndex;
	private Integer knowledgeCount;
	private Integer exampleCount;
	private Integer videoSeconds;
	private Double originPrice;
	private Double price;
	private Integer buyCount;
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
	public void setKnowledgeCount(Integer knowledgeCount){
		this.knowledgeCount=knowledgeCount;
	}
	public Integer getKnowledgeCount(){
		return knowledgeCount;
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
	public void setOriginPrice(Double originPrice){
		this.originPrice=originPrice;
	}
	public Double getOriginPrice(){
		return originPrice;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	public Double getPrice(){
		return price;
	}
	public void setBuyCount(Integer buyCount){
		this.buyCount=buyCount;
	}
	public Integer getBuyCount(){
		return buyCount;
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
}

