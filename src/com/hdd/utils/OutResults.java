package com.hdd.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 描述：对查询结果进行封装，并给出查询说明
 * @author Q
 * @created 2016年5月13日 上午9:56:59
 * @since
 */
public class OutResults {
	
	// 保存查询结果，默认为[]
	private List results = new ArrayList();
	// 默认错误编号，默认为"0"
	private String error_no = "0";
	// 默认查询结果说明
	private String error_info = "调用成功";
	
	public List getResults() {
		return results;
	}
	public void setResults(List results) {
		this.results = results;
	}
	public String getError_no() {
		return error_no;
	}
	public void setError_no(String error_no) {
		this.error_no = error_no;
	}
	public String getError_info() {
		return error_info;
	}
	public void setError_info(String error_info) {
		this.error_info = error_info;
	}
	
	public void setError_info_no(String error_no, String error_info) {
		this.error_no = error_no;
		this.error_info = error_info;
	}
}
