package net.xueshupa.model;

/**
 * 前端知识点详情页数据模型
 * @author LiuXi
 *
 */
public class MiniDiffLevelListModel {
	private Integer id;
	private String code;
	private String sequence;
	private String answerInfo;
	private String answerType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}


