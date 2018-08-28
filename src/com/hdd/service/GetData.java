package com.hdd.service;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import com.hdd.utils.OutResults;

/**
 * 
 * 描述：获取数据库数据
 * @author Q
 * @created 2016年7月15日 下午12:53:30
 * @since
 */
public interface GetData extends Serializable {
	
	/**
	 * 登录信息
	 * @return
	 * @throws SQLException
	 */
	public boolean getUserinfo(String username, String password);
	
	/**
	 * 获取图片集合类型
	 * @return
	 * @throws SQLException
	 */
	public OutResults getImgArrType() throws SQLException;
	
	/**
	 * 获取图片集合
	 * @return
	 * @throws SQLException
	 */
	public OutResults getImgArr(String typeid, String minid) throws Exception;
	
	/**
	 * 获取图片集合
	 * @return
	 * @throws SQLException
	 */
	public OutResults getImgPhoto(String id, String typeid, String minid) throws Exception;
}
