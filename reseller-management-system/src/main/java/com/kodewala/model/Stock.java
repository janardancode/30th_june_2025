package com.kodewala.model;

public class Stock {
	private int id;
	private int resellerId;
	private String productName;
	private int quantity;
	private double price;

	public int getId() {
		return id;
	}

	public int getResellerId() {
		return resellerId;
	}

	public String getProductName() {
		return productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setResellerId(int resellerId) {
		this.resellerId = resellerId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
