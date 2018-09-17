package com.hdd.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.hdd.db.DBHelper;
import com.hdd.utils.QException;
import com.hdd.utils.VerSqlParam;

/**
 * 
 * �����������ݿ����Ӳ㣬����ѯ����
 * @author Q
 * @created 2016��7��15�� ����12:37:20
 * @since
 */
public class AddDataDao {
	
	static DBHelper dbh;
	static ResultSet rs;
	
	// ��֤����
	VerSqlParam verSql = new VerSqlParam();
	
	/**
	 * ����ͼƬ��
	 * @author Q
	 * @created 
	 * @since 
	 * @throws SQLException
	 * @throws IOException
	 * @throws QException 
	 */
	public String addMinArr(String typeid, String albumid, String imguri, String imgwidth, String imgheight,  String arrayname, String arraycontent, String arraynum, String createtime, String updatetime, String shareurl, String isshow, String reserve) throws Exception {
		String sql = "insert into minphoto(typeid,albumid,imguri,imgwidth,imgheight,arrayname,arraycontent,arraynum,createtime,updatetime,shareurl,isshow,reserve) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		dbh = new DBHelper(sql, Statement.RETURN_GENERATED_KEYS);
		dbh.pst.setString(1, typeid);
		dbh.pst.setString(2, albumid);
		dbh.pst.setString(3, imguri);
		dbh.pst.setString(4, imgwidth);
		dbh.pst.setString(5, imgheight);
		dbh.pst.setString(6, arrayname);
		dbh.pst.setString(7, arraycontent);
		dbh.pst.setString(8, arraynum);
		dbh.pst.setString(9, createtime);
		dbh.pst.setString(10, updatetime);
		dbh.pst.setString(11, shareurl);
		dbh.pst.setString(12, isshow);
		dbh.pst.setString(13, reserve);
		dbh.pst.executeUpdate();
		ResultSet rs = dbh.pst.getGeneratedKeys();
		if (rs.next()) {  
            int id = rs.getInt(1);  
            return id + "";
        }
		throw new QException("-2", "�ϴ�ͼƬ�쳣");
	}
	
//	/**
//	 * ����ͼƬ
//	 * @author Q
//	 * @created 2016
//	 * @since 
//	 * @throws SQLException
//	 * @throws IOException
//	 * @throws QException 
//	 */
//	public boolean addImages(String typeid, String minid, String albumid, String uri, String imgwidth, String imgheight, String imgtitle, String imgcontent, String numno, String isshow, String reserve) throws Exception {
//		String sql = "insert into myphoto(typeid,minid,albumid,uri,imgwidth,imgheight,imgtitle,imgcontent,numno,isshow,reserve) values(?,?,?,?,?,?,?,?,?,?,?)";
//		dbh = new DBHelper(sql);
//		dbh.pst.setString(1, typeid);
//		dbh.pst.setString(2, minid);
//		dbh.pst.setString(3, albumid);
//		dbh.pst.setString(4, uri);
//		dbh.pst.setString(5, imgwidth);
//		dbh.pst.setString(6, imgheight);
//		dbh.pst.setString(7, imgtitle);
//		dbh.pst.setString(8, imgcontent);
//		dbh.pst.setString(9, numno);
//		dbh.pst.setString(10, isshow);
//		dbh.pst.setString(11, reserve);
//		int up_num = dbh.pst.executeUpdate();
//		if (up_num > 0) {
//			return true;
//		} else {
//			throw new QException("-3", "�ϴ�ͼƬ�쳣");
//		}
//	}
	
