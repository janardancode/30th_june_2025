package com.jk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SecondApplication {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kodewaladb30thjune", "root", "test");
        Statement stmnt = con.createStatement();

        String insertSql = "INSERT INTO product(name, description, price) VALUES ('Mobile', 'Android phone', 15999.99)";
        int rowsInserted = stmnt.executeUpdate(insertSql);
        if (rowsInserted > 0) {
            System.out.println("Product inserted successfully");
        }

        String selectSql = "SELECT * FROM product";
        ResultSet rs = stmnt.executeQuery(selectSql);
        while (rs.next()) {
            String product_id = rs.getString(1);
            String name = rs.getString(2);
            String description = rs.getString(3);
            double price = rs.getDouble(4);
            System.out.println(product_id + " | " + name + " | " + description + " | " + price);
        }

        String updateSql = "UPDATE product SET price = 499.99 WHERE product_id = 1";
        int rowsUpdated = stmnt.executeUpdate(updateSql);
        if (rowsUpdated > 0) {
            System.out.println("Product updated successfully");
        }

        String deleteSql = "DELETE FROM product WHERE product_id = 2";
        int rowsDeleted = stmnt.executeUpdate(deleteSql);
        if (rowsDeleted > 0) {
            System.out.println("Product deleted successfully");
        }

        stmnt.close();
        con.close();
    }
}

