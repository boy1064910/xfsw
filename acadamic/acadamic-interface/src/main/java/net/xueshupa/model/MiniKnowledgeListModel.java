package net.xueshupa.model;

public class MiniKnowledgeListModel {

	private Integer id;
	private String code;
	private String name;
	private Integer exampleCount;
	private Integer videoSeconds;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getExampleCount() {
		return exampleCount;
	}
	public void setExampleCount(Integer exampleCount) {
		this.exampleCount = exampleCount;
	}
	public Integer getVideoSeconds() {
		return videoSeconds;
	}
	public void setVideoSeconds(Integer videoSeconds) {
		this.videoSeconds = videoSeconds;
	}
}
