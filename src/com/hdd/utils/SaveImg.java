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
import java.util.Random;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class SaveImg {
	
	/**
	 * ��¼
	 * @param username
	 * @param password
	 * @return 
	 * @throws IOException 
	 */
	public ArrayList saveImg(String imgUri, String filename, String distUri, String minUri, String origUri) throws IOException{
		
		ArrayList allList = new ArrayList();
        // ����ļ�������
		BufferedImage src = ImageIO.read(new File(imgUri));
    	double imgWidth = src.getWidth(); // �õ�ԴͼƬ��
    	double imgHeight = src.getHeight();// �õ�ԴͼƬ��
    	
    	if (imgWidth > imgHeight && imgWidth > 750) {
    		imgHeight = imgHeight / imgWidth * 750;
    		imgWidth = 750;
    	} else {
    		if (imgHeight > 750) {
    			imgWidth = imgWidth / imgHeight * 750;
    			imgHeight = 750;
    		}
    	}
    	
    	// ����ͼƬ
		allList.add(doPageImg(src, (int)imgWidth, (int)imgHeight, distUri, filename));
        // ��������ͼ
        int minwidht = (int) (imgWidth * 0.6);
		int minheight = (int) (imgHeight * 0.6);
    	allList.add(doPageImg(src, minwidht, minheight, minUri, filename));
    	// ���Ƴ�ʼͼƬ
    	doPageImg(src, (int)src.getWidth(), (int)src.getHeight(), origUri, filename);
        return allList;
	}
	
	// ����ѹ��ͼƬ
	public ArrayList doPageImg(BufferedImage src, int imgWidth, int imgHeight, String distUri, String filename) throws IOException {
		ArrayList list = new ArrayList();
		list.add(String.valueOf(imgWidth));
		list.add(String.valueOf(imgHeight));
        // ����һ������ΪԤ����ͼ������֮һ�� BufferedImage
        BufferedImage tag = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        //����ͼ��  getScaledInstance��ʾ������ͼ������Ű汾������һ���µ����Ű汾Image,��ָ����width,height����ͼ��
        //Image.SCALE_SMOOTH,ѡ��ͼ��ƽ���ȱ������ٶȾ��и������ȼ���ͼ�������㷨��
        tag.getGraphics().drawImage(src.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), 0, 0, null);
        //�����ļ������
        FileOutputStream out = new FileOutputStream(distUri + File.separator + filename);
        //��ͼƬ��JPEGѹ�������浽out��
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(tag);
        //�ر��ļ������
        out.close();
        return list;
	}
}