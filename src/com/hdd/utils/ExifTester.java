package com.hdd.utils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
 
import com.drew.metadata.exif.ExifIFD0Directory;
//import com.drew.imaging.jpeg.JpegMetadataReader;
//import com.drew.metadata.Directory;
//import com.drew.metadata.Metadata;
//import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
 
/**
 * �������ڶ�ȡͼƬ��EXIF��Ϣ
 * @author Q
 */
public class ExifTester {
	
	public HashMap<String, String> getPhotoInfo(String uri) throws ImageProcessingException, IOException {

		File jpegFile = new File(uri);
        Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, String> mapInfo = new HashMap<String, String>();
        for (Directory directory : metadata.getDirectories()) {
			if("ExifSubIFDDirectory".equalsIgnoreCase( directory.getClass().getSimpleName() )) {
//				//��ȦFֵ=��ͷ�Ľ���/��ͷ��Ȧ��ֱ��
//				System.out.println("��Ȧֵ: f/" + directory.getString(ExifSubIFDDirectory.TAG_FNUMBER));
//				System.out.println("�ع�ʱ��: " + directory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME)+ "��" );
//				System.out.println("ISO�ٶ�: " + directory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT) );
//				System.out.println("����: " + directory.getString(ExifSubIFDDirectory.TAG_FOCAL_LENGTH) + "����" );
//				
//				System.out.println("����ʱ��: " + directory.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL) );
//				System.out.println("��: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH) );
//				System.out.println("��: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT) );
				
				// ��Ȧֵ
				mapInfo.put("f_number", "f/" + directory.getString(ExifSubIFDDirectory.TAG_FNUMBER));
				// �ع�ʱ��
				mapInfo.put("time_of_exposure", directory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME) + "��");
				// ISO
				mapInfo.put("iso_equivalent", directory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT));
				// ����
				mapInfo.put("focal_length", directory.getString(ExifSubIFDDirectory.TAG_FOCAL_LENGTH) + "����");
				// ����ʱ��
				mapInfo.put("datetime_original", directory.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL));
				// ��
				mapInfo.put("image_width", directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH));
				// ��
				mapInfo.put("image_height", directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT));
				
				// ��
				map.put("image_width", directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH));
				// ��
				map.put("image_height", directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT));
			}
			
			if("ExifIFD0Directory".equalsIgnoreCase( directory.getClass().getSimpleName() )){
//				System.out.println("�����������: " + directory.getString(ExifIFD0Directory.TAG_MAKE) );
//				System.out.println("������ͺ�: " + directory.getString(ExifIFD0Directory.TAG_MODEL) );
//				System.out.println("ˮƽ�ֱ���: " + directory.getString(ExifIFD0Directory.TAG_X_RESOLUTION) );
//				System.out.println("��ֱ�ֱ���: " + directory.getString(ExifIFD0Directory.TAG_Y_RESOLUTION) );
				
				// �����������
				mapInfo.put("make", directory.getString(ExifIFD0Directory.TAG_MAKE));
				// ������ͺ�
				mapInfo.put("model", directory.getString(ExifIFD0Directory.TAG_MODEL));
				// ˮƽ�ֱ���
				mapInfo.put("x_resolution", directory.getString(ExifIFD0Directory.TAG_X_RESOLUTION));
				// ��ֱ�ֱ���
				mapInfo.put("y_resolution", directory.getString(ExifIFD0Directory.TAG_Y_RESOLUTION));
			}
        }
        // �����Ϣ�������ݿ�
        // -----
        
        
        return map;
	}
}
