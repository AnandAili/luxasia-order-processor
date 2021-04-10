package com.example.luxasiaorderprocessor.mock.sapinventory;

public class ProductStock {
	private int quantity;
	private String name;

	public ProductStock() {
	}

	public ProductStock(int quantity, String name) {
		this.quantity = quantity;
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
