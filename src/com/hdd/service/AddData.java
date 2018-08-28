package com.hdd.service;

import java.io.Serializable;
import java.sql.SQLException;

import com.hdd.utils.OutResults;
import com.hdd.utils.QException;

/**
 * 
 * 描述：向数据库添加数据
 * @author Q
 * @created 2016年7月15日 下午12:53:19
 * @since
 */
public interface AddData extends Serializable {
	
	/**
	 * 
	 * 描述：提交图片集合
	 * @author Q
	 * @created 2016年7月15日 下午12:55:36
	 * @since 
	 * @param time 提交的时间
	 * @param date 提交的日期
	 * @param text 提交的内容
	 * @param mark 0-建议，1-留言
	 * @param ip 提交的主机
	 * @return
	 * @throws SQLException
	 * @throws QException 
	 */
	public String submitImgArr(String typeid, String imguri, String imgwidth, String imgheight, String arrayname, String arraynum) throws Exception;
	
	/**
	 * 提交图片详情
	 * @param typeid
	 * @param imguri
	 * @param arrayname
	 * @param arraynum
	 * @return
	 * @throws SQLException
	 */
	public boolean submitImgDetails(String typeid, String minid, String uri, String imgwidth, String imgheight, String imgtitle, String imgcontent) throws Exception;
	
	/**
	 * 更新查看数量
	 * @return
	 */
	public boolean updateCheckZan(String checknum, String likenum, String id) throws Exception;

}
