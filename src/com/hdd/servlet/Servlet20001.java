package com.hdd.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.drew.imaging.ImageProcessingException;
import com.hdd.utils.ExifTester;
import com.hdd.utils.OutResults;
import com.hdd.utils.QOutput;

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
    
    private static final long serialVersionUID = 1L;
    
    private String fileNameExtractorRegex = "filename=\".+\"";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savePath = request.getServletContext().getRealPath("/upload/cache");
        
        Collection<Part> parts = request.getParts();
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        OutResults or = new OutResults();
        // �������еı����ݣ������е��ļ�д���ϴ��ļ�Ŀ¼
        for (Iterator<Part> iterator = parts.iterator(); iterator.hasNext();) {
        
            Part part = iterator.next();
            
            // ��Part��content-disposition����ȡ�ϴ��ļ����ļ���
            String fileName = getFilename();
            
            if (fileName != null) {
            	String imgUri = savePath + File.separator + fileName;
            	System.out.println(imgUri);
                part.write(imgUri);
                try {
                	// ��ȡ��Ƭ��Ϣ
					HashMap<String, String> map = exiftester.getPhotoInfo(imgUri);
					map.put("uri", "/upload/cache" + File.separator + fileName);
					list.add(map);
				} catch (ImageProcessingException e) {
					this.outPut("-1", "�ϴ�ͼƬʧ��", response);
					e.printStackTrace();
				}
            }
        }
        or.setResults(list);
        this.outPut(or, response);
    }

//    /**
//     * ��Part��Header��Ϣ����ȡ�ϴ��ļ����ļ���
//     * 
//     * @param part
//     * @return �ϴ��ļ����ļ�����������û���򷵻�null
//     */
//    private String getFileName(Part part) {
//        
//        String fileName = null;
//
//        // ��ȡheader��Ϣ�е�content-disposition�����Ϊ�ļ�������Դ�������ȡ���ļ���
//        String cotentDesc = part.getHeader("content-disposition");
//        Pattern pattern = Pattern.compile(fileNameExtractorRegex);
//        
//        Matcher matcher = pattern.matcher(cotentDesc);
//        
//        if (matcher.find()) {
//            fileName = matcher.group();
//            fileName = fileName.substring(10, fileName.length() - 1);
//        }
//        return fileName;
//    }
    
    // �����ļ�����
    public String getFilename() {
    	long _time = new Date().getTime();
        Random random = new Random();
        int rd = random.nextInt(999);
    	return _time + "_" + rd + ".jpg";
    }
}