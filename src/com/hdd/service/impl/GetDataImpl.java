package com.hdd.service.impl;

import java.sql.SQLException;

import com.hdd.dao.GetDataDao;
import com.hdd.service.GetData;
import com.hdd.utils.OutResults;
import com.hdd.utils.VerSqlParam;

public class GetDataImpl implements GetData {
	
	private static final long serialVersionUID = 3388115292101105247L;
	
	GetDataDao getDataDao = new GetDataDao();
	
	// ��֤����
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
	public OutResults getImgArr(String typeid, String minid) throws Exception {
		
		// ��֤�Ƿ�����
        verSql.checkNum(new String[]{ typeid, minid });
		
		return getDataDao.getImgArr(typeid, minid);
	}

	@Override
	public OutResults getImgPhoto(String id, String typeid, String minid) throws Exception {
		// ��֤�Ƿ�����
        verSql.checkNum(new String[]{ id, typeid, minid });
		
		return getDataDao.getImgHpoto(id, typeid, minid);
	}
}
