package com.hdd.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TokenFilter implements Filter {
	
	com.hdd.utils.QOutput qoutput = new com.hdd.utils.QOutput();
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
//		HttpSession session = req.getSession();
//		// 匹配功能号
//		String _url = matcherStr(req.getRequestURI());
//
//		if (_url.equals("20001") || _url.equals("20002")) {
//			String token = "";
//			// 如果Cookie不为空
//			if (req.getCookies() !=null) {
//				// 遍历Cookie
//				for(Cookie cookie : req.getCookies()) {
//					if(cookie.getName().equals("token")) {
//						token = cookie.getValue();
//					}
//				}
//			}
//			if (!token.equals(session.getAttribute(session.getId()))) {
//				qoutput.outPut("-1000", "非法调用，请登录后重试", (HttpServletResponse) response);
//			}
//		}
        //传递给目标servlet或jsp的实际上时包装器对象的引用，而不是原始的HttpServletRequest对象
        chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	// 匹配功能号
	public String matcherStr(String str) {
		Matcher matcher = Pattern.compile("\\d{5}").matcher(str);
		String _no = "";
		while (matcher.find()) {
			_no = matcher.group(0);
		}  
		return _no;
	}
}
