package com.lukasz.yumnow.database.repository;

import com.lukasz.yumnow.buisness.dao.LocalDeliveryAddressDao;
import com.lukasz.yumnow.database.entity.LocalDeliveryAddressEntity;
import com.lukasz.yumnow.database.entity.LocalEntity;
import com.lukasz.yumnow.database.jpa.LocalDeliveryAddressJpaRepository;
import com.lukasz.yumnow.database.jpa.LocalJpaRepository;
import com.lukasz.yumnow.database.jpa.mapper.LocalDeliveryAddressMapper;
import com.lukasz.yumnow.database.jpa.mapper.LocalMapper;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.LocalDeliveryAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class LocalDeliveryAddressRepository implements LocalDeliveryAddressDao {

    private final LocalDeliveryAddressJpaRepository localDeliveryAddressJpaRepository;
    private final LocalDeliveryAddressMapper localDeliveryAddressMapper;
    private final LocalJpaRepository localJpaRepository;
    private final LocalMapper localMapper;

    @Override
    public LocalDeliveryAddress create(Local local, LocalDeliveryAddress localDeliveryAddress) {
        LocalEntity localEntity = localMapper.mapToEntity(local);
        LocalEntity retrieved = localJpaRepository.findByName(localEntity.getName()).orElseThrow();
        LocalDeliveryAddressEntity entity = localDeliveryAddressMapper.mapToEntity(localDeliveryAddress);
        entity.getLocals().add(retrieved);
        localDeliveryAddressJpaRepository.save(entity);
//        localJpaRepository.save(retrieved);
        return localDeliveryAddressMapper.mapFromEntity(entity);
    }



    @Override
    public Optional<LocalDeliveryAddress> findByCode(String code) {
        return Optional.empty();
    }
}
