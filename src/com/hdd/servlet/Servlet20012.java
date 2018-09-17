package com.hdd.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hdd.service.impl.AddDataImpl;
import com.hdd.service.impl.GetDataImpl;
import com.hdd.utils.QOutput;

/**
 * ��������������
 * @author Q
 */

public class Servlet20012 extends QOutput {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -612699435117378447L;
	// ����ҵ���
	AddDataImpl addData = new AddDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// ��ȡ��Ϣ
		String itemid = request.getParameter("itemid");
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		try {
			// ���������б�
//			String id = addData.submitArticleArr(itemid, title, desc, writer);
			// ������������
			boolean submit = addData.submitArticle(itemid, title, desc, writer, content);
			if (submit) {
				this.outPut("0", "�ύ���³ɹ�", response);
			} else {
				this.outPut("-12", "�ύ����ʧ��", response);
			}
		} catch (Exception e) {
			this.outPut("-222", "ϵͳ�ڲ����󣡣���", response);
			e.printStackTrace();
		}
	}

}
