package com.lukasz.yumnow.domain;

import com.lukasz.yumnow.database.entity.LocalEntity;
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
    Set<Local> locals;

}
