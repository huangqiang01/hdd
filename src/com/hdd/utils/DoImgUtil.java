package com.hdd.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class DoImgUtil {
	
	public String saveImgData(HashMap map, ArrayList list) {

    	//ʹ��params.get��ȡ����ֵ
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
        // ����ͼƬ������Ϣ
        String _firsturl = "/upload/min/" + firsturl;
        // ����ͼƬ��ַ
        String _shareurl = "/upload/share/" + _name1;
        String minid = addData.submitImgArr(arrtips, arralbum, _firsturl, String.valueOf((int)_firstwidth), String.valueOf((int)_firstheight), arrtitle, arrcontent, String.valueOf(listSize), _shareurl);
//        if (minid.equals("")) {
//        	session.removeAttribute("imglist");
//        	this.outPut("-2", "�ϴ�ͼƬʧ��", response);
//        }
        int mark = 0;
        for (int i = 0; i < listSize; i++) {
        	HashMap map = (HashMap) list.get(i);
        	//ʹ��params.get��ȡ����ֵ
        	String name = (String) map.get("name");
        	String imgw_h = (String) map.get("imgw_h");
        	String[] w_hArr = imgw_h.split("_");
        	String title = (String) map.get("title");
        	String content = (String) map.get("content");
        	String numno = (String) map.get("numno");
        	// ����ͼƬ
        	String _name = "/upload/cache/" + name;
        	boolean isupload = addData.submitImgDetails(arrtips, minid, arralbum, _name, w_hArr[0], w_hArr[1], title, content, numno);
        	if (isupload) {
        		mark++;
        	}
        }
	}

}
