package com.hdd.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
	public String submitImgArr(String typeid, String albumid, String imguri, String imgwidth, String imgheight, String arrayname, String arraycontent, String arraynum, String shareurl) throws Exception {
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
        verSql.checkNum(new String[]{ albumid, imgwidth, imgheight, arraynum });
        
        imguri = verSql.encodeString(imguri);
        // 1-2-3
        typeid = verSql.encodeString(typeid);
        shareurl = verSql.encodeString(shareurl);
        
        verSql.checkLength(arrayname, 100);
        arrayname = verSql.codeHtml(arrayname);
        
        verSql.checkLength(arraycontent, 300);
        arraycontent = verSql.codeHtml(arraycontent);
        
		return addDataDao.addMinArr(typeid, albumid, imguri, imgwidth, imgheight, arrayname, arraycontent, arraynum, createtime, updatetime, shareurl, isshow, reserve);
	}
	
	@Override
	public boolean submitImgDetails(String typeid, String minid, String albumid, String uri, String imgwidth, String imgheight, String imgtitle, String imgcontent, String numno) throws Exception {
		
		// 是否显示，1-默认显示
        String isshow = String.valueOf('1');
        // 备注
        String reserve = "黄蛋蛋最帅!";
        
        // 验证是否数字
        verSql.checkNum(new String[]{ albumid, minid, imgwidth, imgheight, numno });
        
        typeid = verSql.encodeString(typeid);
        uri = verSql.encodeString(uri);
        
        verSql.checkLength(imgtitle, 30);
        imgtitle = verSql.codeHtml(imgtitle);
        
        verSql.checkLength(imgcontent, 300);
        imgcontent = verSql.codeHtml(imgcontent);
		
		return addDataDao.addImages(typeid, minid, albumid, uri, imgwidth, imgheight, imgtitle, imgcontent, numno, isshow, reserve);
	}
	
	@Override
	public boolean submitImg(HashMap map, ArrayList list) throws Exception {
		
		return addDataDao.addImages(map, list);
	}
	
	@Override
	public boolean updateCheckZan(String checknum, String likenum, String id) throws Exception {
		// 验证是否数字
        verSql.checkNum(new String[]{ checknum, likenum, id });
		return addDataDao.addCheckNum(checknum, likenum, id);
	}

	@Override
	public boolean submitArticle(String itemid, String title, String desc, String writer, String content) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        // 创建时间
        String createtime = sdf.format(date);
        // 是否显示，1-默认显示
        String isshow = String.valueOf('1');
        // 备注
        String reserve = "黄蛋蛋最帅!";
        // 验证是否数字
        verSql.checkNum(new String[]{ itemid });
        
        writer = verSql.encodeString(writer);
        
        verSql.checkLength(title, 200);
        title = verSql.codeHtml(title);
        
        verSql.checkLength(desc, 600);
        desc = verSql.codeHtml(desc);
        
		return addDataDao.addArticle(itemid, title, desc, createtime, writer, isshow, reserve, content);
	}
}
