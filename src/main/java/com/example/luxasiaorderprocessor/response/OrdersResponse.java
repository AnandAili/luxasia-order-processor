package com.example.luxasiaorderprocessor.response;

public class OrdersResponse {

	private OrderDetails data;

	public OrdersResponse() {
	}

	public OrdersResponse(OrderDetails data) {
		this.data = data;
	}

	public OrderDetails getData() {
		return data;
	}

	public void setData(OrderDetails data) {
		this.data = data;
	}
}
