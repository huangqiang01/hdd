package com.hdd.utils;

public class QException extends Exception {
	
	private static final long serialVersionUID = -1868588148729708991L;
	
	private String no;
	private String msg;
	
	public QException(String no, String msg){
		super(msg);
		this.no = no;
		this.msg = msg;
	}
	
	public String getErrorNo(){
		return this.no;
	}
	
	public String getErrorInfo(){
		return this.msg;
	}
}
