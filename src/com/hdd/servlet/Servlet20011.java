package com.hdd.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hdd.service.impl.GetDataImpl;
import com.hdd.utils.QOutput;

/**
 * ��������ȡ��������
 * @author Q
 */

public class Servlet20011 extends QOutput {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -852064134492244205L;
	// ����ҵ���
	GetDataImpl getDataImpl = new GetDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// ��ȡ��Ϣ
		String id = request.getParameter("id");
		try {
			this.outPut(getDataImpl.getArticleContent(id), response);
		} catch (Exception e) {
			this.outPut("-222", "ϵͳ�ڲ����󣡣���", response);
			e.printStackTrace();
		}
	}

}
