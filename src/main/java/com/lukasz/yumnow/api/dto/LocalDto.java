package com.lukasz.yumnow.api.dto;

import com.lukasz.yumnow.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocalDto {

    String name;
    String address;
    String description;
    Owner owner;
    Set<Opinion> opinions;
    Set<Food> foods;
    Set<LocalDeliveryAddress> localDeliveryAddresses;
}
