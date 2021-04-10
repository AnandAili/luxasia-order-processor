package com.example.luxasiaorderprocessor.response;


import com.example.luxasiaorderprocessor.module.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
	private long count;
	private List<Order> orders = new ArrayList<>();

	public OrderDetails() {
	}

	public OrderDetails(long count, List<Order> orders) {
		this.count = count;
		this.orders = orders;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
