package com.example.luxasiaorderprocessor.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
public class OrderDetail {

	private String voucher_platform;
	private String voucher;
	private String warehouse_code;
	private String order_number;
	private String voucher_seller;
	private String created_at;
	private String voucher_code;
	private String gift_option;
	private String shipping_fee_discount_platform;
	private String customer_last_name;
	private String updated_at;
	private String promised_shipping_times;
	private String price;
	private String national_registration_number;
	private String shipping_fee_original;
	private String payment_method;
	private String address_updated_at;
	private String customer_first_name;
	private String shipping_fee_discount_seller;
	private String shipping_fee;
	private String branch_number;
	private String tax_code;
	private String items_count;
	private String delivery_info;
	private String statuses;
	@Embedded
	@AttributeOverrides({
			@AttributeOverride( name = "country", column = @Column(name = "billing_country")),
			@AttributeOverride( name = "address3", column = @Column(name = "billing_address3")),
			@AttributeOverride( name = "phone", column = @Column(name = "billing_phone")),
			@AttributeOverride( name="address2", column = @Column(name = "billing_address2")),
			@AttributeOverride( name="city", column = @Column(name = "billing_city")),
			@AttributeOverride( name="address1", column = @Column(name = "billing_address1")),
			@AttributeOverride( name="post_code", column = @Column(name = "billing_post_code")),
			@AttributeOverride( name="phone2", column = @Column(name = "billing_phone2")),
			@AttributeOverride( name="last_name", column = @Column(name = "billing_last_name")),
			@AttributeOverride( name="address5", column = @Column(name = "billing_address5")),
			@AttributeOverride( name="address4", column = @Column(name = "billing_address4")),
			@AttributeOverride( name="first_name", column = @Column(name = "billing_first_name"))
	})
	private AddressBilling address_billing;
	private String extra_attributes;
	@Id
	private String order_id;
	private String gift_message;
	private String remarks;
	@Embedded
	private AddressShipping address_shipping;

	public OrderDetail() {
	}

	public String getVoucher_platform() {
		return voucher_platform;
	}

	public void setVoucher_platform(String voucher_platform) {
		this.voucher_platform = voucher_platform;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public String getWarehouse_code() {
		return warehouse_code;
	}

	public void setWarehouse_code(String warehouse_code) {
		this.warehouse_code = warehouse_code;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public String getVoucher_seller() {
		return voucher_seller;
	}

	public void setVoucher_seller(String voucher_seller) {
		this.voucher_seller = voucher_seller;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getVoucher_code() {
		return voucher_code;
	}

	public void setVoucher_code(String voucher_code) {
		this.voucher_code = voucher_code;
	}

	public String getGift_option() {
		return gift_option;
	}

	public void setGift_option(String gift_option) {
		this.gift_option = gift_option;
	}

	public String getShipping_fee_discount_platform() {
		return shipping_fee_discount_platform;
	}

	public void setShipping_fee_discount_platform(String shipping_fee_discount_platform) {
		this.shipping_fee_discount_platform = shipping_fee_discount_platform;
	}

	public String getCustomer_last_name() {
		return customer_last_name;
	}

	public void setCustomer_last_name(String customer_last_name) {
		this.customer_last_name = customer_last_name;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getPromised_shipping_times() {
		return promised_shipping_times;
	}

	public void setPromised_shipping_times(String promised_shipping_times) {
		this.promised_shipping_times = promised_shipping_times;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNational_registration_number() {
		return national_registration_number;
	}

	public void setNational_registration_number(String national_registration_number) {
		this.national_registration_number = national_registration_number;
	}

	public String getShipping_fee_original() {
		return shipping_fee_original;
	}

	public void setShipping_fee_original(String shipping_fee_original) {
		this.shipping_fee_original = shipping_fee_original;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getAddress_updated_at() {
		return address_updated_at;
	}

	public void setAddress_updated_at(String address_updated_at) {
		this.address_updated_at = address_updated_at;
	}

	public String getCustomer_first_name() {
		return customer_first_name;
	}

	public void setCustomer_first_name(String customer_first_name) {
		this.customer_first_name = customer_first_name;
	}

	public String getShipping_fee_discount_seller() {
		return shipping_fee_discount_seller;
	}

	public void setShipping_fee_discount_seller(String shipping_fee_discount_seller) {
		this.shipping_fee_discount_seller = shipping_fee_discount_seller;
	}

	public String getShipping_fee() {
		return shipping_fee;
	}

	public void setShipping_fee(String shipping_fee) {
		this.shipping_fee = shipping_fee;
	}

	public String getBranch_number() {
		return branch_number;
	}

	public void setBranch_number(String branch_number) {
		this.branch_number = branch_number;
	}

	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	public String getItems_count() {
		return items_count;
	}

	public void setItems_count(String items_count) {
		this.items_count = items_count;
	}

	public String getDelivery_info() {
		return delivery_info;
	}

	public void setDelivery_info(String delivery_info) {
		this.delivery_info = delivery_info;
	}

	public String getStatuses() {
		return statuses;
	}

	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}

	public AddressBilling getAddress_billing() {
		return address_billing;
	}

	public void setAddress_billing(AddressBilling address_billing) {
		this.address_billing = address_billing;
	}

	public String getExtra_attributes() {
		return extra_attributes;
	}

	public void setExtra_attributes(String extra_attributes) {
		this.extra_attributes = extra_attributes;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getGift_message() {
		return gift_message;
	}

	public void setGift_message(String gift_message) {
		this.gift_message = gift_message;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public AddressShipping getAddress_shipping() {
		return address_shipping;
	}

	public void setAddress_shipping(AddressShipping address_shipping) {
		this.address_shipping = address_shipping;
	}
}
