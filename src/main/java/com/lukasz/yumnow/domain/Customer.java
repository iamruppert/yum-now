package com.lukasz.yumnow.domain;

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
    Set<Opinion> opinions;
    Set<Purchase> purchases;

}
