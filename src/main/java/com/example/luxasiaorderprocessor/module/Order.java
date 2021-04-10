package com.example.luxasiaorderprocessor.module;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderDetail {

    private String voucher_platform;
    private String  voucher;
    private String  warehouse_code;
    private String  order_number;
    private String  voucher_seller;
    private String  created_at;
    private String  voucher_code;
    private String gift_option;
    private String  shipping_fee_discount_platform;
    private String  customer_last_name;
    private String  updated_at;
    private String  promised_shipping_times;
    private String  price;
    private String  national_registration_number;
    private String  shipping_fee_original;
    private String  payment_method;
    private String  address_updated_at;
    private String  customer_first_name;
    private String  shipping_fee_discount_seller;
    private String  shipping_fee;
    private String  branch_number;
    private String  tax_code;
    private String  items_count;
    private String  delivery_info;
    private String  statuses;
    AddressBilling address_billing;
    private String  extra_attributes;
    private String  order_id;
    private String  gift_message;
    private String  remarks ;
    AddressShipping address_shipping;


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
