package com.hdd.bean;

import java.io.Serializable;

/**
 * 
 * 描述：获取图片集合
 * @author Q
 * @created 2016年7月4日 下午2:28:23
 * @since
 */
public class ImgArr implements Serializable {
	
	private static final long serialVersionUID = 773534906664586976L;
	private String id;
	private String typeid;
	private String imguri;
	private String imgwidth;
	private String imgheight;
	private String arrayname;
	private String arraynum;
	private String createtime;
	private String updatetime;
	private String checknum;
	private String likenum;
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
	public String getImguri() {
		return imguri;
	}
	public void setImguri(String imguri) {
		this.imguri = imguri;
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
	public String getArrayname() {
		return arrayname;
	}
	public void setArrayname(String arrayname) {
		this.arrayname = arrayname;
	}
	public String getArraynum() {
		return arraynum;
	}
	public void setArraynum(String arraynum) {
		this.arraynum = arraynum;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getChecknum() {
		return checknum;
	}
	public void setChecknum(String checknum) {
		this.checknum = checknum;
	}
	public String getLikenum() {
		return likenum;
	}
	public void setLikenum(String likenum) {
		this.likenum = likenum;
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
