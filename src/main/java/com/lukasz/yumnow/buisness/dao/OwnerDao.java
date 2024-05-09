package com.lukasz.yumnow.buisness.dao;

import com.lukasz.yumnow.domain.Owner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface OwnerDao {

    Owner create(Owner owner);

    Optional<Owner> findByEmail(String email);

}
