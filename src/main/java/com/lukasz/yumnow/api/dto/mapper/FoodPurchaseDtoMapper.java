package com.lukasz.yumnow.api.dto.mapper;

import com.lukasz.yumnow.api.dto.FoodPurchaseDto;
import com.lukasz.yumnow.buisness.FoodService;
import com.lukasz.yumnow.domain.FoodPurchase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FoodPurchaseDtoMapper {

    private FoodService foodService;

    public FoodPurchase mapToFoodPurchase(FoodPurchaseDto foodPurchaseDto) {
        return FoodPurchase.builder()
                .quantity(foodPurchaseDto.getQuantity())
                .food(foodService.findById(foodPurchaseDto.getFoodId()))
                .build();
    }

}
