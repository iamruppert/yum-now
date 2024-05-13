package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.LocalEntity;
import com.lukasz.yumnow.database.entity.PurchaseEntity;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseMapper {

    @Mapping(source = "local", target = "local", qualifiedByName = "mapLocal")
    @Mapping(target = "deliveryAddress", ignore = true)
    @Mapping(target = "confirmation", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "foodPurchases", ignore = true)
    Purchase mapFromEntity(PurchaseEntity entity);

    @Named("mapLocal")
    default Local mapLocal(LocalEntity entity){
        return this.mapFromEntityLocal(entity);
    }

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "opinions", ignore = true)
    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    @Mapping(target = "localDeliveryAddresses", ignore = true)
    Local mapFromEntityLocal(LocalEntity localEntity);

    PurchaseEntity mapToEntity(Purchase purchase);
}
