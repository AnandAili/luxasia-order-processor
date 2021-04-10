package com.example.luxasiaorderprocessor.module;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Entity
public class OrderDetail {

    private String voucher_platform;
    private String  voucher;
    private String  warehouse_code;
    @Id
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
    @Embedded
    private AddressBilling address_billing;
    private String  extra_attributes;
    private String  order_id;
    private String  gift_message;
    private String  remarks ;
    @Embedded
    private AddressShipping address_shipping;
}
