package com.lukasz.yumnow.database.repository;

import com.lukasz.yumnow.buisness.dao.PurchaseDao;
import com.lukasz.yumnow.database.entity.DeliveryAddressEntity;
import com.lukasz.yumnow.database.entity.FoodPurchaseEntity;
import com.lukasz.yumnow.database.entity.PurchaseEntity;
import com.lukasz.yumnow.database.jpa.DeliveryAddressJpaRepository;
import com.lukasz.yumnow.database.jpa.FoodPurchaseJpaRepository;
import com.lukasz.yumnow.database.jpa.PurchaseJpaRepository;
import com.lukasz.yumnow.database.jpa.mapper.DeliveryAddressMapper;
import com.lukasz.yumnow.database.jpa.mapper.FoodPurchaseMapper;
import com.lukasz.yumnow.database.jpa.mapper.PurchaseMapper;
import com.lukasz.yumnow.domain.DeliveryAddress;
import com.lukasz.yumnow.domain.FoodPurchase;
import com.lukasz.yumnow.domain.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PurchaseRepository implements PurchaseDao {

    private final PurchaseJpaRepository purchaseJpaRepository;
    private final PurchaseMapper purchaseMapper;

    private final DeliveryAddressMapper deliveryAddressMapper;
    private final DeliveryAddressJpaRepository deliveryAddressJpaRepository;

    private final FoodPurchaseJpaRepository foodPurchaseJpaRepository;
    private final FoodPurchaseMapper foodPurchaseMapper;

    @Override
    public Purchase create(Purchase purchase) {

        DeliveryAddress deliveryAddress = purchase.getDeliveryAddress();
        DeliveryAddressEntity deliveryAddressEntity = deliveryAddressMapper.mapToEntity(deliveryAddress);

        Optional<DeliveryAddressEntity> optionalDeliveryAddressEntity = deliveryAddressJpaRepository.findOneByAllFields(
                deliveryAddressEntity.getCode(),
                deliveryAddressEntity.getCountry(),
                deliveryAddressEntity.getCity(),
                deliveryAddressEntity.getPostalCode(),
                deliveryAddressEntity.getStreet(),
                deliveryAddressEntity.getBuildingNumber(),
                deliveryAddressEntity.getApartmentNumber()
        );

        if (optionalDeliveryAddressEntity.isPresent()) {
            PurchaseEntity purchaseEntity = purchaseMapper.mapToEntity(purchase);
            purchaseEntity.setDeliveryAddress(optionalDeliveryAddressEntity.get());
            PurchaseEntity saved = purchaseJpaRepository.save(purchaseEntity);

            Set<FoodPurchaseEntity> foodPurchaseEntities = saved.getFoodPurchases();
            List<FoodPurchaseEntity> collect = foodPurchaseEntities.stream()
                    .map(v -> v.withPurchase(saved))
                    .toList();


            Set<FoodPurchaseEntity> collect1 = collect.stream().map(foodPurchaseJpaRepository::saveAndFlush).collect(Collectors.toSet());

            return purchaseMapper.mapFromEntity(saved);
        } else {
            DeliveryAddressEntity savedDeliveryAddress = deliveryAddressJpaRepository.save(deliveryAddressEntity);
            PurchaseEntity purchaseEntity = purchaseMapper.mapToEntity(purchase);
            purchaseEntity.setDeliveryAddress(savedDeliveryAddress);
            PurchaseEntity saved = purchaseJpaRepository.save(purchaseEntity);

            Set<FoodPurchaseEntity> foodPurchases = saved.getFoodPurchases();
            List<FoodPurchaseEntity> list = foodPurchases.stream()
                    .map(e -> e.withPurchase(saved)).toList();

            list.stream().map(foodPurchaseJpaRepository::saveAndFlush);

            return purchaseMapper.mapFromEntity(saved);
        }

    }
}
