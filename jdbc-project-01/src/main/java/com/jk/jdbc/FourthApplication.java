package com.jk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FourthApplication {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kodewaladb30thjune", "root", "test");
		String sql = "SELECT * FROM product WHERE price > ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, 0.0);

		ResultSet rs = ps.executeQuery();

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

		rs.close();
		ps.close();
		con.close();
	}
}

