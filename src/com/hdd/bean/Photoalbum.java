package com.hdd.bean;

import java.io.Serializable;

/**
 * ПаІб
 * @author hq
 *
 */
public class Photoalbum implements Serializable {

	private static final long serialVersionUID = 4789569905804134343L;
	
	private String id;
	private String albumname;
	private String introduce;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlbumname() {
		return albumname;
	}
	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
}
