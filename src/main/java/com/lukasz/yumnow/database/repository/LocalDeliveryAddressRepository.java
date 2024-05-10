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
        LocalEntity localEntity = localJpaRepository.findByName(local.getName()).get();
//        LocalEntity localEntity = localMapper.mapToEntity(local);
        Optional<LocalDeliveryAddressEntity> optionalDeliveryAddress = localDeliveryAddressJpaRepository.findByCode(localDeliveryAddress.getCode());
        if(optionalDeliveryAddress.isPresent()){
            LocalDeliveryAddressEntity localDeliveryAddressEntity = optionalDeliveryAddress.get();
            localDeliveryAddressEntity.getLocals().add(localEntity);
            localDeliveryAddressJpaRepository.save(localDeliveryAddressEntity);
//            localEntity.getLocalDeliveryAddresses().add(localDeliveryAddressEntity);
//            localJpaRepository.save(localEntity);
            return localDeliveryAddressMapper.mapFromEntity(localDeliveryAddressEntity);
        }
        else{
            LocalDeliveryAddressEntity localDeliveryAddressEntity = localDeliveryAddressMapper.mapToEntity(localDeliveryAddress);
            localDeliveryAddressEntity.getLocals().add(localEntity);
            localEntity.getLocalDeliveryAddresses().add(localDeliveryAddressEntity);
            localJpaRepository.save(localEntity);
            localDeliveryAddressJpaRepository.save(localDeliveryAddressEntity);
            return localDeliveryAddressMapper.mapFromEntity(localDeliveryAddressEntity);
        }
    }



    @Override
    public Optional<LocalDeliveryAddress> findByCode(String code) {
        return Optional.empty();
    }
}
