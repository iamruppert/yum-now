package com.lukasz.yumnow.domain;

import com.lukasz.yumnow.database.entity.LocalEntity;
import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "code")
@ToString(of = {"localDeliveryAddressId", "code", "country", "city", "street"})
public class LocalDeliveryAddress {

    Integer localDeliveryAddressId;
    String code;
    String country;
    String city;
    String street;
    Set<Local> locals;

}
