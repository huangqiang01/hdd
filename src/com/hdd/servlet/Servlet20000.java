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
 * 描述：登录
 * @author Q
 */

public class Servlet20000 extends QOutput {
	
	private static final long serialVersionUID = -1803879837342807036L;
	
	private static final String KEY =":cookie@woaihdd.com"; // 密钥
	// 创建业务层
	GetDataImpl getDataImpl = new GetDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// 获取登录信息
		String username =request.getParameter("username");
        String password =request.getParameter("password");
		
		// 获取Session对象
		HttpSession session = request.getSession();
		// 清除cookie
		clearCookie(session);
		
		boolean isLogin = getDataImpl.getUserinfo(username, password);
		
		if (!isLogin) {
        	this.outPut("-2000001", "用户名或密码错误", response);
        } else {
        	// 获取token 生产信息
            String ip = request.getRemoteHost();
            long time = new Date().getTime();
            
			try {
				// 把账号、密钥使用MD5加密后保存
				String token = calcMD5(username + KEY + ip + time);
				// 新建Cookie
				Cookie tokenCookie = new Cookie("token", token);
//				tokenCookie.setDomain('/Q_jang');
				tokenCookie.setHttpOnly(true);
				tokenCookie.setPath(request.getContextPath());
				// 设置有效期 -1 -- 关闭浏览器即失效  , Integer.MAX_VALUE -- 永久有效
				tokenCookie.setMaxAge(-1);
			    response.addCookie(tokenCookie);
			    // 设置Session中的属性
			    session.setAttribute(session.getId(), token);
			    this.outPut("0", "登录成功", response);
			} catch (NoSuchAlgorithmException e) {
				this.outPut("-222", "系统内部错误！！！", response);
				e.printStackTrace();
			}
        }
	}
	
	public void clearCookie(HttpSession session) {
		if (session.getId()!= null) {
			session.removeAttribute(session.getId());
		}
	}
	
	 
    public final static String calcMD5(String ss) throws NoSuchAlgorithmException { // MD1 加密算法
       String s = ss == null ? "" : ss; // 若为null返回空
       char hexDigits[] = { '0','1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' }; // 字典
       byte[] strTemp = s.getBytes();                          // 获取字节
       MessageDigest mdTemp = MessageDigest.getInstance("MD5"); // 获取MD1
       mdTemp.update(strTemp);                                // 更新数据
       byte[] md =mdTemp.digest();                        // 加密
       int j =md.length;                                 // 加密后的长度
       char str[] = new char[j * 2];                       // 新字符串数组
       int k =0;                                         // 计数器k
       for (int i = 0; i< j; i++) {                       // 循环输出
           byte byte0 = md[i];
           str[k++] = hexDigits[byte0 >>> 4 & 0xf];
           str[k++] = hexDigits[byte0 & 0xf];
       }
       return new String(str);                             // 加密后字符串
    }

}
