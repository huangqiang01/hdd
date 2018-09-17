package com.hdd.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hdd.service.impl.AddDataImpl;
import com.hdd.utils.ExifTester;
import com.hdd.utils.QOutput;
import com.hdd.utils.SaveImg;

@WebServlet(description = "�ļ��ϴ�", urlPatterns = { "/upload" })

/** 
 * 
 *     ���� HTML5 + Servlet 3.0 + AJAX
 * 
 *     maxFileSize:            ����ϴ��ļ���С,���Ժ���ΪӦ�����ֽ�Ϊ��λ
 *     fileSizeThreshold    :    �����������ڸ�ֵʱ�����ݽ���д���ļ�����specification�еĽ��͵Ĵ����˼����֪���ǲ���ָBuffer size������СҲ�����ֽڵ�λ
                            maxRequestSize = 8*1024*1024*6 //��Ը� multipart/form-data ��������������Ĭ��ֵΪ-1����ʾû�����ơ����ֽ�Ϊ��λ��
 */
@MultipartConfig( maxFileSize = 1024 *1024 * 100 , fileSizeThreshold = 819200 )
public class Servlet20001 extends QOutput {
	
	ExifTester exiftester = new ExifTester();
	
	SaveImg saveImg = new SaveImg();
	AddDataImpl addData = new AddDataImpl();
    
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savePath = request.getServletContext().getRealPath("/upload/cache");
        String savePathMin = request.getServletContext().getRealPath("/upload/min");
        String sharePath = request.getServletContext().getRealPath("/upload/share");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");  
        HashMap params = new HashMap();
        HttpSession session = request.getSession(true);
        try {
            List items = upload.parseRequest(request);
            for (Object object : items) {
                FileItem fileItem = (FileItem) object;
                if (fileItem.isFormField()) {
                    params.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
                } else {
                	String fileName = fileItem.getName();
                	String imgUri = "";
                	if (fileName.indexOf("share_") > -1) {
                		imgUri = sharePath + File.separator + fileName;
                	} else {
                		imgUri = savePath + File.separator + fileName;
                	}
                	File file = new File(imgUri);
                	fileItem.write(file);
                	params.put(fileItem.getFieldName(), imgUri);
                	params.put("name", fileName);
                	System.out.println(fileName);
                }
            }
            String firstupload = (String) params.get("firstupload");
            if (firstupload != null && firstupload.equals("1")) {
            	// ��λs
                session.setMaxInactiveInterval(1 * 60 * 30);
            	session.removeAttribute("imglist");
            }
            String firsturl = (String) params.get("firsturl");
            ArrayList list = (ArrayList) session.getAttribute("imglist");
            if (list == null) {
            	list = new ArrayList();
            }
            if (firsturl == null) {
                // ����СͼƬ
                ArrayList list1 = saveImg.saveImg(params, savePathMin);
                params.put("minimg", list1);
                list.add(params);
                session.setAttribute("imglist", list);
                this.outPut("0", "�ϴ�ͼƬ�ɹ�", response);
            } else {
            	int listSize = list.size();
            	if (listSize > 100) {
            		this.outPut("-100", "�ϴ�ͼƬ��������", response);
            		return;
            	}
            	// ����ͼƬ
            	boolean isupload = addData.submitImg(params, list);
            	
//            	
//            	//ʹ��params.get��ȡ����ֵ
//                String arrtitle = (String) params.get("arrtitle");
//                String arrcontent = (String) params.get("arrcontent");
//                String arrtips = (String) params.get("arrtips");
//                String arralbum = (String) params.get("arralbum");
//                String firstwidth = (String) params.get("firstwidth");
//                String firstheight = (String) params.get("firstheight");
//                double _firstwidth = Integer.parseInt(firstwidth) * 0.4;
//                double _firstheight = Integer.parseInt(firstheight) * 0.4;
//                String _name1 = (String) params.get("name");
//                // ����ͼƬ������Ϣ
//                String _firsturl = "/upload/min/" + firsturl;
//                // ����ͼƬ��ַ
//                String _shareurl = "/upload/share/" + _name1;
//                String minid = addData.submitImgArr(arrtips, arralbum, _firsturl, String.valueOf((int)_firstwidth), String.valueOf((int)_firstheight), arrtitle, arrcontent, String.valueOf(listSize), _shareurl);
////                if (minid.equals("")) {
////                	session.removeAttribute("imglist");
////                	this.outPut("-2", "�ϴ�ͼƬʧ��", response);
////                }
//                int mark = 0;
//                for (int i = 0; i < listSize; i++) {
//                	HashMap map = (HashMap) list.get(i);
//                	//ʹ��params.get��ȡ����ֵ
//                	String name = (String) map.get("name");
//                	String imgw_h = (String) map.get("imgw_h");
//                	String[] w_hArr = imgw_h.split("_");
//                	String title = (String) map.get("title");
//                	String content = (String) map.get("content");
//                	String numno = (String) map.get("numno");
//                	// ����ͼƬ
//                	String _name = "/upload/cache/" + name;
//                	boolean isupload = addData.submitImgDetails(arrtips, minid, arralbum, _name, w_hArr[0], w_hArr[1], title, content, numno);
//                	if (isupload) {
//                		mark++;
//                	}
//                }
//                session.removeAttribute("imglist");
//                if (mark == listSize) {
//                	this.outPut("0", "�ϴ��ɹ�", response);
//                } else {
//                	this.outPut("-1", "�ϴ��쳣��������", response);
//                }
            }
		} catch (Exception e) {
			session.removeAttribute("imglist");
			this.outPut("-1", "ϵͳ�쳣", response);
			e.printStackTrace();
		}
    }
    
    // �����ļ�����
//    public String getFilename() {
//    	long _time = new Date().getTime();
//        Random random = new Random();
//        int rd = random.nextInt(999);
//    	return _time + "_" + rd + ".jpg";
//    }
}