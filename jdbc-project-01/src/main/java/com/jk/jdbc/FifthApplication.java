package com.jk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FifthApplication {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/kodewaladb30thjune", "root", "test");

        String sql = "SELECT * FROM orders WHERE price > ? AND status = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, 1000);
        stmt.setString(2, "Delivered");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String item = rs.getString("itemname");
            String desc = rs.getString("description");
            int price = rs.getInt("price");
            String status = rs.getString("status");

            System.out.println("  | item " + item + " | desc " + desc + " | price " + price + " | status " + status);
        }

        String updateStatusSql = "UPDATE orders SET status = 'Delivered' WHERE status != 'Delivered'";
        PreparedStatement updateStmt = connection.prepareStatement(updateStatusSql);

        int recordsUpdated = updateStmt.executeUpdate();
        System.out.println("Total records updated: " + recordsUpdated);

        // Close resources
        updateStmt.close();
        stmt.close();
        connection.close();
    }
}
