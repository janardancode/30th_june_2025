package com.kodewala.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;               
import java.util.ArrayList;
import java.util.List;

import com.kodewala.model.Cart;
import com.kodewala.model.Order;
import com.kodewala.model.Product;
import com.kodewala.utils.DbUtils;

public class OrderDao {

    private final ProductDao productDao = new ProductDao();

    public boolean placeOrder(int userId) {
        List<Cart> cartItems = getCartItems(userId);
        if (cartItems == null || cartItems.isEmpty()) {
            System.out.println("Cart is empty!");
            return false;
        }

        double totalAmount = 0;
        for (Cart c : cartItems) {
            Product p;
            try {
                p = productDao.getProductById(c.getProductId());
            } catch (Exception ex) {
                p = null;
            }
            if (p == null || p.getQuantity() < c.getQuantity()) {
                System.out.println("Product " + c.getProductId() + " not available in sufficient quantity.");
                return false;
            }
            totalAmount += p.getPrice() * c.getQuantity();
        }

        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            conn.setAutoCommit(false);

            String insertOrderSql = "INSERT INTO orders (user_id, order_date, total_amount) VALUES (?, NOW(), ?)";
            try (PreparedStatement psOrder = conn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS)) {
                psOrder.setInt(1, userId);
                psOrder.setDouble(2, totalAmount);
                psOrder.executeUpdate();

                try (ResultSet keys = psOrder.getGeneratedKeys()) {
                    if (!keys.next()) throw new SQLException("Order ID not generated");
                    int orderId = keys.getInt(1);

                    String insertOrderItemSql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
                    String updateProductQtySql = "UPDATE products SET quantity = quantity - ? WHERE id = ?";

                    try (PreparedStatement psItem = conn.prepareStatement(insertOrderItemSql);
                         PreparedStatement psUpdateProduct = conn.prepareStatement(updateProductQtySql)) {

                        for (Cart c : cartItems) {
                            Product p = productDao.getProductById(conn, c.getProductId()); // transaction-safe

                            if (p == null || p.getQuantity() < c.getQuantity()) {
                                throw new SQLException("Product " + c.getProductId() + " not available during transaction");
                            }

                            psItem.setInt(1, orderId);
                            psItem.setInt(2, c.getProductId());
                            psItem.setInt(3, c.getQuantity());
                            psItem.setDouble(4, p.getPrice());
                            psItem.executeUpdate();

                            psUpdateProduct.setInt(1, c.getQuantity());
                            psUpdateProduct.setInt(2, c.getProductId());
                            psUpdateProduct.executeUpdate();
                        }
                    }

                    String clearCartSql = "DELETE FROM cart WHERE user_id=?";
                    try (PreparedStatement psClearCart = conn.prepareStatement(clearCartSql)) {
                        psClearCart.setInt(1, userId);
                        psClearCart.executeUpdate();
                    }

                    conn.commit();
                    System.out.println("Order placed successfully. Total amount: " + totalAmount);
                    return true;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Cart> getCartItems(int userId) {
        String sql = "SELECT product_id, quantity FROM cart WHERE user_id = ?";
        List<Cart> list = new ArrayList<>();
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cart c = new Cart();
                    c.setProductId(rs.getInt("product_id"));
                    c.setQuantity(rs.getInt("quantity"));
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // existing getters for orders...
    public List<Order> getUserOrders(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC";
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(new Order(rs.getInt("id"), userId, rs.getTimestamp("order_date"), rs.getDouble("total_amount")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY order_date DESC";
        try (Connection conn = DbUtils.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"), rs.getInt("user_id"), rs.getTimestamp("order_date"), rs.getDouble("total_amount")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }
}
