package com.hdd.servlet;

import java.io.File;
import java.io.IOException;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hdd.utils.ExifTester;
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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savePath = request.getServletContext().getRealPath("/upload/cache");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");  
        HashMap params = new HashMap();
        try {
            List items = upload.parseRequest(request);
            for (Object object : items) {
                FileItem fileItem = (FileItem) object;
                if (fileItem.isFormField()) {
                    params.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
                } else {
                	String fileName = getFilename();
                	String imgUri = savePath + File.separator + fileName;
                	File file = new File(imgUri);
                	fileItem.write(file);
                	params.put(fileItem.getFieldName(), imgUri);
                }
            }
            //ʹ��params.get��ȡ����ֵ
            String send_time = (String) params.get("image");
            String name = (String) params.get("name");
            System.out.println(send_time);
            System.out.println(name);
            
		} catch (Exception e) {
			// TODO: handle exception
		}
//        Collection<Part> parts = request.getParts();
//        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//        OutResults or = new OutResults();
//        // �������еı����ݣ������е��ļ�д���ϴ��ļ�Ŀ¼
//        for (Iterator<Part> iterator = parts.iterator(); iterator.hasNext();) {
//        
//            Part part = iterator.next();
//            String fileName = getFilename();
//            if (fileName != null) {
//            	String imgUri = savePath + File.separator + fileName;
//            	System.out.println(imgUri);
//                part.write(imgUri);
//                try {
//                	// ��ȡ��Ƭ��Ϣ
//					HashMap<String, String> map = exiftester.getPhotoInfo(imgUri);
//					map.put("uri", "/upload/cache" + File.separator + fileName);
//					list.add(map);
//				} catch (ImageProcessingException e) {
//					this.outPut("-1", "�ϴ�ͼƬʧ��", response);
//					e.printStackTrace();
//				}
//            }
//        }
//        or.setResults(list);
//        this.outPut(or, response);
    }
    
    // �����ļ�����
    public String getFilename() {
    	long _time = new Date().getTime();
        Random random = new Random();
        int rd = random.nextInt(999);
    	return _time + "_" + rd + ".jpg";
    }
}