package com.hdd.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hdd.service.impl.AddDataImpl;
import com.hdd.utils.VerSqlParam;
import com.hdd.utils.QOutput;
import com.hdd.utils.SaveImg;

/**
 * �������ύͼƬ��
 * @author Q
 */

public class Servlet20002 extends QOutput {
	
	private static final long serialVersionUID = 3536816937227069389L;
	
	SaveImg saveImg = new SaveImg();
	AddDataImpl addData = new AddDataImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	// ��ȡ��Ϣ
		String imgstr = request.getParameter("imgstr");
        String arrtitle = request.getParameter("arrtitle");
        String imgtype = request.getParameter("imgtype");
        // ʱ���
        String updatemark = request.getParameter("updatemark");
        HttpSession session = request.getSession(true);
        // ��λs
        session.setMaxInactiveInterval(1 * 60 * 30);
        String markStr = (String) session.getAttribute(updatemark);
        
        if (markStr != null) {
        	this.outPut("-22", "�����ظ��ύ", response);
        	return;
        }
        session.setAttribute("uploadmark", updatemark);
        String jsonStr = URLDecoder.decode(imgstr, "UTF-8");
        
        arrtitle = URLDecoder.decode(arrtitle, "UTF-8");
        // ͼƬ����·��
        String savePath = request.getServletContext().getRealPath("/upload/img");
        // ͼƬ����·��--Сͼ
        String savePathMin = request.getServletContext().getRealPath("/upload/min");
        // ͼƬ����·��--ԭͼ
        String savePathOrig = request.getServletContext().getRealPath("/upload/orig");
        
        String[] arr = jsonStr.split("\\|\\|");
        int arrLength = arr.length;

        // ͼƬ����
        ArrayList allList = new ArrayList();
        
        // ����ͼ
        ArrayList firstImg = new ArrayList();
        
        // ������������
        for (int i = 0; i < arrLength; i++) {
        	String filename = getFilename();
        	String img = arr[i];
        	String[] infoArr = img.split("\\$\\@");
        	
        	// ����ͼƬ��������
        	ArrayList list = saveImg.saveImg(request.getSession().getServletContext().getRealPath(infoArr[0]), filename, savePath, savePathMin, savePathOrig);
        	// �Ƿ����ó�ʡ��ͼ
        	if (infoArr.length > 3 && infoArr[3].equals("1")) {
        		firstImg.add(list.get(0));
        		firstImg.add(list.get(1));
        		firstImg.add(filename);
        	}
        	ArrayList list1 = new ArrayList();
        	list1.add(list.get(0));
        	list1.add(list.get(1));
        	list1.add(filename);
        	if (infoArr.length >= 2) {
        		list1.add(infoArr[1]);
        	}
        	if (infoArr.length >= 3) {
        		list1.add(infoArr[2]);
        	}
        	allList.add(list1);
        }
        
        try {
        	if (firstImg.isEmpty()) {
        		firstImg = (ArrayList) allList.get(0);
        	}
        	ArrayList _minArr = (ArrayList) firstImg.get(1);
        	String _uri = "/upload/min/" + (String) firstImg.get(2);
        	// ����ͼƬ������Ϣ
//			String minid = addData.submitImgArr(imgtype, _uri, (String)_minArr.get(0), (String)_minArr.get(1), arrtitle, String.valueOf(arrLength));
			
			for (int j = 0; j < arrLength; j++) {
				ArrayList imgArr = (ArrayList) allList.get(j);
				ArrayList _minArr1 = (ArrayList) imgArr.get(0);
				String _uri1 = "/upload/img/" + (String) imgArr.get(2);
				String imgtitle = "";
				String imgcontent = "";
				if (imgArr.size() > 3) {
					imgtitle = (String) imgArr.get(3);
				}
				if (imgArr.size() > 4) {
					imgcontent = (String) imgArr.get(4);
				}
				// �������鵽���ݿ�
//	        	addData.submitImgDetails(imgtype, minid, _uri1, (String)_minArr1.get(0), (String)_minArr1.get(1), imgtitle, imgcontent);
			}
			session.removeAttribute("uploadmark");
			this.outPut("0", "ͼƬ�ϴ��ɹ�", response);
		} catch (Exception e) {
			this.outPut("-111", "ͼƬ����ʧ��", response);
			e.printStackTrace();
		}
	}
	
	// �����ļ�����
    public String getFilename() {
    	long _time = new Date().getTime();
        Random random = new Random();
        int rd = random.nextInt(999);
    	return _time + "_" + rd + ".jpg";
    }

}
