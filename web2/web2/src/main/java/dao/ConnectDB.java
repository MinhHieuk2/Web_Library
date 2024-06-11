package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	private static String dbPrefix = "jdbc:mysql://localhost";
	private static String dbPort = "3306";
	private static String dbName = "web";
	private static String dbUser = "root";
	private static String dbPass = "123456";
	private static String dbClass = "com.mysql.cj.jdbc.Driver";
	public static Connection getConnection() {
		String dbUrl = dbPrefix + ":" + dbPort + "/" + dbName;
		Connection conn = null;
		try {
			Class.forName(dbClass);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		}catch(Exception e) {
			e.printStackTrace();			
		}
		return conn;
	}
}
