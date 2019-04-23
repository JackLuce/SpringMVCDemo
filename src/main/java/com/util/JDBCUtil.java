package com.util;

import java.sql.*;

public class JDBCUtil {
	private static String mysqlName="com.mysql.jdbc.Driver";
	private static String userName = "root";
	//本地mysql
//	private static String url="jdbc:mysql://localhost:3306/jrtest?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
//	private static String password="root";//本地mysql密码
	//虚拟机mysql
	private static String url="jdbc:mysql://192.168.63.200:3306/jrtest?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	private static String password="123456";//虚拟机mysql密码

	private static Connection con=null;
//	private static PreparedStatement stmt=null;
// static修饰connection在一个类中被共享，
// 在多线程的环境中，使用单个（static）connection会引起事务的混乱

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

	/**
	 * 关闭数据库
	 * @param resultSet
	 * @param stmt
	 * @param con
	 */
	public static void close(ResultSet resultSet, PreparedStatement stmt, Connection con) {
			try {
				if(resultSet !=null)resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	/**
	 * 关闭数据库
	 * @param stmt
	 * @param con
	 */
	public static void close(PreparedStatement stmt, Connection con) {
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
