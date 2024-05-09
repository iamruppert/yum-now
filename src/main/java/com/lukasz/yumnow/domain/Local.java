package com.lukasz.yumnow.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "name")
@ToString(of = {"localId", "name", "address", "description"})
public class Local {

    Integer localId;
    String name;
    String address;
    String description;
    Owner owner;
    Set<Opinion> opinions;
    Set<Food> foods;
    Set<Purchase> purchases;
    Set<LocalDeliveryAddressLocal> localDeliveryAddressLocals;
}
