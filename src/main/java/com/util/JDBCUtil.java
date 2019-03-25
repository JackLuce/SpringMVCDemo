package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUtil {
	private static String mysqlName="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/jrtest?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	private static String userName = "root";
	private static String password="root";
	private static Connection con=null;
	private static PreparedStatement stmt=null;
	
	public static  Connection load() {
		try {
			Class.forName(mysqlName);
			System.out.println("驱动加载成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			 con=DriverManager.getConnection(url, userName, password);
			 System.out.println("数据库连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void close(PreparedStatement stmt,Connection con) {
		/*
		 * if(stmt!=null) { try { stmt.close(); } catch (SQLException e1) {
		 * e1.printStackTrace(); } if(con!=null) { try { con.close(); } catch
		 * (SQLException e) { e.printStackTrace(); } } }
		 */
		 try {
	            if(stmt !=null)stmt.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }

	        try {
	            if(con !=null)con.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	}
}
