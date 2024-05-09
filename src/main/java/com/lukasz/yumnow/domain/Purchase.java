package com.lukasz.yumnow.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "purchaseNumber")
@ToString(of = {"purchaseId", "purchaseNumber", "totalPrice", "time", "status"})
public class Purchase {

    Integer purchaseId;
    String purchaseNumber;
    BigDecimal totalPrice;
    OffsetDateTime time;
    String status;
    Local local;
    DeliveryAddress deliveryAddress;
    Confirmation confirmation;
    Customer customer;
    Set<FoodPurchase> foodPurchases;

}
