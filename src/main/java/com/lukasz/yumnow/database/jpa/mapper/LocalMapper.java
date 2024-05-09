package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.LocalEntity;
import com.lukasz.yumnow.domain.Local;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalMapper {

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "opinions", ignore = true)
    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    @Mapping(target = "localDeliveryAddressLocals", ignore = true)
    Local mapFromEntity(LocalEntity entity);

    LocalEntity mapToEntity(Local local);
}
