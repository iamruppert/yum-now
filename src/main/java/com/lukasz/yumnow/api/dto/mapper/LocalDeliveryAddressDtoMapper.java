package com.lukasz.yumnow.api.dto.mapper;

import com.lukasz.yumnow.api.dto.LocalDeliveryAddressDto;
import com.lukasz.yumnow.domain.LocalDeliveryAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalDeliveryAddressDtoMapper {

    LocalDeliveryAddress map(LocalDeliveryAddressDto dto);
}
