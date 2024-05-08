package com.lukasz.yumnow.domain;

import com.lukasz.yumnow.database.entity.OpinionEntity;
import com.lukasz.yumnow.database.entity.PurchaseEntity;
import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"customerId", "name", "surname", "email", "address"})
public class Customer {

    Integer customerId;
    String name;
    String surname;
    String email;
    String address;
    Set<OpinionEntity> opinions;
    Set<PurchaseEntity> purchases;

}
