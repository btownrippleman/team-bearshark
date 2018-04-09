package com.bearshark.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.test.wait.WaitManager;

public class ConnectionFactory {
	private static ConnectionFactory connFactory = null;
	private long connectionTimeout = 3000L;
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "bearshark";
	private static final String PASS = "password";
	
	private ConnectionFactory() {}
	
	public static ConnectionFactory getInstance() {
		if (connFactory == null)
			connFactory = new ConnectionFactory();
		return connFactory;
	}
	
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		long start = System.currentTimeMillis();
		while (true) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				return connection;
			}
			catch (SQLException e) {
				WaitManager.WaitMillis(50L);
				if (System.currentTimeMillis() > start + connectionTimeout)
					throw e;
			}
		}
	}
}
