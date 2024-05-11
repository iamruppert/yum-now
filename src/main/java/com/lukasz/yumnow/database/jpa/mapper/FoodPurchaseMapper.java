package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.FoodPurchaseEntity;
import com.lukasz.yumnow.domain.FoodPurchase;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodPurchaseMapper {

    FoodPurchase mapFromEntity(FoodPurchaseEntity foodPurchaseEntity);

    FoodPurchaseEntity mapToEntity(FoodPurchase foodPurchase);
}
