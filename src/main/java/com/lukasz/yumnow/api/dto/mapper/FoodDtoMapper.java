package com.lukasz.yumnow.api.dto.mapper;

import com.lukasz.yumnow.api.dto.FoodDto;
import com.lukasz.yumnow.domain.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodDtoMapper {

    Food map(FoodDto foodDto);
}
