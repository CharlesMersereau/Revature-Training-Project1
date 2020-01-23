package com.revature.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.util.LoggerUtil;

public class ConnectionFactory {

	private static final String PROPERTIES_FILE = "src/main/webapp/WEB-INF/database.properties";
	private static String url = "";
	private static String username= "";
	private static String password="";
	private static ConnectionFactory cf;
	private LoggerUtil logger = new LoggerUtil();

	private ConnectionFactory() {

		Properties prop = new Properties();
		FileInputStream fis = null;
				
		try {
			fis = new FileInputStream(PROPERTIES_FILE);
			prop.load(fis);
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");

		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static Connection getConnection() {

		if (cf == null) {
			cf = new ConnectionFactory();
		}

		return cf.createConnection();
	}

	private Connection createConnection() {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
