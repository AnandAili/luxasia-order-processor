package com.example.luxasiaorderprocessor.module;


public class Order {

	private String customer_last_name;

	public Order() {
	}

	public Order(String customer_last_name) {
		this.customer_last_name = customer_last_name;
	}

	public String getCustomer_last_name() {
		return customer_last_name;
	}

	public void setCustomer_last_name(String customer_last_name) {
		this.customer_last_name = customer_last_name;
	}
}
