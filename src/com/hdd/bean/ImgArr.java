package com.hdd.bean;

import java.io.Serializable;

/**
 * 
 * ��������ȡͼƬ����
 * @author Q
 * @created 2016��7��4�� ����2:28:23
 * @since
 */
public class ImgArr implements Serializable {
	
	private static final long serialVersionUID = 773534906664586976L;
	private String id;
	private String typeid;
	private String albumid;
	private String imguri;
	private String imgwidth;
	private String imgheight;
	private String arrayname;
	private String arraycontent;
	private String arraynum;
	private String createtime;
	private String updatetime;
	private String checknum;
	private String likenum;
	private String shareurl;
	
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
	public String getAlbumid() {
		return albumid;
	}
	public void setAlbumid(String albumid) {
		this.albumid = albumid;
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
	public String getArraycontent() {
		return arraycontent;
	}
	public void setArraycontent(String arraycontent) {
		this.arraycontent = arraycontent;
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
	public String getShareurl() {
		return shareurl;
	}
	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}
	
	
}
