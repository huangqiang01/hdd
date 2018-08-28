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
	public OutResults getImgArr(String typeid, String minid) throws Exception;
	
	/**
	 * ��ȡͼƬ����
	 * @return
	 * @throws SQLException
	 */
	public OutResults getImgPhoto(String id, String typeid, String minid) throws Exception;
}
