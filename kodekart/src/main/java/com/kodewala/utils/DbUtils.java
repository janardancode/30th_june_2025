package com.kodewala.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
	private static final String URL = "jdbc:mysql://localhost:3306/kodekart_db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "test"; 

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
