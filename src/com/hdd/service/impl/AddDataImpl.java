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
	// 验证参数
	VerSqlParam verSql = new VerSqlParam();
	
	@Override
	public String submitImgArr(String typeid, String imguri, String imgwidth, String imgheight, 
			String arrayname, String arraynum) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        // 创建时间
        String createtime = sdf.format(date);
        // 更新时间
        String updatetime = sdf.format(date);
        // 是否显示，1-默认显示
        String isshow = String.valueOf('1');
        // 备注
        String reserve = "黄蛋蛋最帅!";
        // 验证是否数字
        verSql.checkNum(new String[]{ typeid, imgwidth, imgheight, arraynum });
        
        imguri = verSql.encodeString(imguri);
        
        verSql.checkLength(arrayname, 200);
        arrayname = verSql.codeHtml(arrayname);
        
		return addDataDao.addMinArr(typeid, imguri, imgwidth, imgheight, arrayname, arraynum, createtime, updatetime, isshow, reserve);
	}
	
	@Override
	public boolean submitImgDetails(String typeid, String minid, String uri, String imgwidth, String imgheight, String imgtitle, String imgcontent) throws Exception {
		
		// 是否显示，1-默认显示
        String isshow = String.valueOf('1');
        // 备注
        String reserve = "黄蛋蛋最帅!";
        
        // 验证是否数字
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
		// 验证是否数字
        verSql.checkNum(new String[]{ checknum, likenum, id });
		return addDataDao.addCheckNum(checknum, likenum, id);
	}
}
