package net.xueshupa.entity;
import java.util.Date;
import java.util.List;

/**
* Exercise 
* @author lxp
* Mon Jul 24 22:18:23 CST 2017
*/ 
public class Exercise{

	private Integer id;
	private String code;
	private String picUrl;
	private Integer orderIndex;
	private String lastUpdater;
	private Date lastUpdateTime;
	
	private List<Answer> answerList;

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
	public void setOrderIndex(Integer orderIndex){
		this.orderIndex=orderIndex;
	}
	public Integer getOrderIndex(){
		return orderIndex;
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
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public List<Answer> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
}

