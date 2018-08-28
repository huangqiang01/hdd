package com.hdd.service;

import java.io.Serializable;
import java.sql.SQLException;

import com.hdd.utils.OutResults;
import com.hdd.utils.QException;

/**
 * 
 * �����������ݿ��������
 * @author Q
 * @created 2016��7��15�� ����12:53:19
 * @since
 */
public interface AddData extends Serializable {
	
	/**
	 * 
	 * �������ύͼƬ����
	 * @author Q
	 * @created 2016��7��15�� ����12:55:36
	 * @since 
	 * @param time �ύ��ʱ��
	 * @param date �ύ������
	 * @param text �ύ������
	 * @param mark 0-���飬1-����
	 * @param ip �ύ������
	 * @return
	 * @throws SQLException
	 * @throws QException 
	 */
	public String submitImgArr(String typeid, String imguri, String imgwidth, String imgheight, String arrayname, String arraynum) throws Exception;
	
	/**
	 * �ύͼƬ����
	 * @param typeid
	 * @param imguri
	 * @param arrayname
	 * @param arraynum
	 * @return
	 * @throws SQLException
	 */
	public boolean submitImgDetails(String typeid, String minid, String uri, String imgwidth, String imgheight, String imgtitle, String imgcontent) throws Exception;
	
	/**
	 * ���²鿴����
	 * @return
	 */
	public boolean updateCheckZan(String checknum, String likenum, String id) throws Exception;

}
