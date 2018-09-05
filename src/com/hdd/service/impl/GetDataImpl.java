package com.hdd.service.impl;

import java.sql.SQLException;

import com.hdd.dao.GetDataDao;
import com.hdd.service.GetData;
import com.hdd.utils.OutResults;
import com.hdd.utils.VerSqlParam;

public class GetDataImpl implements GetData {
	
	private static final long serialVersionUID = 3388115292101105247L;
	
	GetDataDao getDataDao = new GetDataDao();
	
	// 验证参数
	VerSqlParam verSql = new VerSqlParam();
	
	@Override
	public boolean getUserinfo(String username, String password) {
		if (username.equals("admin") && password.equals("wwwtandoudoucom")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public OutResults getImgArrType() throws SQLException {
		return getDataDao.getImgArrType();
	}

	@Override
	public OutResults getImgArr(String typeid, String minid, String albumid) throws Exception {
		
		// 验证是否数字
        verSql.checkNum(new String[]{ typeid, minid, albumid });
		
		return getDataDao.getImgArr(typeid, minid, albumid);
	}

	@Override
	public OutResults getImgPhoto(String id, String typeid, String minid, String albumid) throws Exception {
		// 验证是否数字
        verSql.checkNum(new String[]{ id, typeid, minid, albumid });
		
		return getDataDao.getImgHpoto(id, typeid, minid, albumid);
	}

	@Override
	public OutResults getAlbum(String id) throws Exception {
		// 验证是否数字
        verSql.checkNum(new String[]{ id });
		
		return getDataDao.getImgAlbum(id);
	}
}
