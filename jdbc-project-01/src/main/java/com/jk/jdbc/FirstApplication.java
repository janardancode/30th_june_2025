package com.jk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstApplication {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kodewaladb30thjune", "root", "test");
		Statement stmnt = con.createStatement();
		String sql = "select * from product";

		ResultSet rs = stmnt.executeQuery(sql);

		while (rs.next()) {
			String product_id = rs.getString(1);
			String name = rs.getString(2);
			String description = rs.getString(3);
			double price = rs.getDouble(4);
			System.out.println(product_id);
			System.out.println(name);
			System.out.println(description);
			System.out.println(price);
		}

	}
}
