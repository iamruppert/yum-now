package com.lukasz.yumnow.database.jpa;

import com.lukasz.yumnow.database.entity.DeliveryAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAddressJpaRepository extends JpaRepository<DeliveryAddressEntity, Integer> {
}
