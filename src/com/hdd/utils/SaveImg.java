package com.hdd.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class SaveImg {
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return 
	 * @throws IOException 
	 */
	public ArrayList saveImg(String imgUri, String filename, String distUri, String minUri, String origUri) throws IOException{
		
		ArrayList allList = new ArrayList();
        // 获得文件输入流
		BufferedImage src = ImageIO.read(new File(imgUri));
    	double imgWidth = src.getWidth(); // 得到源图片宽
    	double imgHeight = src.getHeight();// 得到源图片高
    	
    	if (imgWidth > imgHeight && imgWidth > 750) {
    		imgHeight = imgHeight / imgWidth * 750;
    		imgWidth = 750;
    	} else {
    		if (imgHeight > 750) {
    			imgWidth = imgWidth / imgHeight * 750;
    			imgHeight = 750;
    		}
    	}
    	
    	// 保存图片
		allList.add(doPageImg(src, (int)imgWidth, (int)imgHeight, distUri, filename));
        // 处理缩略图
        int minwidht = (int) (imgWidth * 0.6);
		int minheight = (int) (imgHeight * 0.6);
    	allList.add(doPageImg(src, minwidht, minheight, minUri, filename));
    	// 复制初始图片
    	doPageImg(src, (int)src.getWidth(), (int)src.getHeight(), origUri, filename);
        return allList;
	}
	
	/**
	 * 保存小图片
	 * @param username
	 * @param password
	 * @return 
	 * @throws IOException 
	 */
	public ArrayList saveImg(HashMap params, String savePathMin) throws IOException{
		String image = (String) params.get("image");
		String name = (String) params.get("name");
        String imgw_h = (String) params.get("imgw_h");
        String isFirst = (String) params.get("isFirst");
        String title = (String) params.get("title");
        String content = (String) params.get("content");
        String numno = (String) params.get("numno");
        // 获得文件输入流
		BufferedImage src = ImageIO.read(new File(image));
		String[] w_h = imgw_h.split("_");
		int imgWidth = (int) (Integer.parseInt(w_h[0]) * 0.35);
		int imgHeight = (int) (Integer.parseInt(w_h[1]) * 0.35);
        // 处理缩略图
        return doPageImg(src, imgWidth, imgHeight, savePathMin, name);
	}
	
	// 处理压缩图片
	public ArrayList doPageImg(BufferedImage src, int imgWidth, int imgHeight, String distUri, String filename) throws IOException {
		ArrayList list = new ArrayList();
		list.add(String.valueOf(imgWidth));
		list.add(String.valueOf(imgHeight));
        // 构造一个类型为预定义图像类型之一的 BufferedImage
        BufferedImage tag = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
        //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
        tag.getGraphics().drawImage(src.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), 0, 0, null);
        //创建文件输出流
        FileOutputStream out = new FileOutputStream(distUri + File.separator + filename);
        //将图片按JPEG压缩，保存到out中
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(tag);
        //关闭文件输出流
        out.close();
        return list;
	}
}
