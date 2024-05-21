package com.lukasz.yumnow.api.dto;


import com.lukasz.yumnow.domain.Customer;
import com.lukasz.yumnow.domain.DeliveryAddress;
import com.lukasz.yumnow.domain.FoodPurchase;
import com.lukasz.yumnow.domain.Local;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {

    String email;
    Customer customer;
    String localName;
    DeliveryAddress deliveryAddress;
    List<FoodPurchaseDto> foodPurchases;
}
