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
 * 测试用于读取图片的EXIF信息
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
//				//光圈F值=镜头的焦距/镜头光圈的直径
//				System.out.println("光圈值: f/" + directory.getString(ExifSubIFDDirectory.TAG_FNUMBER));
//				System.out.println("曝光时间: " + directory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME)+ "秒" );
//				System.out.println("ISO速度: " + directory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT) );
//				System.out.println("焦距: " + directory.getString(ExifSubIFDDirectory.TAG_FOCAL_LENGTH) + "毫米" );
//				
//				System.out.println("拍照时间: " + directory.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL) );
//				System.out.println("宽: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH) );
//				System.out.println("高: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT) );
				
				// 光圈值
				mapInfo.put("f_number", "f/" + directory.getString(ExifSubIFDDirectory.TAG_FNUMBER));
				// 曝光时间
				mapInfo.put("time_of_exposure", directory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME) + "秒");
				// ISO
				mapInfo.put("iso_equivalent", directory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT));
				// 焦距
				mapInfo.put("focal_length", directory.getString(ExifSubIFDDirectory.TAG_FOCAL_LENGTH) + "毫米");
				// 拍照时间
				mapInfo.put("datetime_original", directory.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL));
				// 宽
				mapInfo.put("image_width", directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH));
				// 高
				mapInfo.put("image_height", directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT));
				
				// 宽
				map.put("image_width", directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH));
				// 高
				map.put("image_height", directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT));
			}
			
			if("ExifIFD0Directory".equalsIgnoreCase( directory.getClass().getSimpleName() )){
//				System.out.println("照相机制造商: " + directory.getString(ExifIFD0Directory.TAG_MAKE) );
//				System.out.println("照相机型号: " + directory.getString(ExifIFD0Directory.TAG_MODEL) );
//				System.out.println("水平分辨率: " + directory.getString(ExifIFD0Directory.TAG_X_RESOLUTION) );
//				System.out.println("垂直分辨率: " + directory.getString(ExifIFD0Directory.TAG_Y_RESOLUTION) );
				
				// 照相机制造商
				mapInfo.put("make", directory.getString(ExifIFD0Directory.TAG_MAKE));
				// 照相机型号
				mapInfo.put("model", directory.getString(ExifIFD0Directory.TAG_MODEL));
				// 水平分辨率
				mapInfo.put("x_resolution", directory.getString(ExifIFD0Directory.TAG_X_RESOLUTION));
				// 垂直分辨率
				mapInfo.put("y_resolution", directory.getString(ExifIFD0Directory.TAG_Y_RESOLUTION));
			}
        }
        // 相关信息存入数据库
        // -----
        
        
        return map;
	}
}
