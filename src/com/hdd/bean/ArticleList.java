package com.hdd.bean;

import java.io.Serializable;

/**
 * 
 * 描述：获取文章列表
 * @author Q
 * @created 2016年7月4日 下午2:28:23
 * @since
 */
public class ArticleList implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2997854390094433030L;
	private String id;
	private String itemid;
	private String title;
	private String articledesc;
	private String createtime;
	private String writer;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArticledesc() {
		return articledesc;
	}
	public void setArticledesc(String articledesc) {
		this.articledesc = articledesc;
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
