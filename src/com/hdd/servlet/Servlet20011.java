package com.hdd.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hdd.service.impl.GetDataImpl;
import com.hdd.utils.QOutput;

/**
 * 描述：获取文章详情
 * @author Q
 */

public class Servlet20011 extends QOutput {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -852064134492244205L;
	// 创建业务层
	GetDataImpl getDataImpl = new GetDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// 获取信息
		String id = request.getParameter("id");
		try {
			this.outPut(getDataImpl.getArticleContent(id), response);
		} catch (Exception e) {
			this.outPut("-222", "系统内部错误！！！", response);
			e.printStackTrace();
		}
	}

}
