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
	private String itemid;
	private String listid;
	private String content;
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
	public String getListid() {
		return listid;
	}
	public void setListid(String listid) {
		this.listid = listid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
