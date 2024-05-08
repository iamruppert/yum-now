package com.lukasz.yumnow.domain;

import com.lukasz.yumnow.database.entity.LocalEntity;
import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"ownerId", "name", "surname", "email", "address"})
public class Owner {

    Integer ownerId;
    String name;
    String surname;
    String email;
    String address;
    Set<LocalEntity> locals;

}
