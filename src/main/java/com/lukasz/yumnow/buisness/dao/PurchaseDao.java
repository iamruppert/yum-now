package com.lukasz.yumnow.buisness.dao;

import com.lukasz.yumnow.domain.Purchase;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface PurchaseDao {

    Purchase create(Purchase purchase);

    Optional<Purchase> findById(Integer id);

    Optional<Purchase> findByPurchaseNumber(String purchaseNumber);

    void cancelPurchase(Purchase purchase);
}
