package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.PurchaseEntity;
import com.lukasz.yumnow.domain.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PurchaseMapper {

    Purchase mapFromEntity(PurchaseEntity entity);

    PurchaseEntity mapToEntity(Purchase purchase);
}
