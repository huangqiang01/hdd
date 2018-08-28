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
 * 描述：更新查看数量，和点赞数量
 * @author Q
 */

public class Servlet20006 extends QOutput {
	
	private static final long serialVersionUID = 3536816937227069389L;
	
	// 创建业务层
	AddDataImpl addData = new AddDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String checknum =request.getParameter("checknum");
		String likenum =request.getParameter("likenum");
		String minid =request.getParameter("minid");
		
		try {
			
			if (addData.updateCheckZan(checknum, likenum, minid)) {
				this.outPut("0", "操作成功", response);
			} else {
				this.outPut("-211", "操作失败", response);
			}
		} catch (Exception e) {
			this.outPut("-222", "系统内部错误！！！", response);
			e.printStackTrace();
		}
	}

}
