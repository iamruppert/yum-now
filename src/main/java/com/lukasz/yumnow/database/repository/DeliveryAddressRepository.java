package com.lukasz.yumnow.database.repository;

import com.lukasz.yumnow.buisness.dao.DeliveryAddressDao;
import com.lukasz.yumnow.database.entity.DeliveryAddressEntity;
import com.lukasz.yumnow.database.jpa.DeliveryAddressJpaRepository;
import com.lukasz.yumnow.database.jpa.mapper.DeliveryAddressMapper;
import com.lukasz.yumnow.domain.DeliveryAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DeliveryAddressRepository implements DeliveryAddressDao {

    private final DeliveryAddressJpaRepository deliveryAddressJpaRepository;
    private final DeliveryAddressMapper deliveryAddressMapper;

    @Override
    public List<DeliveryAddress> findAllByCode(String code) {
        List<DeliveryAddressEntity> allByCode = deliveryAddressJpaRepository.findAllByCode(code);
        return allByCode.stream().map(deliveryAddressMapper::mapFromEntity).toList();

    }

    @Override
    public DeliveryAddress create(DeliveryAddress deliveryAddress) {
        DeliveryAddressEntity deliveryAddressEntity = deliveryAddressMapper.mapToEntity(deliveryAddress);
        DeliveryAddressEntity saved = deliveryAddressJpaRepository.save(deliveryAddressEntity);
        return deliveryAddressMapper.mapFromEntity(saved);
    }
}
