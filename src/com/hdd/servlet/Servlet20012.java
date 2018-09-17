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
 * 描述：增加文章
 * @author Q
 */

public class Servlet20012 extends QOutput {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -612699435117378447L;
	// 创建业务层
	AddDataImpl addData = new AddDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// 获取信息
		String itemid = request.getParameter("itemid");
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		try {
			// 保存文章列表
//			String id = addData.submitArticleArr(itemid, title, desc, writer);
			// 保存文章详情
			boolean submit = addData.submitArticle(itemid, title, desc, writer, content);
			if (submit) {
				this.outPut("0", "提交文章成功", response);
			} else {
				this.outPut("-12", "提交文章失败", response);
			}
		} catch (Exception e) {
			this.outPut("-222", "系统内部错误！！！", response);
			e.printStackTrace();
		}
	}

}
