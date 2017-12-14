package net.xueshupa.entity;
import java.util.Date;

/**
* DiffLevel 
* @author lxp
* Mon Jul 24 22:18:23 CST 2017
*/ 
public class DiffLevel{

	private Integer id;
	private String code;
	private String sequence;
	private String answerInfo;
	private Integer answerType;
	private Integer orderIndex;
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
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getAnswerInfo() {
		return answerInfo;
	}
	public void setAnswerInfo(String answerInfo) {
		this.answerInfo = answerInfo;
	}
	public Integer getAnswerType() {
		return answerType;
	}
	public void setAnswerType(Integer answerType) {
		this.answerType = answerType;
	}
}

