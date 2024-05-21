package com.lukasz.yumnow.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {

    String name;
    String category;
    String description;
    BigDecimal price;
    Integer calories;
}
