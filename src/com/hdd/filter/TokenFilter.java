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
//		// ƥ�书�ܺ�
//		String _url = matcherStr(req.getRequestURI());
//
//		if (_url.equals("20001") || _url.equals("20002")) {
//			String token = "";
//			// ���Cookie��Ϊ��
//			if (req.getCookies() !=null) {
//				// ����Cookie
//				for(Cookie cookie : req.getCookies()) {
//					if(cookie.getName().equals("token")) {
//						token = cookie.getValue();
//					}
//				}
//			}
//			if (!token.equals(session.getAttribute(session.getId()))) {
//				qoutput.outPut("-1000", "�Ƿ����ã����¼������", (HttpServletResponse) response);
//			}
//		}
        //���ݸ�Ŀ��servlet��jsp��ʵ����ʱ��װ����������ã�������ԭʼ��HttpServletRequest����
        chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	// ƥ�书�ܺ�
	public String matcherStr(String str) {
		Matcher matcher = Pattern.compile("\\d{5}").matcher(str);
		String _no = "";
		while (matcher.find()) {
			_no = matcher.group(0);
		}  
		return _no;
	}
}
