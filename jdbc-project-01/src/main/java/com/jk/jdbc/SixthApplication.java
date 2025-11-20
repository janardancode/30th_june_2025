package com.jk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SixthApplication
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {

	// 1st step - Register JDBC driver
	Class.forName("com.mysql.cj.jdbc.Driver");

	// 2nd step - Create connection

	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kodewaladb30thjune", "root",
		"test");

	String insertQuery = " insert into orders (itemname, description, price, status) values(? , ? , ? , ?)";

	connection.setAutoCommit(false);
	// 3rd Create Statement
	PreparedStatement stmt = connection.prepareStatement(insertQuery);

	System.out.println(" Before executing query......");
	for (int i = 0; i < 100; i++)
	{
	    stmt.setString(1, "samsung s23" + i);
	    stmt.setString(2, "samsung s23........" + i);
	    stmt.setInt(3, 12345 + i);
	    stmt.setString(4, "Pending" + i);
	    stmt.addBatch();
	}
	System.out.println(" After executing Query......");
	int[] rs = stmt.executeBatch();
         System.out.println(" Batch updated......");    
	connection.commit();

    }
}
