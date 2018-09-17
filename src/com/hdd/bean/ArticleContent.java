package com.hdd.bean;

import java.io.Serializable;

/**
 * 
 * 描述：获取文章详情
 * @author Q
 * @created 2016年7月4日 下午2:28:23
 * @since
 */
public class ArticleContent implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2773253744237175536L;
	private String id;
	private String content;
	private String title;
	private String createtime;
	private String writer;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
}
