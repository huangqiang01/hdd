package com.hdd.bean;

import java.io.Serializable;

/**
 * 
 * ��������ȡ���±�ǩ
 * @author Q
 * @created 2016��7��4�� ����2:28:23
 * @since
 */
public class ArticleType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9041179395680091708L;
	private String id;
	private String itemname;
	private String introduce;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
}
