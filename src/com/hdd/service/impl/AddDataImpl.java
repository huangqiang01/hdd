package com.hdd.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hdd.dao.AddDataDao;
import com.hdd.dao.GetDataDao;
import com.hdd.service.AddData;
import com.hdd.utils.VerSqlParam;

public class AddDataImpl implements AddData {
	
	private static final long serialVersionUID = -3885798033866006173L;
	
	GetDataDao getDataDao = new GetDataDao();
	AddDataDao addDataDao = new AddDataDao();
	// ��֤����
	VerSqlParam verSql = new VerSqlParam();
	
	@Override
	public String submitImgArr(String typeid, String imguri, String imgwidth, String imgheight, 
			String arrayname, String arraynum) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        // ����ʱ��
        String createtime = sdf.format(date);
        // ����ʱ��
        String updatetime = sdf.format(date);
        // �Ƿ���ʾ��1-Ĭ����ʾ
        String isshow = String.valueOf('1');
        // ��ע
        String reserve = "�Ƶ�����˧!";
        // ��֤�Ƿ�����
        verSql.checkNum(new String[]{ typeid, imgwidth, imgheight, arraynum });
        
        imguri = verSql.encodeString(imguri);
        
        verSql.checkLength(arrayname, 200);
        arrayname = verSql.codeHtml(arrayname);
        
		return addDataDao.addMinArr(typeid, imguri, imgwidth, imgheight, arrayname, arraynum, createtime, updatetime, isshow, reserve);
	}
	
	@Override
	public boolean submitImgDetails(String typeid, String minid, String uri, String imgwidth, String imgheight, String imgtitle, String imgcontent) throws Exception {
		
		// �Ƿ���ʾ��1-Ĭ����ʾ
        String isshow = String.valueOf('1');
        // ��ע
        String reserve = "�Ƶ�����˧!";
        
        // ��֤�Ƿ�����
        verSql.checkNum(new String[]{ typeid, minid, imgwidth, imgheight });
        
        uri = verSql.encodeString(uri);
        
        verSql.checkLength(imgtitle, 30);
        imgtitle = verSql.codeHtml(imgtitle);
        
        verSql.checkLength(imgcontent, 300);
        imgcontent = verSql.codeHtml(imgcontent);
		
		return addDataDao.addImages(typeid, minid, uri, imgwidth, imgheight, imgtitle, imgcontent, isshow, reserve);
	}

	@Override
	public boolean updateCheckZan(String checknum, String likenum, String id) throws Exception {
		// ��֤�Ƿ�����
        verSql.checkNum(new String[]{ checknum, likenum, id });
		return addDataDao.addCheckNum(checknum, likenum, id);
	}
}
