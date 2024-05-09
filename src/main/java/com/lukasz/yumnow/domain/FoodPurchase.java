package com.lukasz.yumnow.domain;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@EqualsAndHashCode(of = "foodPurchaseId")
@ToString(of = {"foodPurchaseId", "quantity", "totalPrice"})
public class FoodPurchase {

    Integer foodPurchaseId;
    Integer quantity;
    BigDecimal totalPrice;
    Purchase purchase;
    Food food;
}
