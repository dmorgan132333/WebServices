/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

import java.sql.*;
import java.util.Properties;

/**
 *
 * @author mike
 */
public class JDBCUtilities {
	public static String dbms = "mysql";
	public static String jarFile;
	public static String dbName = "webserviceshw3";
	public static String userName = "webserviceshw3";
	public static String password = "Oswego!1";
	public static String urlString;
	
	private static String driver;
	private static String serverName = "webserviceshw3.db.10690692.hostedresource.com";
	private static int portNumber = 3306;
	private static Properties prop;
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		}

		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:" + dbms + "://" +serverName +":" + portNumber + "/" + dbName, connectionProps);

		System.out.println("Connected to database");
		return conn;
		}
}
