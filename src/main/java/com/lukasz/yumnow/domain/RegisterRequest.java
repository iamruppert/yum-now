package com.lukasz.yumnow.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"name", "surname", "email", "address", "accountType"})
public class RegisterRequest {

    String name;
    String surname;
    String email;
    String address;
    String accountType;

}
