package com.lukasz.yumnow.buisness;

import com.lukasz.yumnow.buisness.dao.PurchaseDao;
import com.lukasz.yumnow.domain.*;
import com.lukasz.yumnow.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final PurchaseDao purchaseDao;
    private final LocalService localService;
    private final CustomerService customerService;

    public Purchase findById(Integer id){

        Optional<Purchase> optionalPurchase = purchaseDao.findById(id);
        if (optionalPurchase.isPresent()) {
            return optionalPurchase.get();
        } else throw new NotFoundException("Cannot find purchase with id: [%s]".formatted(id));

    }

    public Purchase createPurchase(String email, Customer customer, String localName, List<FoodPurchase> foods, DeliveryAddress deliveryAddress) {

        Customer foundCustomer;

        if (email.isEmpty()) {
            foundCustomer = customerService.create(customer);
        } else {
           foundCustomer = customerService.findByEmail(email);
        }

        Local local = localService.findByName(localName);

        List<FoodPurchase> updatedFoods = foods.stream().map(food -> food.withTotalPrice(BigDecimal.valueOf(food.getQuantity()).multiply(food.getFood().getPrice()))).toList();

        BigDecimal purchaseTotalPrice = updatedFoods.stream().reduce(BigDecimal.ZERO, (left, right) -> left.add(right.getTotalPrice()), BigDecimal::add);

        DeliveryAddress updatedDeliveryAddress = deliveryAddress.withCode(generateDeliveryAddressCode(deliveryAddress));

        if (!local.getLocalDeliveryAddresses().contains(LocalDeliveryAddress.builder().code(updatedDeliveryAddress.getCode()).build())) {
            throw new RuntimeException("This address is not supported by this local [%s]".formatted(updatedDeliveryAddress.getCode()));
        } else {

            Purchase purchaseToSave = Purchase.builder()
                    .purchaseNumber(generatePurchaseNumber())
                    .totalPrice(purchaseTotalPrice)
                    .time(OffsetDateTime.now())
                    .status("CREATED").local(local)
                    .deliveryAddress(updatedDeliveryAddress)
                    .confirmation(null).customer(foundCustomer)
                    .foodPurchases(new HashSet<>(updatedFoods))
                    .build();


            return purchaseDao.create(purchaseToSave);

        }
    }

    public void cancelPurchase(String purchaseNumber) {

        Optional<Purchase> optionalPurchase = purchaseDao.findByPurchaseNumber(purchaseNumber);

        if (optionalPurchase.isEmpty()) {
            throw new NotFoundException("Cannot cancel purchase [%s]. Purchase with this purchase number does not exist."
                    .formatted(purchaseNumber));
        } else {
            Purchase purchase = optionalPurchase.get();
            Duration between = Duration.between(purchase.getTime(), OffsetDateTime.now());
            long minutes = between.toMinutes();
            if (minutes > 1) {
                throw new RuntimeException("Cannot cancel purchase with purchase number: [%s], cannot cancel purchase that was done more then 20 minutes ago"
                        .formatted(purchaseNumber));
            }
            purchaseDao.cancelPurchase(purchase);
        }

    }

    private String generatePurchaseNumber() {

        return UUID.randomUUID().toString();
    }

    private String generateDeliveryAddressCode(DeliveryAddress deliveryAddress) {
        return deliveryAddress.getCountry() + '/' + deliveryAddress.getCity() + '/' + deliveryAddress.getStreet();
    }
}
