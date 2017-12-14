package net.xueshupa.model;

/**
 * 前端知识点详情页数据模型
 * @author LiuXi
 *
 */
public class MiniKnowledgeInfoModel {
	private Integer id;
	private String code;
	private String name;
	private String videoUrl;
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
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}


