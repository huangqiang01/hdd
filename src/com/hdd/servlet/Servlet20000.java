package com.hdd.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hdd.service.impl.GetDataImpl;
import com.hdd.utils.VerSqlParam;
import com.hdd.utils.QOutput;

/**
 * ��������¼
 * @author Q
 */

public class Servlet20000 extends QOutput {
	
	private static final long serialVersionUID = -1803879837342807036L;
	
	private static final String KEY =":cookie@woaihdd.com"; // ��Կ
	// ����ҵ���
	GetDataImpl getDataImpl = new GetDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// ��ȡ��¼��Ϣ
		String username =request.getParameter("username");
        String password =request.getParameter("password");
		
		// ��ȡSession����
		HttpSession session = request.getSession();
		// ���cookie
		clearCookie(session);
		
		boolean isLogin = getDataImpl.getUserinfo(username, password);
		
		if (!isLogin) {
        	this.outPut("-2000001", "�û������������", response);
        } else {
        	// ��ȡtoken ������Ϣ
            String ip = request.getRemoteHost();
            long time = new Date().getTime();
            
			try {
				// ���˺š���Կʹ��MD5���ܺ󱣴�
				String token = calcMD5(username + KEY + ip + time);
				// �½�Cookie
				Cookie tokenCookie = new Cookie("token", token);
//				tokenCookie.setDomain('/Q_jang');
				tokenCookie.setHttpOnly(true);
				tokenCookie.setPath(request.getContextPath());
				// ������Ч�� -1 -- �ر��������ʧЧ  , Integer.MAX_VALUE -- ������Ч
				tokenCookie.setMaxAge(-1);
			    response.addCookie(tokenCookie);
			    // ����Session�е�����
			    session.setAttribute(session.getId(), token);
			    this.outPut("0", "��¼�ɹ�", response);
			} catch (NoSuchAlgorithmException e) {
				this.outPut("-222", "ϵͳ�ڲ����󣡣���", response);
				e.printStackTrace();
			}
        }
	}
	
	public void clearCookie(HttpSession session) {
		if (session.getId()!= null) {
			session.removeAttribute(session.getId());
		}
	}
	
	 
    public final static String calcMD5(String ss) throws NoSuchAlgorithmException { // MD1 �����㷨
       String s = ss == null ? "" : ss; // ��Ϊnull���ؿ�
       char hexDigits[] = { '0','1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' }; // �ֵ�
       byte[] strTemp = s.getBytes();                          // ��ȡ�ֽ�
       MessageDigest mdTemp = MessageDigest.getInstance("MD5"); // ��ȡMD1
       mdTemp.update(strTemp);                                // ��������
       byte[] md =mdTemp.digest();                        // ����
       int j =md.length;                                 // ���ܺ�ĳ���
       char str[] = new char[j * 2];                       // ���ַ�������
       int k =0;                                         // ������k
       for (int i = 0; i< j; i++) {                       // ѭ�����
           byte byte0 = md[i];
           str[k++] = hexDigits[byte0 >>> 4 & 0xf];
           str[k++] = hexDigits[byte0 & 0xf];
       }
       return new String(str);                             // ���ܺ��ַ���
    }

}
