package com.hdd.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

/**
 * 
 * 描述：打开数据库，关闭数据库
 * @author Q
 * @created 2016年5月13日 下午2:02:26
 * @since
 */
public class DBHelper {
	
	private final static String url = "jdbc:mysql://127.0.0.1:3306/hdd?useSSL=false&useUnicode=true&characterEncoding=utf-8";
	
	private final static String driver = "com.mysql.jdbc.Driver";
	
	private final static String user = "root";
	
//	private final static String password = "tqyhq2@db";// 生产修改
	private final static String password = "root";// 生产修改
	
	public PreparedStatement pst = null;
	
	private static final Vector pool = new Vector();
	private static final int MAX_SIZE = 30;
	private static final int MIN_SIZE = 3;
	
	private static Connection ceateConnection() throws ClassNotFoundException, SQLException{
		Connection conn = null;
		// 指定连接类型
		Class.forName(driver);
		// 获取连接
		conn = DriverManager.getConnection(url, user, password);
		
		return conn;
	}
	static {
		for(int i = 0; i < MIN_SIZE; i++){
			try {
				pool.add(ceateConnection());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static synchronized Connection getConn() throws ClassNotFoundException, SQLException{
		Connection conn = null;
		if (pool.isEmpty()){
			conn = ceateConnection();
		} else {
			int last_id = pool.size() - 1;
			conn = (Connection) pool.get(last_id);
			pool.remove(conn);
		}
		return conn;
	}
	
	public static synchronized void close(Connection conn) throws SQLException{
		if (pool.size() < MAX_SIZE){
			pool.add(conn);
		} else {
			conn.close();
		}
	}
	
	public DBHelper(String sql){
		try {
			// 准备执行语句
			pst = getConn().prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DBHelper(Connection conn, String sql) {
		try {
			// 准备执行语句
			pst = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DBHelper(String sql, int returnGeneratedKeys) {
		try {
			// 准备执行语句
			pst = getConn().prepareStatement(sql, returnGeneratedKeys);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DBHelper(Connection conn, String sql, int returnGeneratedKeys) {
		try {
			// 准备执行语句
			pst = conn.prepareStatement(sql, returnGeneratedKeys);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
