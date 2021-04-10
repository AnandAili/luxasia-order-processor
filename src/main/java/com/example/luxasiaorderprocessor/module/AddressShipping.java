package com.example.luxasiaorderprocessor.module;

import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@Embeddable
public class AddressShipping {

	private String country;
    private String address3;
    private String phone;
    private String address2;
    private String city;
    private String address1;
    private String post_code;
    private String phone2;
    private String last_name;
    private String address5;
    private String address4;
    private String first_name;

}
