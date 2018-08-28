package com.hdd.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class VerSqlParam {

	/**
	 * ��֤����
	 * @param num
	 * @throws QException
	 */
	public void checkNum(String[] num) throws QException {
		int numLength = num.length;
		for(int i = 0; i < numLength; i++) {
			String item = num[i];
			if (item != null && !item.equals("") && !item.matches("\\d+")) {
				throw new QException("-3", "����ʧ�ܣ�������");
			}
		}
	}
	
	/**
	 * �滻html �����ַ�
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
	 * �ֶα���
	 * @param str
	 * @return
	 * @throws QException
	 * @throws UnsupportedEncodingException 
	 */
	public String encodeString(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str,"UTF-8");
	}
	
	/**
	 * ����
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String decodeString(String str) throws UnsupportedEncodingException {
		return URLDecoder.decode(str,"UTF-8");
	}
	
	/**
	 * �ֶα���
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
	 * ������ݳ���
	 * @param str
	 * @return
	 * @throws QException 
	 */
	public void checkLength(String str, int length) throws QException {
		if (str != null) {
			if (str.length() > length) {
				throw new QException("-3", "����ʧ�ܣ�������");
			}
		}
	}
	
	
	public static void main(String[] args) {
		String str = "2018-08-20 21:48:53";
		str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&apos;");
			System.out.println(str.length());
		
	}
		
}
