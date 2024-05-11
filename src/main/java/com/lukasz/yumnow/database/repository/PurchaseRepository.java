package com.lukasz.yumnow.database.repository;

import com.lukasz.yumnow.buisness.dao.PurchaseDao;
import com.lukasz.yumnow.database.entity.DeliveryAddressEntity;
import com.lukasz.yumnow.database.entity.PurchaseEntity;
import com.lukasz.yumnow.database.jpa.DeliveryAddressJpaRepository;
import com.lukasz.yumnow.database.jpa.LocalDeliveryAddressJpaRepository;
import com.lukasz.yumnow.database.jpa.PurchaseJpaRepository;
import com.lukasz.yumnow.database.jpa.mapper.DeliveryAddressMapper;
import com.lukasz.yumnow.database.jpa.mapper.PurchaseMapper;
import com.lukasz.yumnow.domain.DeliveryAddress;
import com.lukasz.yumnow.domain.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PurchaseRepository implements PurchaseDao {

    private final PurchaseJpaRepository purchaseJpaRepository;
    private final PurchaseMapper purchaseMapper;
    private final DeliveryAddressMapper deliveryAddressMapper;
    private final DeliveryAddressJpaRepository deliveryAddressJpaRepository;

    @Override
    public Purchase create(Purchase purchase) {
        PurchaseEntity purchaseEntity = purchaseMapper.mapToEntity(purchase);
        PurchaseEntity saved = purchaseJpaRepository.save(purchaseEntity);
        return purchaseMapper.mapFromEntity(saved);
    }
}
