package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.FoodEntity;
import com.lukasz.yumnow.domain.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodMapper {

    @Mapping(target = "local", ignore = true)
    @Mapping(target = "foodPurchases", ignore = true)
    Food mapFromEntity(FoodEntity foodEntity);

    FoodEntity mapToEntity(Food food);
}
