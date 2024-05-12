package com.lukasz.yumnow.buisness;

import com.lukasz.yumnow.buisness.dao.PurchaseDao;
import com.lukasz.yumnow.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final PurchaseDao purchaseDao;
    private final LocalService localService;
    private final CustomerService customerService;
    private final FoodService foodService;


    public Purchase createPurchaseWithAccount(String email, String localName, List<FoodPurchase> foods, DeliveryAddress deliveryAddress) {
        Local local = localService.findByName(localName);
        Customer customer = customerService.findByEmail(email);


        List<FoodPurchase> updatedFoods = foods.stream()
                .map(food -> food.withTotalPrice(BigDecimal.valueOf(food.getQuantity()).multiply(food.getFood().getPrice())))
                .toList();

        BigDecimal purchaseTotalPrice = updatedFoods.stream().reduce(BigDecimal.ZERO, (left, right) -> left.add(right.getTotalPrice()), BigDecimal::add);

        DeliveryAddress updatedDeliveryAddress = deliveryAddress.withCode(generateDeliveryAddressCode(deliveryAddress));

        if (!local.getLocalDeliveryAddresses().contains(LocalDeliveryAddress.builder()
                .code(updatedDeliveryAddress.getCode())
                .build())) {
            throw new RuntimeException("This address is not supported by this local [%s]"
                    .formatted(updatedDeliveryAddress.getCode()));
        } else {

            Purchase purchaseToSave = Purchase.builder()
                    .purchaseNumber(generatePurchaseNumber())
                    .totalPrice(purchaseTotalPrice)
                    .time(OffsetDateTime.now())
                    .status("CREATED")
                    .local(local)
                    .deliveryAddress(updatedDeliveryAddress)
                    .confirmation(null)
                    .customer(customer)
                    .foodPurchases(new HashSet<>(updatedFoods))
                    .build();


            return purchaseDao.create(purchaseToSave);

        }

    }

    private String generatePurchaseNumber() {

        return UUID.randomUUID().toString();
    }

    private String generateDeliveryAddressCode(DeliveryAddress deliveryAddress) {
        return
                deliveryAddress.getCountry() + '/' +
                        deliveryAddress.getCity() + '/' +
                        deliveryAddress.getStreet();
    }

//    public Purchase createNextTimePurchase(String email, String localName, List<FoodPurchase> foods, DeliveryAddress deliveryAddress) {
//
//    }
}
