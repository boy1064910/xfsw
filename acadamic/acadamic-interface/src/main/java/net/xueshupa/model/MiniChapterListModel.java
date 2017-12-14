package net.xueshupa.model;

public class MiniChapterListModel {

	private Integer id;
	private String code;
	private String sequence;
	private String name;
	private Integer knowledgeCount;
	private Integer exampleCount;
	private Integer videoSeconds;
	private Integer buyCount;
	private Double price;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
