package com.hdd.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class VerSqlParam {

	/**
	 * 验证数字
	 * @param num
	 * @throws QException
	 */
	public void checkNum(String[] num) throws QException {
		int numLength = num.length;
		for(int i = 0; i < numLength; i++) {
			String item = num[i];
			if (item != null && !item.equals("") && !item.matches("\\d+")) {
				throw new QException("-3", "操作失败，请重试");
			}
		}
	}
	
	/**
	 * 替换html 常见字符
	 * @param str
	 * @return
	 * @throws QException
	 */
	public String htmlString(String str) {
		return str.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;")
				.replaceAll(" ", "&nbsp;")
				.replaceAll("\"", "&quot;");
	}
	
	/**
	 * 字段编码
	 * @param str
	 * @return
	 * @throws QException
	 * @throws UnsupportedEncodingException 
	 */
	public String encodeString(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str,"UTF-8");
	}
	
	/**
	 * 解码
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String decodeString(String str) throws UnsupportedEncodingException {
		return URLDecoder.decode(str,"UTF-8");
	}
	
	/**
	 * 字段编码
	 * @param str
	 * @return
	 * @throws QException
	 * @throws UnsupportedEncodingException 
	 */
	public String codeHtml(String str) throws UnsupportedEncodingException {
		str = this.htmlString(str);
		return this.encodeString(str);
	}
	
	/**
	 * 检查数据长度
	 * @param str
	 * @return
	 * @throws QException 
	 */
	public void checkLength(String str, int length) throws QException {
		if (str != null) {
			if (str.length() > length) {
				throw new QException("-3", "操作失败，请重试");
			}
		}
	}
	
	
	public static void main(String[] args) {
		String str = "2018-08-20 21:48:53";
		str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&apos;");
			System.out.println(str.length());
		
	}
		
}
