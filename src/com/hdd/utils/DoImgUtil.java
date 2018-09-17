package com.hdd.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class DoImgUtil {
	
	public String saveImgData(HashMap map, ArrayList list) {

    	//使用params.get获取参数值
		String firsturl = (String) map.get("firsturl");
        String arrtitle = (String) map.get("arrtitle");
        String arrcontent = (String) map.get("arrcontent");
        String arrtips = (String) map.get("arrtips");
        String arralbum = (String) map.get("arralbum");
        String firstwidth = (String) map.get("firstwidth");
        String firstheight = (String) map.get("firstheight");
        double _firstwidth = Integer.parseInt(firstwidth) * 0.4;
        double _firstheight = Integer.parseInt(firstheight) * 0.4;
        String _name1 = (String) map.get("name");
        // 保存图片集合信息
        String _firsturl = "/upload/min/" + firsturl;
        // 分享图片地址
        String _shareurl = "/upload/share/" + _name1;
        String minid = addData.submitImgArr(arrtips, arralbum, _firsturl, String.valueOf((int)_firstwidth), String.valueOf((int)_firstheight), arrtitle, arrcontent, String.valueOf(listSize), _shareurl);
//        if (minid.equals("")) {
//        	session.removeAttribute("imglist");
//        	this.outPut("-2", "上传图片失败", response);
//        }
        int mark = 0;
        for (int i = 0; i < listSize; i++) {
        	HashMap map = (HashMap) list.get(i);
        	//使用params.get获取参数值
        	String name = (String) map.get("name");
        	String imgw_h = (String) map.get("imgw_h");
        	String[] w_hArr = imgw_h.split("_");
        	String title = (String) map.get("title");
        	String content = (String) map.get("content");
        	String numno = (String) map.get("numno");
        	// 保存图片
        	String _name = "/upload/cache/" + name;
        	boolean isupload = addData.submitImgDetails(arrtips, minid, arralbum, _name, w_hArr[0], w_hArr[1], title, content, numno);
        	if (isupload) {
        		mark++;
        	}
        }
	}

}
