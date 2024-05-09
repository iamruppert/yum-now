package com.lukasz.yumnow.database.jpa.mapper;

import com.lukasz.yumnow.database.entity.LocalDeliveryAddressEntity;
import com.lukasz.yumnow.database.entity.LocalEntity;
import com.lukasz.yumnow.domain.Local;
import com.lukasz.yumnow.domain.LocalDeliveryAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalMapper {

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "opinions", ignore = true)
    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    @Mapping(source = "localDeliveryAddresses",target = "localDeliveryAddresses", qualifiedByName = "mapDeliveryAddress")
    Local mapFromEntity(LocalEntity entity);

    @Named("mapDeliveryAddress")
    default Set<LocalDeliveryAddress> mapFromEntity(Set<LocalDeliveryAddressEntity> entities){
        return entities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }

    @Mapping(target = "locals", ignore = true)
    LocalDeliveryAddress mapFromEntity(LocalDeliveryAddressEntity localDeliveryAddressEntity);


    LocalEntity mapToEntity(Local local);
}
