package net.xueshupa.model;

public class MiniCourseListModel {

	private Integer id;
	private String code;
	private String name;
	private Integer chapterCount;
	private Integer knowledgeCount;
	private Integer exampleCount;
	private Integer videoSeconds;
	private Integer buyCount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getChapterCount() {
		return chapterCount;
	}
	public void setChapterCount(Integer chapterCount) {
		this.chapterCount = chapterCount;
	}
	public Integer getKnowledgeCount() {
		return knowledgeCount;
	}
	public void setKnowledgeCount(Integer knowledgeCount) {
		this.knowledgeCount = knowledgeCount;
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
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
