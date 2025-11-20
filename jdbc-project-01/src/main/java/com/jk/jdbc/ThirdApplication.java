package com.jk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThirdApplication {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kodewaladb30thjune", "root", "test");

        String insertSql = "INSERT INTO product(name, description, price) VALUES (?, ?, ?)";
        PreparedStatement insertStmt = con.prepareStatement(insertSql);
        insertStmt.setString(1, "Mobile");
        insertStmt.setString(2, "Android phone");
        insertStmt.setDouble(3, 15999.99);
        int rowsInserted = insertStmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Product inserted successfully");
        }

        String selectSql = "SELECT * FROM product";
        PreparedStatement selectStmt = con.prepareStatement(selectSql);
        ResultSet rs = selectStmt.executeQuery();
        while (rs.next()) {
            String product_id = rs.getString(1);
            String name = rs.getString(2);
            String description = rs.getString(3);
            double price = rs.getDouble(4);
            System.out.println(product_id + " | " + name + " | " + description + " | " + price);
        }

        String updateSql = "UPDATE product SET price = ? WHERE product_id = ?";
        PreparedStatement updateStmt = con.prepareStatement(updateSql);
        updateStmt.setDouble(1, 499.99);
        updateStmt.setInt(2, 1);
        int rowsUpdated = updateStmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Product updated successfully");
        }

        String deleteSql = "DELETE FROM product WHERE product_id = ?";
        PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
        deleteStmt.setInt(1, 2);
        int rowsDeleted = deleteStmt.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Product deleted successfully");
        }

        insertStmt.close();
        selectStmt.close();
        updateStmt.close();
        deleteStmt.close();
        con.close();
    }
}
