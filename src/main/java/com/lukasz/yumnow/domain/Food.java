package com.lukasz.yumnow.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "code")
@ToString(of = {"foodId","code", "name", "category", "description", "price", "calories"})
public class Food {

    Integer foodId;
    String code;
    String name;
    String category;
    String description;
    BigDecimal price;
    Integer calories;
    Local local;
    Set<FoodPurchase> foodPurchases;

}