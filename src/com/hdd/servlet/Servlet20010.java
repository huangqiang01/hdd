package com.hdd.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hdd.service.impl.GetDataImpl;
import com.hdd.utils.QOutput;

/**
 * ��������ȡ�����б�
 * @author Q
 */

public class Servlet20010 extends QOutput {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1520098030378456560L;
	// ����ҵ���
	GetDataImpl getDataImpl = new GetDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// ��ȡ��Ϣ
		String itemid = request.getParameter("itemid");
        String str = request.getParameter("str");
		try {
			this.outPut(getDataImpl.getArticleList(itemid, str), response);
		} catch (Exception e) {
			this.outPut("-222", "ϵͳ�ڲ����󣡣���", response);
			e.printStackTrace();
		}
	}

}
