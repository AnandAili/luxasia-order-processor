package com.example.luxasiaorderprocessor.response;


import com.example.luxasiaorderprocessor.module.OrderDetail;
import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
	private long count;
	private List<OrderDetail> orders = new ArrayList<>();

	public OrderDetails() {
	}

	public OrderDetails(long count, List<OrderDetail> orders) {
		this.count = count;
		this.orders = orders;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<OrderDetail> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetail> orders) {
		this.orders = orders;
	}
}
