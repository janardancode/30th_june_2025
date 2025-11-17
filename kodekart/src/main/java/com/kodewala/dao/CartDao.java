package com.kodewala.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kodewala.model.Cart;
import com.kodewala.utils.DbUtils;

public class CartDao {
	public boolean addToCart(int userId, int productId, int quantity) {
	    String selectSql = "SELECT quantity FROM cart WHERE user_id=? AND product_id=?";
	    String insertSql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
	    String updateSql = "UPDATE cart SET quantity=? WHERE user_id=? AND product_id=?";
	    try (Connection conn = DbUtils.getConnection()) {
	        PreparedStatement ps = conn.prepareStatement(selectSql);
	        ps.setInt(1, userId);
	        ps.setInt(2, productId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            int existingQty = rs.getInt("quantity");
	            int newQty = existingQty + quantity;
	            ps = conn.prepareStatement(updateSql);
	            ps.setInt(1, newQty);
	            ps.setInt(2, userId);
	            ps.setInt(3, productId);
	            return ps.executeUpdate() > 0;
	        } else {
	            ps = conn.prepareStatement(insertSql);
	            ps.setInt(1, userId);
	            ps.setInt(2, productId);
	            ps.setInt(3, quantity);
	            return ps.executeUpdate() > 0;
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}

	public boolean removeFromCart(int userId, int productId) {
	    String sql = "DELETE FROM cart WHERE user_id=? AND product_id=?";
	    try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, userId);
	        ps.setInt(2, productId);
	        return ps.executeUpdate() > 0;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}

	public List<Cart> getCartItems(int userId) {
	    List<Cart> list = new ArrayList<>();
	    String sql = "SELECT * FROM cart WHERE user_id=?";
	    try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            list.add(new Cart(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("product_id"), rs.getInt("quantity")));
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	    return list;
	}

}
