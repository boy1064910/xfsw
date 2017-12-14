package net.xueshupa.model;

import java.util.List;

import net.xueshupa.entity.Exercise;

public class MiniExerciseListModel {

	private Integer id;
	private String code;
	private String picUrl;
	private List<String> answers;
	
	public MiniExerciseListModel(Exercise exercise){
		this.id = exercise.getId();
//		this.code = exercise.getCode();
//		this.picUrl = exercise.getPicUrl();
//		if(!ListUtil.isEmpty(exercise.getAnswerList())){
//			this.answers = new ArrayList<String>(exercise.getAnswerList().size());
//			for(Answer answer:exercise.getAnswerList()){
//				this.answers.add(answer.getAnswer());
//			}
//		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
