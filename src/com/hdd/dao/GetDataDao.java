package com.hdd.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hdd.bean.ImgArr;
import com.hdd.bean.ImgArrType;
import com.hdd.bean.ImgPhoto;
import com.hdd.db.DBHelper;
import com.hdd.utils.OutResults;
import com.hdd.utils.VerSqlParam;

/**
 * 
 * 描述：与数据库连接层，并查询数据
 * @author Q
 * @created 2016年7月15日 下午12:37:20
 * @since
 */
public class GetDataDao {
	
	static DBHelper dbh;
	static ResultSet rs;
	
	// 验证参数
	VerSqlParam verSql = new VerSqlParam();
	
	/**
	 * 
	 * 描述：获取图片集合类型
	 * @author Q
	 * @created 2016年7月4日 下午2:37:41
	 * @since 
	 * @throws SQLException
	 * @throws IOException
	 */
	public OutResults getImgArrType() throws SQLException {
		List list = new ArrayList();
		OutResults or = new OutResults();
		//模拟获取数据库数据
		ImgArrType imgArrType;
		dbh = new DBHelper("select p.id,p.typename,p.introduce,p.reserve from phototype as p where isshow=1 order by id;");
		rs = dbh.pst.executeQuery();
		while(rs.next()){
			imgArrType = new ImgArrType();
			imgArrType.setId(rs.getString("id"));
			imgArrType.setTypename(rs.getString("typename"));
			imgArrType.setIntroduce(rs.getString("introduce"));
			imgArrType.setReserve(rs.getString("reserve"));
			list.add(imgArrType);
		}
		or.setResults(list);
		return or;
	}
	
	/**
	 * 获取图片集
	 * @return
	 * @throws SQLException
	 */
	public OutResults getImgArr(String typeid, String minid) throws Exception{
		String sql = "select m.id,m.typeid,m.imguri,m.imgwidth,m.imgheight,m.arrayname,m.arraynum,m.createtime,m.updatetime,m.checknum,m.likenum,m.reserve from minphoto as m where isshow=1 ";
		if (typeid != null && !typeid.equals("") && typeid.matches("\\d+")) {
			sql += "and typeid=" + typeid + " ";
		}
		if (minid != null && !minid.equals("") && minid.matches("\\d+")) {
			sql += "and id=" + minid + " ";
		}
		sql += "order by id;";
		List list = new ArrayList();
		OutResults or = new OutResults();
		//模拟获取数据库数据
		ImgArr imgArr;
		dbh = new DBHelper(sql);
		rs = dbh.pst.executeQuery();
		while(rs.next()){
			imgArr = new ImgArr();
			imgArr.setId(rs.getString("id"));
			imgArr.setTypeid(rs.getString("typeid"));
			imgArr.setImguri(verSql.decodeString(rs.getString("imguri")));
			imgArr.setImgwidth(rs.getString("imgwidth"));
			imgArr.setImgheight(rs.getString("imgheight"));
			imgArr.setArrayname(verSql.decodeString(rs.getString("arrayname")));
			imgArr.setArraynum(rs.getString("arraynum"));
			imgArr.setCreatetime(rs.getString("createtime"));
			imgArr.setUpdatetime(rs.getString("updatetime"));
			imgArr.setChecknum(rs.getString("checknum"));
			imgArr.setLikenum(rs.getString("likenum"));
			imgArr.setReserve(rs.getString("reserve"));
			list.add(imgArr);
		}
		or.setResults(list);
		return or;
	}
	
	/**
	 * 获取图片
	 * @return
	 * @throws SQLException
	 */
	public OutResults getImgHpoto(String id, String typeid, String minid) throws Exception{
		String sql = "select m.id,m.typeid,m.minid,m.uri,m.imgwidth,m.imgheight,m.imgtitle,m.imgcontent,m.reserve from myphoto as m where isshow=1 ";
		if (id != null && !id.equals("") && id.matches("\\d+")) {
			sql += "and id=" + id + " ";
		}
		if (typeid != null && !typeid.equals("") && typeid.matches("\\d+")) {
			sql += "and typeid=" + typeid + " ";
		}
		if (minid != null && !minid.equals("") && minid.matches("\\d+")) {
			sql += "and minid=" + minid + " ";
		}
		sql += "order by id;";
		List list = new ArrayList();
		OutResults or = new OutResults();
		//模拟获取数据库数据
		ImgPhoto imgPhoto;
		dbh = new DBHelper(sql);
		rs = dbh.pst.executeQuery();
		while(rs.next()){
			imgPhoto = new ImgPhoto();
			imgPhoto.setId(rs.getString("id"));
			imgPhoto.setTypeid(rs.getString("typeid"));
			imgPhoto.setMinid(rs.getString("minid"));
			imgPhoto.setUri(verSql.decodeString(rs.getString("uri")));
			imgPhoto.setImgwidth(rs.getString("imgwidth"));
			imgPhoto.setImgheight(rs.getString("imgheight"));
			imgPhoto.setImgtitle(verSql.decodeString(rs.getString("imgtitle")));
			imgPhoto.setImgcontent(verSql.decodeString(rs.getString("imgcontent")));
			imgPhoto.setReserve(rs.getString("reserve"));
			list.add(imgPhoto);
		}
		or.setResults(list);
		return or;
	}
}
