package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static String url = "";
	private static String username= "";
	private static String password="";
	private static ConnectionFactory cf;
//	private LoggerUtil logger = new LoggerUtil();

	private ConnectionFactory() {

		url = "jdbc:postgresql://" + System.getenv("POSTGRES_URL_ENDPOINT") + ":5432/postgres?currentSchema=superhuman";
		username = System.getenv("POSTGRES_USERNAME");
		password = System.getenv("POSTGRES_PASSWORD");
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Connection getConnection() throws SQLException {

		if (cf == null) {
			cf = new ConnectionFactory();
		}

		return cf.createConnection(null);
	}
	
	public static Connection getConnection(String test) throws SQLException {

		if (cf == null) {
			cf = new ConnectionFactory();
		}

		return cf.createConnection(test);
	}
	
	private Connection createConnection(String test) throws SQLException {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, username, password);
			
			if (test != null) {
				conn.setSchema("superhuman_test");
			}
			
		} catch (SQLException e) {
//			logger.debug("could not establish a connection");
			throw e;
		}

		return conn;
	}
}
