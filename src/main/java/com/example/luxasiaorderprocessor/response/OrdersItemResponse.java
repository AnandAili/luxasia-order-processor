package com.example.luxasiaorderprocessor.response;

import java.util.List;

public class OrdersItemResponse {

	private int code;
	private List<OrderItem> data;
	private String request_id;

	public OrdersItemResponse() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public List<OrderItem> getData() {
		return data;
	}

	public void setData(List<OrderItem> data) {
		this.data = data;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
}