	/**
	 * ����������ͼƬ
	 * @author Q
	 * @created 2018��9��17�� ����4:57:50
	 * @since 
	 * @param map
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean addImages(HashMap map, ArrayList list) {
		Connection conn = DBHelper.getConn();
		try {
			String sql = "insert into minphoto(typeid,albumid,imguri,imgwidth,imgheight,arrayname,arraycontent,arraynum,createtime,updatetime,shareurl,isshow,reserve) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			dbh = new DBHelper(conn, sql, Statement.RETURN_GENERATED_KEYS);
			dbh.pst.setString(1, typeid);
			dbh.pst.setString(2, albumid);
			dbh.pst.setString(3, imguri);
			dbh.pst.setString(4, imgwidth);
			dbh.pst.setString(5, imgheight);
			dbh.pst.setString(6, arrayname);
			dbh.pst.setString(7, arraycontent);
			dbh.pst.setString(8, arraynum);
			dbh.pst.setString(9, createtime);
			dbh.pst.setString(10, updatetime);
			dbh.pst.setString(11, shareurl);
			dbh.pst.setString(12, isshow);
			dbh.pst.setString(13, reserve);
			dbh.pst.executeUpdate();
			ResultSet rs = dbh.pst.getGeneratedKeys();
			
			
		} catch() {
			
		}
		
		
		
		
		dbh.pst.setString(1, typeid);
		dbh.pst.setString(2, albumid);
		dbh.pst.setString(3, imguri);
		dbh.pst.setString(4, imgwidth);
		dbh.pst.setString(5, imgheight);
		dbh.pst.setString(6, arrayname);
		dbh.pst.setString(7, arraycontent);
		dbh.pst.setString(8, arraynum);
		dbh.pst.setString(9, createtime);
		dbh.pst.setString(10, updatetime);
		dbh.pst.setString(11, shareurl);
		dbh.pst.setString(12, isshow);
		dbh.pst.setString(13, reserve);
		dbh.pst.executeUpdate();
		ResultSet rs = dbh.pst.getGeneratedKeys();
		if (rs.next()) {  
            int id = rs.getInt(1);  
            return id + "";
        }
		throw new QException("-2", "�ϴ�ͼƬ�쳣");
		
		
		
		
		String sql = "insert into myphoto(typeid,minid,albumid,uri,imgwidth,imgheight,imgtitle,imgcontent,numno,isshow,reserve) values(?,?,?,?,?,?,?,?,?,?,?)";
		dbh = new DBHelper(sql);
		dbh.pst.setString(1, typeid);
		dbh.pst.setString(2, minid);
		dbh.pst.setString(3, albumid);
		dbh.pst.setString(4, uri);
		dbh.pst.setString(5, imgwidth);
		dbh.pst.setString(6, imgheight);
		dbh.pst.setString(7, imgtitle);
		dbh.pst.setString(8, imgcontent);
		dbh.pst.setString(9, numno);
		dbh.pst.setString(10, isshow);
		dbh.pst.setString(11, reserve);
		int up_num = dbh.pst.executeUpdate();
		if (up_num > 0) {
			return true;
		} else {
			throw new QException("-3", "�ϴ�ͼƬ�쳣");
		}
	}
	
	/**
	 * ���ӵ����
	 * @param checknum
	 * @param likenum
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean addCheckNum(String checknum, String likenum, String id) throws Exception {
		Connection conn = DBHelper.getConn();
		try {
			String sql = "update minphoto set ";
			if (checknum != null && checknum.equals("1") ) {
				sql += "checknum=(checknum+1) ";
			} else if (likenum != null && likenum.equals("1")) {
				sql += "likenum=(likenum+1) ";
			}
			sql += "where id=" + id;
			dbh = new DBHelper(conn, sql);
			int up_num = dbh.pst.executeUpdate();
			if (up_num > 0) {
				return true;
			} else {
				throw new QException("-4", "����쳣");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(conn);
		}
		return false;
	}
	
	/**
	 * ���������б�-����
	 * @author Q
	 * @created 
	 * @since 
	 * @throws SQLException
	 * @throws IOException
	 * @throws QException 
	 */
	public boolean addArticle(String itemid, String title, String articledesc, String createtime, String writer, String isshow, String reserve, String content) throws Exception {
		Connection conn = DBHelper.getConn();
		try {
			String id = "";
			// --���������б� ��ʼ
			String sql = "insert into web_list(itemid,title,articledesc,createtime,writer,isshow,reserve) values(?,?,?,?,?,?,?);";
			// ��ʼ����
			conn.setAutoCommit(false);
			dbh = new DBHelper(conn, sql, Statement.RETURN_GENERATED_KEYS);
			dbh.pst.setString(1, itemid);
			dbh.pst.setString(2, title);
			dbh.pst.setString(3, articledesc);
			dbh.pst.setString(4, createtime);
			dbh.pst.setString(5, writer);
			dbh.pst.setString(6, isshow);
			dbh.pst.setString(7, reserve);
			dbh.pst.executeUpdate();
			ResultSet rs = dbh.pst.getGeneratedKeys();
			if (rs.next()) {
			    id = rs.getInt(1) + "";
			}
			// --���������б� ����
			if (id == null || id.equals("")) {
				// �ع�
				conn.rollback();
				throw new QException("-2", "���������쳣");
			}
			// --������������ ��ʼ
			sql = "insert into web_content(id,content,reserve) values(?,?,?)";
			dbh = new DBHelper(conn, sql);
			dbh.pst.setInt(1, Integer.parseInt(id));
			dbh.pst.setString(2, content);
			dbh.pst.setString(3, reserve);
			int up_num = dbh.pst.executeUpdate();
			if (up_num > 0) {
				conn.commit();
				return true;
			} else {
				// �ع�
				conn.rollback();
				throw new QException("-3", "���������쳣");
			}
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			DBHelper.close(conn);
		}
		return false;
	}
}
