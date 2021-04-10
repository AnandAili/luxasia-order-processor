package com.example.luxasiaorderprocessor.module;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderDetail {

	@Id
	private Long orderid;

	private String customer_last_name;

	public OrderDetail() {
	}

	public OrderDetail(Long orderid, String customer_last_name) {
		this.orderid = orderid;
		this.customer_last_name = customer_last_name;
	}

	public String getCustomer_last_name() {
		return customer_last_name;
	}

	public void setCustomer_last_name(String customer_last_name) {
		this.customer_last_name = customer_last_name;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
}
