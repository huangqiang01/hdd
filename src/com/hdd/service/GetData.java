package com.hdd.service;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import com.hdd.utils.OutResults;

/**
 * 
 * ��������ȡ���ݿ�����
 * @author Q
 * @created 2016��7��15�� ����12:53:30
 * @since
 */
public interface GetData extends Serializable {
	
	/**
	 * ��¼��Ϣ
	 * @return
	 * @throws SQLException
	 */
	public boolean getUserinfo(String username, String password);
	
	/**
	 * ��ȡͼƬ��������
	 * @return
	 * @throws SQLException
	 */
	public OutResults getImgArrType() throws SQLException;
	
	/**
	 * ��ȡͼƬ����
	 * @return
	 * @throws SQLException
	 */
	public OutResults getImgArr(String typeid, String minid, String albumid) throws Exception;
	
	/**
	 * ��ȡͼƬ����
	 * @return
	 * @throws SQLException
	 */
	public OutResults getImgPhoto(String id, String typeid, String minid, String albumid) throws Exception;
	
	/**
	 * ��ȡ���
	 * @return
	 * @throws SQLException
	 */
	public OutResults getAlbum(String id) throws Exception;
	
	/**
	 * ��ȡ���±�ǩ
	 * @return
	 * @throws SQLException
	 */
	public OutResults getArticleType() throws SQLException;
	
	/**
	 * ��ȡ�����б�
	 * @return
	 * @throws SQLException
	 */
	public OutResults getArticleList(String itemid, String str) throws Exception;
	
	/**
	 * ��ȡ��������
	 * @return
	 * @throws SQLException
	 */
	public OutResults getArticleContent(String id) throws Exception;
	
}
