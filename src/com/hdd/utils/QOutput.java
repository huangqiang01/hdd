package com.hdd.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * 描述：自定义异常处理工具，并对异常信息进行输出
 * @author Q
 * @created 2016年4月29日 上午9:22:52
 * @since
 */

public class QOutput extends HttpServlet {
	
	private static final long serialVersionUID = -3273574819375933515L;
	/*
	 * 异常处理工具
	 */
	public OutResults setOutBean(String no, String msg){
		OutResults or = new OutResults();
		or.setError_no(no);
		or.setError_info(msg);
		return or;
	}

	/**
	 * 
	 * 描述：输出查询结果
	 * @author Q
	 * @created 2016年7月12日 下午4:00:23
	 * @since 
	 * @param or
	 * @param response
	 * @throws IOException
	 */
	public void outPut(OutResults or, HttpServletResponse response) throws IOException{
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		om.writeValue(pw, or);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 
	 * 描述：封装错误对象并输出
	 * @author Q
	 * @created 2016年7月12日 下午4:05:32
	 * @since 
	 * @param no
	 * @param msg
	 * @param response
	 * @throws IOException
	 */
	public void outPut(String no, String msg, HttpServletResponse response) throws IOException {
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		om.writeValue(pw, setOutBean(no, msg));
		pw.flush();
		pw.close();
	}
}
