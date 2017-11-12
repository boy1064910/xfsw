package net.xueshupa.entity;
import java.util.Date;

/**
* Course 
* @author lxp
* Mon Jul 24 22:18:23 CST 2017
*/ 
public class Course{

	private Integer id;
	private Integer userId;
	private String code;
	private String name;
	private Double originPrice;
	private Double price;
	private Integer buyCount;
	private Integer state;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	public Course(){}
	
	public Course(String code){
		this.code = code;
	}

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
	public void setState(Integer state){
		this.state=state;
	}
	public Integer getState(){
		return state;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}

