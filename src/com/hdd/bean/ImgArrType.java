package com.hdd.bean;

import java.io.Serializable;

/**
 * 
 * 描述：获取图片集合类型
 * @author Q
 * @created 2016年7月4日 下午2:28:23
 * @since
 */
public class ImgArrType implements Serializable {
	
	private static final long serialVersionUID = 773534906664586976L;
	private String id;
	private String typename;
	private String introduce;
	private String isshow;
	private String reserve;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
}
