package com.example.demo.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Integer addressId;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}