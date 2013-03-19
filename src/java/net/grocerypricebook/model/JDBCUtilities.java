/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.grocerypricebook.model;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author mike
 */
public class JDBCUtilities {
	public static String dbms = "mysql";
	public static String jarFile;
	public static String dbName = "webserviceshw4";
	public static String userName = "webserviceshw4";
	public static String password = "Oswego!1";
	public static String urlString;
	
	private static String serverName = "webserviceshw4.db.10690692.hostedresource.com";
	private static int portNumber = 3306;
	private static BoneCP connectionPool;
	
	private static boolean initialized;
	
	public static Connection getConnection() throws SQLException {
		
		
		if(!initialized){
			try {
				// load the database driver (make sure this is in your classpath!)
				Class.forName("com.mysql.jdbc.Driver");
			} catch (Exception e) {
				e.printStackTrace();
			}

			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:" + dbms + "://" +serverName +":" + portNumber + "/" + dbName );
			config.setUsername(userName); 
			config.setPassword(password);
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			config.setIdleConnectionTestPeriodInMinutes(1);
			config.setConnectionTestStatement("/* ping */ SELECT 1");
			connectionPool = new BoneCP(config); // setup the connection pool
			
			initialized = true;
			
			System.out.println("Connected to database");
			return connectionPool.getConnection();
		} else {
			System.out.println("Connected to database");
			return connectionPool.getConnection();
		}
	}
}
