package com.lukasz.yumnow.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "code")
@ToString(of = {"localDeliveryAddressId", "code", "country", "city", "postalCode", "street"})
public class LocalDeliveryAddress {

    Integer localDeliveryAddressId;
    String code;
    String country;
    String city;
    String postalCode;
    String street;
    Set<LocalDeliveryAddressLocal> localDeliveryAddressLocals;

}
