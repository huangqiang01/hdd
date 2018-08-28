package com.hdd.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ContentTypeFilter implements Filter {
	
	private String charset = "UTF-8";
	private FilterConfig config; 

	@Override
	public void destroy() {
        charset = null;
        config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//设置请求响应字符编码 
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        response.setContentType("text/html;charset=utf-8");
        //新增加的代码
        HttpServletRequest req = (HttpServletRequest)request;
        
        if (req.getMethod().equalsIgnoreCase("get")){
            req = new GetHttpServletRequestWrapper(req, charset);
        }
        
        //传递给目标servlet或jsp的实际上时包装器对象的引用，而不是原始的HttpServletRequest对象
        chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		 this.config = config;
         String charset = config.getServletContext().getInitParameter("charset");
         if( charset != null && charset.trim().length() != 0){
             this.charset = charset;
         }
	}
}
