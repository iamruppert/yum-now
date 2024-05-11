package com.lukasz.yumnow.buisness.dao;

import com.lukasz.yumnow.domain.DeliveryAddress;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeliveryAddressDao {

    List<DeliveryAddress> findAllByCode(String code);

    DeliveryAddress create(DeliveryAddress deliveryAddress);
}
