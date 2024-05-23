package com.lukasz.yumnow.api.dto.mapper;

import com.lukasz.yumnow.api.dto.LocalDto;
import com.lukasz.yumnow.domain.Local;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalDtoMapper {

    default LocalDto map(Local local) {
        return LocalDto.builder()
                .name(local.getName())
                .owner(local.getOwner() != null ? local.getOwner() : null)
                .address(local.getAddress())
                .description(local.getDescription())
                .opinions(local.getOpinions())
                .foods(local.getFoods())
                .localDeliveryAddresses(local.getLocalDeliveryAddresses())
                .build();
    }
}
