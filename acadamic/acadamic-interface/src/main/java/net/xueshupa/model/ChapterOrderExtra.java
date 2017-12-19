/**
 * 
 */
package net.xueshupa.model;

import java.io.Serializable;

/**
 * 
 * @author xiaopeng.liu
 * @version 0.0.1
 */
public class ChapterOrderExtra implements Serializable{

	private static final long serialVersionUID = 3894414883925325646L;

	private Integer chapterId;
	private String chapterName;
	/**
	 * @param chapterId
	 * @param chapterName
	 */
	public ChapterOrderExtra(Integer chapterId, String chapterName) {
		super();
		this.chapterId = chapterId;
		this.chapterName = chapterName;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
}
