package com.example.luxasiaorderprocessor.response;

public class OrdersResponse {

	private int code;
	private OrderDetails data;
	private String request_id;

	public OrdersResponse() {
	}

	public OrdersResponse(int code, OrderDetails data, String request_id) {
		this.code = code;
		this.data = data;
		this.request_id = request_id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public OrderDetails getData() {
		return data;
	}

	public void setData(OrderDetails data) {
		this.data = data;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
}
