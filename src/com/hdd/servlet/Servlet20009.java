package com.hdd.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hdd.service.impl.GetDataImpl;
import com.hdd.utils.QOutput;

/**
 * ��������ȡ���±�ǩ
 * @author Q
 */

public class Servlet20009 extends QOutput {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3921096145918738064L;
	// ����ҵ���
	GetDataImpl getDataImpl = new GetDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			this.outPut(getDataImpl.getArticleType(), response);
		} catch (SQLException e) {
			this.outPut("-222", "ϵͳ�ڲ����󣡣���", response);
			e.printStackTrace();
		}
	}

}
