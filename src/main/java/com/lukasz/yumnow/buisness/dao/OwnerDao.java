package com.lukasz.yumnow.buisness.dao;

import com.lukasz.yumnow.domain.Owner;

import java.util.Optional;

public interface OwnerDao {

    Owner create(Owner owner);

    Optional<Owner> findOwnerByEmail(String email);

}
