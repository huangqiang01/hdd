package com.hdd.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hdd.bean.ImgArr;
import com.hdd.bean.ImgArrType;
import com.hdd.bean.ImgPhoto;
import com.hdd.bean.Photoalbum;
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
		dbh = new DBHelper("select p.id,p.typename,p.introduce from phototype as p where isshow=1 order by id;");
		rs = dbh.pst.executeQuery();
		while(rs.next()){
			imgArrType = new ImgArrType();
			imgArrType.setId(rs.getString("id"));
			imgArrType.setTypename(rs.getString("typename"));
			imgArrType.setIntroduce(rs.getString("introduce"));
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
	public OutResults getImgArr(String typeid, String minid, String albumid) throws Exception{
		String sql = "select m.id,m.typeid,m.albumid,m.imguri,m.imgwidth,m.imgheight,m.arrayname,m.arraycontent,m.arraynum,m.createtime,m.updatetime,m.checknum,m.likenum,m.shareurl from minphoto as m where isshow=1 ";
		if (typeid != null && !typeid.equals("") && typeid.matches("\\d+")) {
			sql += "and typeid=" + typeid + " ";
		}
		if (minid != null && !minid.equals("") && minid.matches("\\d+")) {
			sql += "and id=" + minid + " ";
		}
		if (albumid != null && !albumid.equals("") && albumid.matches("\\d+")) {
			sql += "and albumid=" + albumid + " ";
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
			imgArr.setAlbumid(rs.getString("albumid"));
			imgArr.setImguri(verSql.decodeString(rs.getString("imguri")));
			imgArr.setImgwidth(rs.getString("imgwidth"));
			imgArr.setImgheight(rs.getString("imgheight"));
			imgArr.setArrayname(verSql.decodeString(rs.getString("arrayname")));
			imgArr.setArraycontent(verSql.decodeString(rs.getString("arraycontent")));
			imgArr.setArraynum(rs.getString("arraynum"));
			imgArr.setCreatetime(rs.getString("createtime"));
			imgArr.setUpdatetime(rs.getString("updatetime"));
			imgArr.setChecknum(rs.getString("checknum"));
			imgArr.setLikenum(rs.getString("likenum"));
			String shareurl = rs.getString("shareurl");
			if (shareurl != null && !shareurl.equals("")) {
				shareurl = verSql.decodeString(shareurl);
			}
			imgArr.setShareurl(shareurl);
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
	public OutResults getImgHpoto(String id, String typeid, String minid, String albumid) throws Exception{
		String sql = "select m.id,m.typeid,m.minid,m.albumid,m.uri,m.imgwidth,m.imgheight,m.imgtitle,m.imgcontent from myphoto as m where isshow=1 ";
		if (id != null && !id.equals("") && id.matches("\\d+")) {
			sql += "and id=" + id + " ";
		}
		if (typeid != null && !typeid.equals("") && typeid.matches("\\d+")) {
			sql += "and typeid=" + typeid + " ";
		}
		if (minid != null && !minid.equals("") && minid.matches("\\d+")) {
			sql += "and minid=" + minid + " ";
		}
		if (albumid != null && !albumid.equals("") && albumid.matches("\\d+")) {
			sql += "and albumid=" + albumid + " ";
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
			imgPhoto.setAlbumid(rs.getString("albumid"));
			imgPhoto.setUri(verSql.decodeString(rs.getString("uri")));
			imgPhoto.setImgwidth(rs.getString("imgwidth"));
			imgPhoto.setImgheight(rs.getString("imgheight"));
			imgPhoto.setImgtitle(verSql.decodeString(rs.getString("imgtitle")));
			imgPhoto.setImgcontent(verSql.decodeString(rs.getString("imgcontent")));
			list.add(imgPhoto);
		}
		or.setResults(list);
		return or;
	}
	
	/**
	 * 获取相册
	 * @return
	 * @throws SQLException
	 */
	public OutResults getImgAlbum(String id) throws Exception{
		String sql = "select m.id,m.albumname,m.introduce from photoalbum as m where isshow=1 ";
		if (id != null && !id.equals("") && id.matches("\\d+")) {
			sql += "and id=" + id + " ";
		}
		sql += "order by id;";
		List list = new ArrayList();
		OutResults or = new OutResults();
		//模拟获取数据库数据
		Photoalbum photoalbum;
		dbh = new DBHelper(sql);
		rs = dbh.pst.executeQuery();
		while(rs.next()){
			photoalbum = new Photoalbum();
			photoalbum.setId(rs.getString("id"));
			photoalbum.setAlbumname(rs.getString("albumname"));
			photoalbum.setIntroduce(rs.getString("introduce"));
			list.add(photoalbum);
		}
		or.setResults(list);
		return or;
	}
	
	
}
