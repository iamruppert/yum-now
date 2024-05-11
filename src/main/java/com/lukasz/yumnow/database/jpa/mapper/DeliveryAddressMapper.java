package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.DeliveryAddressEntity;
import com.lukasz.yumnow.domain.DeliveryAddress;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeliveryAddressMapper {

    DeliveryAddress mapFromEntity(DeliveryAddressEntity entity);

    DeliveryAddressEntity mapToEntity(DeliveryAddress address);
}
