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

@WebServlet(description = "文件上传", urlPatterns = { "/upload" })

/** 
 * 
 *     基于 HTML5 + Servlet 3.0 + AJAX
 * 
 *     maxFileSize:            最大上传文件大小,测试后认为应该是字节为单位
 *     fileSizeThreshold    :    当数据量大于该值时，内容将被写入文件。（specification中的解释的大概意思，不知道是不是指Buffer size），大小也是已字节单位
                            maxRequestSize = 8*1024*1024*6 //针对该 multipart/form-data 请求的最大数量，默认值为-1，表示没有限制。以字节为单位。
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
            	// 单位s
                session.setMaxInactiveInterval(1 * 60 * 30);
            	session.removeAttribute("imglist");
            }
            String firsturl = (String) params.get("firsturl");
            ArrayList list = (ArrayList) session.getAttribute("imglist");
            if (list == null) {
            	list = new ArrayList();
            }
            if (firsturl == null) {
                // 保存小图片
                ArrayList list1 = saveImg.saveImg(params, savePathMin);
                params.put("minimg", list1);
                list.add(params);
                session.setAttribute("imglist", list);
                this.outPut("0", "上传图片成功", response);
            } else {
            	int listSize = list.size();
            	if (listSize > 100) {
            		this.outPut("-100", "上传图片数量过大", response);
            		return;
            	}
            	// 保存图片
            	boolean isupload = addData.submitImg(params, list);
            	
//            	
//            	//使用params.get获取参数值
//                String arrtitle = (String) params.get("arrtitle");
//                String arrcontent = (String) params.get("arrcontent");
//                String arrtips = (String) params.get("arrtips");
//                String arralbum = (String) params.get("arralbum");
//                String firstwidth = (String) params.get("firstwidth");
//                String firstheight = (String) params.get("firstheight");
//                double _firstwidth = Integer.parseInt(firstwidth) * 0.4;
//                double _firstheight = Integer.parseInt(firstheight) * 0.4;
//                String _name1 = (String) params.get("name");
//                // 保存图片集合信息
//                String _firsturl = "/upload/min/" + firsturl;
//                // 分享图片地址
//                String _shareurl = "/upload/share/" + _name1;
//                String minid = addData.submitImgArr(arrtips, arralbum, _firsturl, String.valueOf((int)_firstwidth), String.valueOf((int)_firstheight), arrtitle, arrcontent, String.valueOf(listSize), _shareurl);
////                if (minid.equals("")) {
////                	session.removeAttribute("imglist");
////                	this.outPut("-2", "上传图片失败", response);
////                }
//                int mark = 0;
//                for (int i = 0; i < listSize; i++) {
//                	HashMap map = (HashMap) list.get(i);
//                	//使用params.get获取参数值
//                	String name = (String) map.get("name");
//                	String imgw_h = (String) map.get("imgw_h");
//                	String[] w_hArr = imgw_h.split("_");
//                	String title = (String) map.get("title");
//                	String content = (String) map.get("content");
//                	String numno = (String) map.get("numno");
//                	// 保存图片
//                	String _name = "/upload/cache/" + name;
//                	boolean isupload = addData.submitImgDetails(arrtips, minid, arralbum, _name, w_hArr[0], w_hArr[1], title, content, numno);
//                	if (isupload) {
//                		mark++;
//                	}
//                }
//                session.removeAttribute("imglist");
//                if (mark == listSize) {
//                	this.outPut("0", "上传成功", response);
//                } else {
//                	this.outPut("-1", "上传异常，请重试", response);
//                }
            }
		} catch (Exception e) {
			session.removeAttribute("imglist");
			this.outPut("-1", "系统异常", response);
			e.printStackTrace();
		}
    }
    
    // 生成文件名称
//    public String getFilename() {
//    	long _time = new Date().getTime();
//        Random random = new Random();
//        int rd = random.nextInt(999);
//    	return _time + "_" + rd + ".jpg";
//    }
}