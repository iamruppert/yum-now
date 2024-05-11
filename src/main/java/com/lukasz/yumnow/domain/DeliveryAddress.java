package com.lukasz.yumnow.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of= {
        "code",
        "country",
        "city",
        "postalCode",
        "street",
        "buildingNumber",
        "apartmentNumber"
})
@ToString(of = {"deliveryAddressId", "code", "country", "city", "postalCode","street","buildingNumber","apartmentNumber"})
public class DeliveryAddress {

    Integer deliveryAddressId;
    String code;
    String country;
    String city;
    String postalCode;
    String street;
    Integer buildingNumber;
    Integer apartmentNumber;
    Set<Purchase> purchases;

}
