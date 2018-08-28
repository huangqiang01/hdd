package com.hdd.bean;

import java.io.Serializable;

/**
 * 
 * 描述：获取图片
 * @author Q
 * @created 2016年7月4日 下午2:28:23
 * @since
 */
public class ImgPhoto implements Serializable {
	
	private static final long serialVersionUID = 773534906664586976L;
	private String id;
	private String typeid;
	private String minid;
	private String uri;
	private String imgwidth;
	private String imgheight;
	private String imgtitle;
	private String imgcontent;
	private String isshow;
	private String reserve;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getMinid() {
		return minid;
	}
	public void setMinid(String minid) {
		this.minid = minid;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getImgwidth() {
		return imgwidth;
	}
	public void setImgwidth(String imgwidth) {
		this.imgwidth = imgwidth;
	}
	public String getImgheight() {
		return imgheight;
	}
	public void setImgheight(String imgheight) {
		this.imgheight = imgheight;
	}
	public String getImgtitle() {
		return imgtitle;
	}
	public void setImgtitle(String imgtitle) {
		this.imgtitle = imgtitle;
	}
	public String getImgcontent() {
		return imgcontent;
	}
	public void setImgcontent(String imgcontent) {
		this.imgcontent = imgcontent;
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
