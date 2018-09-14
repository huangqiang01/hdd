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
	 * 提交图片集合
	 * @param typeid
	 * @param albumid
	 * @param imguri
	 * @param imgwidth
	 * @param imgheight
	 * @param arrayname
	 * @param arraynum
	 * @return
	 * @throws Exception
	 */
	public String submitImgArr(String typeid, String albumid, String imguri, String imgwidth, String imgheight, String arrayname, String arraycontent, String arraynum, String shareurl) throws Exception;
	
	/**
	 * 提交图片详情
	 * @param typeid
	 * @param imguri
	 * @param arrayname
	 * @param arraynum
	 * @return
	 * @throws SQLException
	 */
	public boolean submitImgDetails(String typeid, String minid, String albumid, String uri, String imgwidth, String imgheight, String imgtitle, String imgcontent, String numno) throws Exception;
	
	/**
	 * 更新查看数量
	 * @return
	 */
	public boolean updateCheckZan(String checknum, String likenum, String id) throws Exception;
	
	/**
	 * 提交文章列表
	 * @param itemid
	 * @param title
	 * @param desc
	 * @param createtime
	 * @param writer
	 * @param reserve
	 * @return
	 * @throws Exception
	 */
	public String submitArticleArr(String itemid, String title, String desc, String writer) throws Exception;
	
	/**
	 * 提交文章详情
	 * @param itemid
	 * @param listid
	 * @param content
	 * @param reserve
	 * @return
	 * @throws SQLException
	 */
	public boolean submitArticle(String itemid, String listid, String content) throws Exception;

}
