package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.PurchaseEntity;
import com.lukasz.yumnow.domain.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseMapper {

    @Mapping(target = "local", ignore = true)
    @Mapping(target = "deliveryAddress", ignore = true)
    @Mapping(target = "confirmation", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "foodPurchases", ignore = true)
    Purchase mapFromEntity(PurchaseEntity entity);

    PurchaseEntity mapToEntity(Purchase purchase);
}
