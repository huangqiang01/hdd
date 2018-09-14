package com.hdd.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hdd.utils.OutResults;
import com.hdd.utils.QOutput;
public class Servlet20008 extends QOutput {
	
    private static final long serialVersionUID = 1L;
    
    public Servlet20008() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String savePath = request.getServletContext().getRealPath("/upload/web");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");  
        OutResults or = new OutResults();
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            List items = upload.parseRequest(request);
            for (Object object : items) {
                FileItem fileItem = (FileItem) object;
                String fileName = "_" + String.valueOf(new Date().getTime()) + ".jpg";
            	String imgUri = savePath + File.separator + fileName;
            	File file = new File(imgUri);
            	HashMap<String, String> map = new HashMap<String, String>();
            	map.put("uri", "/upload/web" + File.separator + fileName);
            	list.add(map);
            	fileItem.write(file);
            }
            or.setResults(list);
            this.outPut(or, response);
		} catch (Exception e) {
			this.outPut("-1", "ϵͳ�쳣", response);
			e.printStackTrace();
		}
    }
}