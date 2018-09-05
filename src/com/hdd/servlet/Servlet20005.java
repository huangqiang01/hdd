package com.hdd.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hdd.service.impl.GetDataImpl;
import com.hdd.utils.QOutput;

/**
 * ��������ȡͼƬ
 * @author Q
 */

public class Servlet20005 extends QOutput {
	
	private static final long serialVersionUID = 3536816937227069389L;
	
	// ����ҵ���
	GetDataImpl getDataImpl = new GetDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String id =request.getParameter("id");
		String typeid =request.getParameter("typeid");
		String minid =request.getParameter("minid");
		String albumid =request.getParameter("albumid");
		
		try {
			this.outPut(getDataImpl.getImgPhoto(id, typeid, minid, albumid), response);
		} catch (Exception e) {
			this.outPut("-222", "ϵͳ�ڲ����󣡣���", response);
			e.printStackTrace();
		}
	}

}
