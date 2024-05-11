package com.lukasz.yumnow.buisness.dao;

import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.LocalDeliveryAddress;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface LocalDeliveryAddressDao {

    LocalDeliveryAddress create(Local local, LocalDeliveryAddress localDeliveryAddress);

    Optional<LocalDeliveryAddress> findByCode(String code);
}
