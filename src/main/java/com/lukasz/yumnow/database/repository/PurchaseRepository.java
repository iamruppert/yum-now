package com.lukasz.yumnow.database.repository;

import com.lukasz.yumnow.buisness.dao.PurchaseDao;
import com.lukasz.yumnow.database.entity.DeliveryAddressEntity;
import com.lukasz.yumnow.database.entity.PurchaseEntity;
import com.lukasz.yumnow.database.jpa.DeliveryAddressJpaRepository;
import com.lukasz.yumnow.database.jpa.FoodPurchaseJpaRepository;
import com.lukasz.yumnow.database.jpa.PurchaseJpaRepository;
import com.lukasz.yumnow.database.jpa.mapper.DeliveryAddressMapper;
import com.lukasz.yumnow.database.jpa.mapper.FoodPurchaseMapper;
import com.lukasz.yumnow.database.jpa.mapper.PurchaseMapper;
import com.lukasz.yumnow.domain.DeliveryAddress;
import com.lukasz.yumnow.domain.Purchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
            PurchaseEntity saved = purchaseJpaRepository.saveAndFlush(purchaseEntity);

            purchase.getFoodPurchases().stream()
                    .map(foodPurchaseMapper::mapToEntity)
                    .forEach(entity->{
                        entity.setPurchase(saved);
                        foodPurchaseJpaRepository.saveAndFlush(entity);
                    });

            return purchaseMapper.mapFromEntity(saved);
        }
        else {
            DeliveryAddressEntity savedDeliveryAddress = deliveryAddressJpaRepository.save(deliveryAddressEntity);
            PurchaseEntity purchaseEntity = purchaseMapper.mapToEntity(purchase);
            purchaseEntity.setDeliveryAddress(savedDeliveryAddress);
            PurchaseEntity saved = purchaseJpaRepository.saveAndFlush(purchaseEntity);

//            Set<FoodPurchaseEntity> foodPurchases = saved.getFoodPurchases();
//            List<FoodPurchaseEntity> list = foodPurchases.stream()
//                    .map(e -> e.withPurchase(saved)).toList();
//
//            list.stream().map(foodPurchaseJpaRepository::saveAndFlush);

            purchase.getFoodPurchases().stream()
                    .map(foodPurchaseMapper::mapToEntity)
                    .forEach(entity->{
                        entity.setPurchase(saved);
                        foodPurchaseJpaRepository.saveAndFlush(entity);
                    });

            return purchaseMapper.mapFromEntity(saved);
        }

    }

    @Override
    public Optional<Purchase> findById(Integer id) {
        Optional<PurchaseEntity> optionalPurchase = purchaseJpaRepository.findById(id);

        if(optionalPurchase.isPresent()){
            PurchaseEntity purchaseEntity = optionalPurchase.get();
            Purchase purchase = purchaseMapper.mapFromEntity(purchaseEntity);
            return Optional.of(purchase);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public Optional<Purchase> findByPurchaseNumber(String purchaseNumber) {

        Optional<PurchaseEntity> optionalPurchase = purchaseJpaRepository.findByPurchaseNumber(purchaseNumber);

        if(optionalPurchase.isPresent()){
            PurchaseEntity purchaseEntity = optionalPurchase.get();
            Purchase purchase = purchaseMapper.mapFromEntity(purchaseEntity);
            return Optional.of(purchase);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public void cancelPurchase(Purchase purchase) {
        PurchaseEntity purchaseEntity = purchaseJpaRepository.findByPurchaseNumber(purchase.getPurchaseNumber()).orElseThrow();
        purchaseEntity.setStatus("CANCELED");
        purchaseJpaRepository.save(purchaseEntity);
    }
}
