package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.CustomerEntity;
import com.lukasz.yumnow.database.entity.LocalDeliveryAddressEntity;
import com.lukasz.yumnow.domain.Customer;
import com.lukasz.yumnow.domain.LocalDeliveryAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.jpa.repository.EntityGraph;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalDeliveryAddressMapper {

    @Mapping(target = "locals", ignore = true)
    LocalDeliveryAddress mapFromEntity(LocalDeliveryAddressEntity entity);

    LocalDeliveryAddressEntity mapToEntity(LocalDeliveryAddress address);
}
