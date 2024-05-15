package com.lukasz.yumnow.api.dto.mapper;

import com.lukasz.yumnow.api.dto.RegisterRequestDto;
import com.lukasz.yumnow.domain.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterRequestDtoMapper {

    RegisterRequest map(RegisterRequestDto dto);

    RegisterRequestDto map(RegisterRequest registerRequest);
}
